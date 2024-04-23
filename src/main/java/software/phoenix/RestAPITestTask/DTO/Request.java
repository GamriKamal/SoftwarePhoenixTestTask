package software.phoenix.RestAPITestTask.DTO;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Request extends BaseEntity {
    private String content;
    private String nameOfProduct;
    private String quantity;
    private String deliveryAddress;
    private String phoneNumber;

    public Request(String content, String nameOfProduct, String quantity, String deliveryAddress, String phoneNumber) {
        this.content = content;
        this.nameOfProduct = nameOfProduct;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        super.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "content: " + content + "\n" +
                "nameOfProduct: " + nameOfProduct + "\n" +
                "quantity: " + quantity + "\n" +
                "deliveryAddress: " + deliveryAddress + "\n" +
                "phoneNumber: " + phoneNumber + "\n" +
                "created at: " + super.createdAt + "\n";
    }
}