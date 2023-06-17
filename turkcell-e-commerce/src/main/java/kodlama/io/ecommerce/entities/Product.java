package kodlama.io.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kodlama.io.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;


    @JsonManagedReference
    @ManyToMany( mappedBy = "products",
            fetch=FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Category> categories=new HashSet<>();


}
