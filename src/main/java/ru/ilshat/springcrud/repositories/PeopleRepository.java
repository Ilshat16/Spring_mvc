package ru.ilshat.springcrud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ilshat.springcrud.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
	Optional<Person> findByEmail(String email);
}
