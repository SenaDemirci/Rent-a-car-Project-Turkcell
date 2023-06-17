package kodlama.io.ecommerce.repository;

import kodlama.io.ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Integer> {
}
