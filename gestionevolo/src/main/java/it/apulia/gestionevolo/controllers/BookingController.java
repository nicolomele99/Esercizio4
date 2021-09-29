package it.apulia.gestionevolo.controllers;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.apulia.gestionevolo.model.Flight;
import it.apulia.gestionevolo.model.Passenger;
import it.apulia.gestionevolo.model.Ticket;
import it.apulia.gestionevolo.model.User;
import it.apulia.gestionevolo.service.FlightService;
import it.apulia.gestionevolo.service.PassengerService;
import it.apulia.gestionevolo.service.TicketService;
import it.apulia.gestionevolo.service.UserService;

@Controller

public class BookingController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private UserService userService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/flight")
    public String viewHomePage(Model model) throws ParseException {
        List<Flight> listFlights = flightService.listAll();

        List<Long> listSeatsAvaiable = listSeatsAvaiable(listFlights);
        model.addAttribute("listFlights", listFlights);
        model.addAttribute("passenger", new Passenger());
        model.addAttribute("tickets", new Ticket());
        model.addAttribute("listSeatsAvaiable", listSeatsAvaiable);
        return "flight";
    }

    @PostMapping("/booking")
    public String SavePassenger1(Model model,@Valid @ModelAttribute("passenger") Passenger passenger, BindingResult bindingResult,
                                 @Valid @ModelAttribute("tickets") Ticket tickets, @AuthenticationPrincipal UserDetails currentUser) {

        String[] arrayFirstName = passenger.getFirstName().split(",");
        String[] arrayLastName = passenger.getLastName().split(",");
        String[] arrayAddress = passenger.getAddress().split(",");
        String[] arrayPhoneNumber = passenger.getPhoneNumber().split(",");
        List<Passenger> pass = new ArrayList();
        Long idFlight = tickets.getFlightId().getFlightId();
        // find a flight record by id
        Flight record = flightService.get(idFlight);
        Long seats = ticketService.findNumberReservedSeats(idFlight);

        if ((arrayFirstName.length) <= record.getSeats() - seats) {
            if ((passenger.getFirstName().isEmpty()) || (passenger.getLastName().isEmpty())) {
                return "Dati vuoti";
            } else {
                for (int i = 0; i < arrayFirstName.length; i++) {
                    pass.add(new Passenger());
                }
                // save on database
                flightService.save(record);

                String email = currentUser.getUsername();
                User userId = new User();
                userId.setId(userService.findByEmail(email).getId());
                userId.setEmail(userService.findByEmail(email).getEmail());

                for (int i = 0; i < pass.size(); i++) {
                    if (arrayFirstName[i].isEmpty() || arrayLastName[i].isEmpty()) {
                        return "";
                    } else {
                        pass.get(i).setFirstName(arrayFirstName[i]);
                        pass.get(i).setLastName(arrayLastName[i]);
                        pass.get(i).setEmail(email);
                        pass.get(i).setAddress(arrayAddress[i]);
                        pass.get(i).setPhoneNumber(arrayPhoneNumber[i]);
                        pass.get(i).addTicket(tickets);
                        pass.get(i).setUserId(userId);
                        passengerService.save(pass.get(i));
                    }
                }
                ticketService.save(tickets);
                // find sum price of this tickets
                Long price = ticketService.findPriceTickets(tickets.getTicketId());
                model.addAttribute("price", price);
                model.addAttribute("totalPrice", price * pass.size());
                model.addAttribute("passengers", pass);
                model.addAttribute("ticketId", tickets.getTicketId());
                model.addAttribute("flightId", tickets.getFlightId().getFlightId());
                return "bookingDetails";
            }

        } else if( bindingResult.hasErrors()) {
            return "newFlight";
        }else {
            return "Biglietti non disponibili!";
        }
    }

    public List<Long> listSeatsAvaiable(List<Flight> listFlights) {
        List<Long> listSeatsAvaiable = new ArrayList();
        for (Flight flight : listFlights) {
            Long seats = ticketService.findNumberReservedSeats(flight.getFlightId());
            Long seatsAvaiable = flight.getSeats() - seats;
            listSeatsAvaiable.add(seatsAvaiable);
        }
        return listSeatsAvaiable;
    }

}
