package software.phoenix.RestAPITestTask.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.phoenix.RestAPITestTask.DTO.Person;
import software.phoenix.RestAPITestTask.Repositories.PersonRepository;

import java.io.InvalidObjectException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public Person getPersonById(long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no person by this id!");
        }
        Optional<Person> tempPerson = repository.findById(id);
        if (tempPerson.isPresent()) {
            return tempPerson.get();
        } else {
            throw new NullPointerException("Your Person is null!");
        }
    }

    public Person getPersonByName(String username) {
        Optional<Person> tempPerson = repository.findByUsername(username);
        return tempPerson.orElse(null);
    }

    public Person savePerson(Person person) throws InvalidObjectException {
        if (!PersonValidator.validateUsername(person.getUsername())) {
            throw new InvalidObjectException("Username is not correct!");
        }

        if (!PersonValidator.validatePassword(person.getPassword())) {
            throw new InvalidObjectException("Password is not correct!");
        }

        if (!PersonValidator.validateAvatarUrl(person.getAvatarUrl())) {
            throw new InvalidObjectException("Url of avatar is not correct!");
        }

        person.setCreatedAt(LocalDateTime.now());
        person.setPassword(PasswordEncoder.encryptPassword(person.getPassword()));

        Optional<Person> tempPerson = null;
        try {
            tempPerson = Optional.of(repository.save(person));
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return tempPerson.get();
    }

    public Person updatePersonById(long id, Person person) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no person by this id!");
        }

        Optional<Person> existingPerson = repository.findById(id);
        return getPerson(person, existingPerson);

    }

    private Person getPerson(Person person, Optional<Person> existingPerson) {
        if (existingPerson.isPresent()) {
            existingPerson.get().setUsername(person.getUsername());
            existingPerson.get().setPassword(PasswordEncoder.encryptPassword(person.getPassword()));
            existingPerson.get().setAvatarUrl(person.getAvatarUrl());
            existingPerson.get().setFullName(person.getFullName());
            existingPerson.get().setCreatedAt(LocalDateTime.now());

            return existingPerson.get();
        } else {
            throw new NullPointerException("Your Person is null!");
        }
    }

    public Person updatePersonByName(String name, Person person) {
        if (!repository.existsByUsername(name)) {
            throw new EntityNotFoundException("There is no person by this name!");
        }

        Optional<Person> existingPerson = repository.findByUsername(name);
        return getPerson(person, existingPerson);

    }

    public boolean deletePersonById(long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no person by this id!");
        }

        try {
            repository.deleteById(id);
            return !repository.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePersonByName(String name) {
        if (!repository.existsByUsername(name)) {
            throw new EntityNotFoundException("There is no person by this name!");
        }

        try {
            repository.deleteByUsername(name);
            boolean isDeleted = !repository.existsByUsername(name);
            return isDeleted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
