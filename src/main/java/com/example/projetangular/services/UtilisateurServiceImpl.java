package com.example.projetangular.services;

import com.example.projetangular.Exceptions.EmailAlreadyExistsException;
import com.example.projetangular.entities.*;
import com.example.projetangular.repositories.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService {
    UtilisateurRepository utilisateurRepository;
    ReservationRepository reservationRepository;
    PasswordEncoder passwordEncoder;
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    FoyerRepository foyerRepository;
    UniversiteRepository universiteRepository;
    ConfirmationTokenRepository confirmationTokenRepository;

    JwtService jwtService;
    private final EmailService emailService;


    @Override
    public Utilisateur addUtilisateur(Utilisateur u) throws  EmailAlreadyExistsException {
        if (isEmailAlreadyExists(u.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        u.setRole(RoleUtilisateur.ETUDIANT);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setEtat("non confirmé");
        utilisateurRepository.save(u);

        // Générer un token de confirmation
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(generateConfirmationToken());
        confirmationToken.setExpiryDate(LocalDateTime.now().plusDays(1));

        // Associer le token à l'utilisateur
        confirmationToken.setUser(u);

        // Sauvegarder le token de confirmation dans la base de données
        confirmationTokenRepository.save(confirmationToken);

        // Associer le token à l'utilisateur
        u.setConfirmationToken(confirmationToken);

        // Sauvegarder l'utilisateur dans la base de données
        utilisateurRepository.save(u);

        // Construire le lien de confirmation
        String confirmationLink = "http://localhost:4200/front/confirm?token=" + confirmationToken.getToken();

        // Envoyer l'email de confirmation
        emailService.sendConfirmationEmail(u.getEmail(), confirmationLink);

        return utilisateurRepository.save(u);
    }

@Override
public Utilisateur login(String email, String password) throws UsernameNotFoundException, BadCredentialsException {
    Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);
    System.out.println("utilisateur"+optionalUtilisateur);

    if (optionalUtilisateur.isPresent()) {
        Utilisateur utilisateur = optionalUtilisateur.get();

        if (passwordEncoder.matches(password, utilisateur.getPassword())&& utilisateur.getEtat().equals("validé")) {
            String jwtToken = jwtService.generateToken(utilisateur);

            // Set the generated token to the Utilisateur object
            utilisateur.setToken(jwtToken);

            return utilisateurRepository.save(utilisateur);
        } else {
            throw new BadCredentialsException("Incorrect username or password");
        }
    } else {
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    @Override
    public List<Utilisateur> getEtudiantsByEtatNon(RoleUtilisateur role ,String etat) {
        return utilisateurRepository.findByRoleAndEtatIsNot(role,etat);
    }
    @Override
    public Utilisateur getUtilisateur(long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }


    @Override
    public boolean deleteUtilisateur(long idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);
        return true;
    }

    @Override
    public Utilisateur updateUtilisateur(long id,Utilisateur e) {
        Utilisateur u = utilisateurRepository.findById(id).orElse(null);
        u.setNomEt(e.getNomEt());
        u.setPrenomEt(e.getPrenomEt());
        u.setEmail(e.getEmail());
        u.setCin(e.getCin());
        u.setDateNaissance(e.getDateNaissance());
        return
                utilisateurRepository.save(u);
    }
    @Override
    public Utilisateur affecterReservationToEtudiant(long idEtudiant, String idReservation){
        Utilisateur etudiant = utilisateurRepository.findById(idEtudiant).orElse(null);
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        etudiant.getReservations().add(reservation);
        return utilisateurRepository.save(etudiant);
    }

    @Override
    public boolean isEmailAlreadyExists(String email) {
        return utilisateurRepository.findByEmail(email).isPresent();
    }

    @Override
    public Utilisateur changerEtatUtilisateur(long id,String etat) {
        Utilisateur u = utilisateurRepository.findById(id).orElse(null);
        u.setEtat(etat);
        return utilisateurRepository.save(u);
    }

    @Override
    public  Map<Universite, List<Utilisateur>> getEtudiantsByUniversite() {
        List<Universite>universites = universiteRepository.findAll();
        Map<Universite, List<Utilisateur>> etudiantsParUniversite = new HashMap<>();

        //List<Chambre> chambresParUni = new ArrayList<>();
        for(Universite u:universites){
            for(Bloc b :u.getFoyer().getBlocs()){
                for(Chambre c: b.getChambres()){
                    for(Reservation r :c.getReservations()){

                        etudiantsParUniversite.computeIfAbsent(u, k -> new ArrayList<>()).addAll(r.getEtudiants());

                    }
                }
            }

        }


        return etudiantsParUniversite;
    }

    private String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }
    @Override
    public boolean confirmUserAccount(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);

        if (confirmationToken != null && !confirmationToken.isExpired()) {
            // Marquer le compte comme confirmé
            Utilisateur utilisateur = confirmationToken.getUser();
            utilisateur.setEtat("confirmé");
            utilisateur.setConfirmationToken(null);
            utilisateurRepository.save(utilisateur);

            // Supprimer le token de confirmation après utilisation
            confirmationTokenRepository.delete(confirmationToken);

            return true;
        } else {
            return false;
        }
    }
}
