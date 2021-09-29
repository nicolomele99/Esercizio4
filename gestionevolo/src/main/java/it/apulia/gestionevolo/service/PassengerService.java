package it.apulia.gestionevolo.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.apulia.gestionevolo.controllers.dto.FindTicketIdAndPassengerDto;
import it.apulia.gestionevolo.model.Passenger;
import it.apulia.gestionevolo.repository.PassengerRepository;
@Service
public class PassengerService {
    @Autowired
    private PassengerRepository repo;

    @PersistenceContext
    private EntityManager em;

    public List<Passenger> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<Long> findTicketId(String email) {
        List<Long> ticketsId = repo.findTicketId(email);
        return ticketsId;
    }

    public List<FindTicketIdAndPassengerDto> findTicketIdPassenger(String email) {
        List<FindTicketIdAndPassengerDto> tickets = repo.findTicketIdAndPassenger(email);
        return tickets;
    }

    public List<FindTicketIdAndPassengerDto> findTicketIdPassengerPaging(String email, Integer pageNo) {
        @SuppressWarnings("unchecked")
        List<FindTicketIdAndPassengerDto> tickets = em.createQuery(
                        "SELECT new com.voli.controllers.dto.FindTicketIdAndPassengerDto(t.ticketId,p.firstName,p.lastName,t.flightId,f.departure,f.destination,p.address,p.phoneNumber,f.price) FROM Passenger p INNER JOIN p.tickets t INNER JOIN t.flightId f WHERE p.email = :email")
                .setParameter("email", email)
                .setFirstResult(pageNo*10)
                .setMaxResults(10)
                .getResultList();
        return tickets;
    }

    public void save(Passenger passenger) {
        repo.save(passenger);
    }
}
