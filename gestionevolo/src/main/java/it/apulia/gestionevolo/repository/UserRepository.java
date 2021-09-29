package it.apulia.gestionevolo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.apulia.gestionevolo.controllers.dto.UserRegistrationDto;
import it.apulia.gestionevolo.model.User;

@Repository
@Transactional
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User save(UserRegistrationDto registration);
}
