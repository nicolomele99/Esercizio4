package it.apulia.gestionevolo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import it.apulia.gestionevolo.controllers.dto.FindTicketIdAndPassengerDto;
import it.apulia.gestionevolo.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByEmail(String email);

    Passenger save(Passenger passenger);

    // @Query("SELECT possiede.ticket_id FROM possiede INNER JOIN passenger ON
    // possiede.passenger_id= passenger.passenger_id WHERE passenger.email =
    // :emailAddress")
    // Ticket findPassengerByTicketId(String emailAddress);

    // @Query("SELECT passenger.ticket_id FROM possiede ")
    // public List<Ticket> findJoinTicketId(@Param("email") String email );

    @Query(value = "SELECT t.ticketId FROM Passenger p INNER JOIN p.tickets t WHERE p.email = :email GROUP BY t.ticketId")
    List<Long> findTicketId(String email);

    @Query(value = "SELECT new com.voli.controllers.dto.FindTicketIdAndPassengerDto(t.ticketId,p.firstName,p.lastName,t.flightId,f.departure,f.destination,p.address,p.phoneNumber,f.price) FROM Passenger p INNER JOIN p.tickets t INNER JOIN t.flightId f WHERE p.email = :email ")
    List<FindTicketIdAndPassengerDto> findTicketIdAndPassenger(String email);
}
