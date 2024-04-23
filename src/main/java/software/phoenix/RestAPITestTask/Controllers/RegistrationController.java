package software.phoenix.RestAPITestTask.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import software.phoenix.RestAPITestTask.DTO.Person;
import software.phoenix.RestAPITestTask.Services.PersonService;

import java.util.Optional;

@Controller
public class RegistrationController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestParam("username") String userName, @RequestParam("password") String password,
                                               @RequestParam("passwordConfirm") String passwordConfirm,
                                               @RequestParam("fullName") String fullName, @RequestParam("avatarUrl") String avatarUrl) {
        if (!password.equals(passwordConfirm)) {
            return ResponseEntity.status(400).body("Passwords do not match!");
        }

        Optional<Person> existingPerson = Optional.ofNullable(personService.getPersonByName(userName));
        if (existingPerson.isPresent()) {
            return ResponseEntity.status(400).body("Username is already taken!");
        }

        try {
            personService.savePerson(new Person(userName, password, fullName, avatarUrl, "USER"));
            return ResponseEntity.status(201).body("Registration has been completed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
