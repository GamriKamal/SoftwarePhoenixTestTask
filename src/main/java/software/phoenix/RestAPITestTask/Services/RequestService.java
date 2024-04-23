package software.phoenix.RestAPITestTask.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.phoenix.RestAPITestTask.DTO.Request;
import software.phoenix.RestAPITestTask.Repositories.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private RequestRepository requestRepository;

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(long id) {
        if (!requestRepository.existsById(id)) {
            throw new EntityNotFoundException("There is no request by this id!");
        }
        Optional<Request> tempRequest = requestRepository.findById(id);
        if (tempRequest.isPresent()) {
            return tempRequest.get();
        } else {
            throw new NullPointerException("Your request is null!");
        }
    }

    public Request getRequestByProductName(String productName) {
        Optional<Request> tempRequest = requestRepository.findByNameOfProduct(productName);
        if (tempRequest.isPresent()) {
            return tempRequest.get();
        } else {
            throw new EntityNotFoundException("There is no request by this name!");
        }
    }

    public Request saveRequest(Request request) {
        request.setCreatedAt(LocalDateTime.now());
        Optional<Request> tempRequest = Optional.of(requestRepository.save(request));
        if (tempRequest.isPresent()) {
            return tempRequest.get();
        } else {
            throw new RuntimeException("An error has occurred. The user has not been saved");
        }
    }

    public Request updateRequestById(long id, Request request) {
        if (!requestRepository.existsById(id)) {
            throw new EntityNotFoundException("There is no request by this id!");
        }

        Optional<Request> existingRequest = requestRepository.findById(id);
        if (existingRequest.isPresent()) {
            existingRequest.get().setContent(request.getContent());
            existingRequest.get().setQuantity(request.getQuantity());
            existingRequest.get().setPhoneNumber(request.getPhoneNumber());
            existingRequest.get().setDeliveryAddress(request.getDeliveryAddress());
            existingRequest.get().setNameOfProduct(request.getNameOfProduct());
            existingRequest.get().setCreatedAt(LocalDateTime.now());

            return existingRequest.get();
        } else {
            throw new NullPointerException("Your Request is null!");
        }

    }

    public Request updateRequestByName(String productName, Request request) {
        if (!requestRepository.existsByNameOfProduct(productName)) {
            throw new EntityNotFoundException("There is no request by this product name!");
        }

        Optional<Request> existingRequest = requestRepository.findByNameOfProduct(productName);
        if (existingRequest.isPresent()) {
            existingRequest.get().setContent(request.getContent());
            existingRequest.get().setQuantity(request.getQuantity());
            existingRequest.get().setPhoneNumber(request.getPhoneNumber());
            existingRequest.get().setDeliveryAddress(request.getDeliveryAddress());
            existingRequest.get().setNameOfProduct(request.getNameOfProduct());
            existingRequest.get().setCreatedAt(LocalDateTime.now());

            return existingRequest.get();
        } else {
            throw new NullPointerException("Your Request is null!");
        }

    }

    public boolean deleteRequestById(long id) {
        if (!requestRepository.existsById(id)) {
            throw new EntityNotFoundException("There is no request by this id!");
        }

        try {
            requestRepository.deleteById(id);
            return !requestRepository.existsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRequestByName(String name) {
        if (!requestRepository.existsByNameOfProduct(name)) {
            throw new EntityNotFoundException("There is no request by this name!");
        }

        try {
            requestRepository.deleteByNameOfProduct(name);
            return !requestRepository.existsByNameOfProduct(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
