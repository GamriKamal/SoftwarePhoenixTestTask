package software.phoenix.RestAPITestTask.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.phoenix.RestAPITestTask.DTO.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

}
