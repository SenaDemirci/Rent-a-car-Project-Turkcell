package kodlama.io.rentacar.repository.abstracts;

import kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository {
    List<Brand> getAll();
}
