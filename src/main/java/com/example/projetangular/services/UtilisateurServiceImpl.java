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
    JwtService jwtService;


    @Override
    public Utilisateur addUtilisateur(Utilisateur u) {
        if (isEmailAlreadyExists(u.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        u.setRole(RoleUtilisateur.ETUDIANT);
        u.setPassword(passwordEncoder.encode(u.getPassword()));

        return utilisateurRepository.save(u);
    }

@Override
public Utilisateur login(String email, String password) throws UsernameNotFoundException, BadCredentialsException {
    Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);

    if (optionalUtilisateur.isPresent()) {
        Utilisateur utilisateur = optionalUtilisateur.get();

        if (passwordEncoder.matches(password, utilisateur.getPassword())) {
            String jwtToken = jwtService.generateToken(utilisateur);

            // Set the generated token to the Utilisateur object
            utilisateur.setToken(jwtToken);

            return utilisateur;
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
    public Utilisateur getUtilisateur(long id) {
        return utilisateurRepository.findById(id).orElse(null);
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

}
