package software.phoenix.RestAPITestTask.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import software.phoenix.RestAPITestTask.DTO.CustomUserDetails;
import software.phoenix.RestAPITestTask.DTO.Person;
import software.phoenix.RestAPITestTask.Repositories.PersonRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personOptional = personRepository.findByUsername(username);
        if (!personOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(personOptional.get());
    }
}


