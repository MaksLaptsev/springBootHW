package ru.clevertec.springboothw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.springboothw.model.Person;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Person,Long> {
    Optional<Person> getUserById(long id);
    Person saveAndFlush(Person person);
    boolean deleteById(long id);

}
