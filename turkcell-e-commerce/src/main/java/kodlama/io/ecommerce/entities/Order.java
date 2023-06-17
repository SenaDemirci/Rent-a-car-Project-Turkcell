package kodlama.io.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne
    private Product product;
    @ManyToOne
    @JoinColumn(name="sale_id")
    private Sale sale;
}
