package com.example.projetangular.contollers;
import com.example.projetangular.entities.Chambre;
import com.example.projetangular.entities.Reservation;
import com.example.projetangular.services.IChambreService;
import com.example.projetangular.services.IReservationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/reservation")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 4600)
public class ReservationController {
    @Autowired
    IReservationService reservationService;

    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation res){return reservationService.ajouterReservation(res);}

    @GetMapping("/getAllRes")
    public Iterable<Reservation>getAllRes(){
        return reservationService.getAllReservations();
    }


    @DeleteMapping("/deleteRes/{idReservation}")
    public void deleteReservation(@PathVariable Long idReservation){
        reservationService.supprimerReservationParIdReservation(idReservation);
    }

    @PutMapping("/updateRes")
    public Reservation updateRes (@RequestBody Reservation res){
        return reservationService.mettreAJourReservation(res);
    }
    
}
