package com.example.projetangular.Repositories;

import com.example.projetangular.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,String> {
    List<Reservation> findByAnneeUniversitaireBetween(Date startDate, Date endDate);
    List<Reservation>findByAnneeUniversitaireBefore(Date date);
    List<Reservation>findByAnneeUniversitaireBetweenAndEstValide(Date startDate, Date endDate,Boolean estValide);
    //List<Reservation>findByEstValide(Boolean estValide);


}
