package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product,Integer>{
    List<Product> findAllByStatusIsNot(Status status);

}
