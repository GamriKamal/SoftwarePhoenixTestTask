package software.phoenix.RestAPITestTask.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import software.phoenix.RestAPITestTask.DTO.Person;
import software.phoenix.RestAPITestTask.Services.PasswordEncoder;
import software.phoenix.RestAPITestTask.Services.PersonService;

import java.util.Optional;

@Controller
public class AuthenticationController {
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestParam("username") String userName, @RequestParam("password") String password) {
        Optional<Person> tempPerson = Optional.ofNullable(personService.getPersonByName(userName));

        if (tempPerson.isEmpty()) {
            return ResponseEntity.status(404).body("Error! Person not found!");
        } else {
            boolean status = PasswordEncoder.checkPassword(password, tempPerson.get().getPassword());
            if (status) {
                Authentication auth = new UsernamePasswordAuthenticationToken(userName, password);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return ResponseEntity.status(200).body("Successfully authenticated!");
            } else {
                return ResponseEntity.status(400).body("Error! Password not correct!");
            }
        }
    }
}