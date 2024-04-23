package software.phoenix.RestAPITestTask.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.phoenix.RestAPITestTask.DTO.Request;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByNameOfProduct(String productName);

    void deleteByNameOfProduct(String productName);

    boolean existsByNameOfProduct(String productName);

}
