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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.apulia.gestionevolo.controllers.dto.FindFlightDto;
import it.apulia.gestionevolo.controllers.dto.FindTicketIdAndPassengerDto;
import it.apulia.gestionevolo.model.Flight;
import it.apulia.gestionevolo.service.FlightService;
import it.apulia.gestionevolo.service.PassengerService;
import it.apulia.gestionevolo.service.TicketService;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/newFlight")
    public String registration(Model model) {
        model.addAttribute("flightForm", new Flight());
        return "newFlight";
    }

    @PostMapping("/newFlight")
    public String registration(Model model, @Valid @ModelAttribute("flightForm") Flight flightForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newFlight";
        }

        flightService.save(flightForm);
        return "redirect:/flight";
    }

    @RequestMapping("/flight/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_flight");
        Flight flight = flightService.get(id);
        mav.addObject("flight", flight);
        return mav;
    }

    @PostMapping(value = "/saveFlight")
    public String saveProduct(@ModelAttribute("flight") Flight flight) {
        flightService.save(flight);
        return "redirect:/flight";
    }

    @RequestMapping("/flight/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        flightService.delete(id);
        return "redirect:/flight";
    }

    @PostMapping("/searchFlight")
    public String SearchFlight(@RequestBody FindFlightDto destination, Model model) {
        String destination2 = destination.getDestination();
        destination2 = "%" + destination2 + "%";
        List<Flight> flights = flightService.findFlight(destination2);

        List<Long> listSeatsAvaiable = listSeatsAvaiable(flights);
        model.addAttribute("listSeatsAvaiable", listSeatsAvaiable);
        model.addAttribute("listFlights", flights);
        model.addAttribute("destination", destination2);
        return "findFlight";
    }

    @RequestMapping("/ticketDetails")
    public String viewHomePageticketDetails(Model model, @AuthenticationPrincipal UserDetails currentUser,
                                            @RequestParam(defaultValue = "0") Integer pageNo) throws ParseException {

        String email = currentUser.getUsername();

        List<FindTicketIdAndPassengerDto> list = passengerService.findTicketIdPassenger(email);
        Integer size = (list.size() / 10);
        if (list.size() % 10 == 0) {
            size = (list.size() / 10) - 1;
        }

        List<FindTicketIdAndPassengerDto> results = passengerService.findTicketIdPassengerPaging(email, pageNo);

        model.addAttribute("passengers", results);
        model.addAttribute("size", size);
        // model.addAttribute("ticketsId", ticketsId);
        return "ticketDetails";
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
