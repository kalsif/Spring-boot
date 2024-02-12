package com.example.esercizio16.repositories;

import com.example.esercizio16.entities.Flight;
import com.example.esercizio16.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Long> {
    List<Flight> findAllFlights();


    Page<Flight> findAllPaged(Pageable pageable);

    List<Flight> findByStatus(Status status);

    @Query(value = "SELECT * FROM flight AS f WHERE f.status = :p1 OR f.status = :p2", nativeQuery = true)
    List<Flight> delayedOrCanceled(@Param("p1") String p1, @Param("p2") String p2);

    @Query(value = "SELECT * FROM flight AS f WHERE f.status = :p1 OR f.status = :p2", nativeQuery = true)
    List<Flight> onTimeOrDelayed(@Param("p1") String p1, @Param("p2") String p2);


}
