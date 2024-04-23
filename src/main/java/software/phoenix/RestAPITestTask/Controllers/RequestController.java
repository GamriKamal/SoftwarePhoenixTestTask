package software.phoenix.RestAPITestTask.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import software.phoenix.RestAPITestTask.DTO.Request;
import software.phoenix.RestAPITestTask.Services.RequestService;

import java.util.Optional;

@Controller
public class RequestController {
    private RequestService requestService;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/request/product")
    public ResponseEntity<String> proceedRequest(@RequestParam("content") String content,
                                                 @RequestParam("nameOfProduct") String nameOfProduct,
                                                 @RequestParam("quantity") String quantity,
                                                 @RequestParam("deliveryAddress") String deliveryAddress,
                                                 @RequestParam("phoneNumber") String phoneNumber) {

        Optional<Request> requestOptional;
        try {
            requestOptional = Optional.ofNullable(requestService.getRequestByProductName(nameOfProduct));
            if (requestOptional.isPresent()) {
                return ResponseEntity.status(400).body("You already have an request!");
            }
        } catch (Exception e) {
            Request request = new Request(content, nameOfProduct, quantity, deliveryAddress, phoneNumber);
            requestService.saveRequest(request);
            return ResponseEntity.status(201).body("Request has been completed successfully!!!");
        }
        return ResponseEntity.status(201).body("Request has been completed successfully!");
    }

    @GetMapping("/request/newsOfRequests")
    public ResponseEntity<String> getAllNews() {
        return ResponseEntity.status(200).body(requestService.getAllRequests().toString());
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(200).body("hello");
    }
}
