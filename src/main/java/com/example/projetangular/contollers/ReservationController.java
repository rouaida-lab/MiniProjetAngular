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


@RequestMapping("api/chambres")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@AllArgsConstructor
public class ReservationController {
    @Autowired
    IReservationService reservationService;
    @GetMapping("/getAllRes")
    public Iterable<Reservation>getAllReservation(){
        return reservationService.getAllReservations();
    }
    @PostMapping("/addRes")
    public  Reservation addRes(@RequestBody Reservation res){

        return reservationService.ajouterReservation(res);
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
