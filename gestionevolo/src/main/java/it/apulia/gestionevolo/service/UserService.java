package it.apulia.gestionevolo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.apulia.gestionevolo.controllers.dto.UserRegistrationDto;
import it.apulia.gestionevolo.model.User;
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
