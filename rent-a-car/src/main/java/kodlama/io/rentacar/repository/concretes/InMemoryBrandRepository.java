package kodlama.io.rentacar.repository.concretes;

import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Bellekte her defasında nesne oluşup silineceği için (geçici db) class ismine inMemory ekledik.
@Repository
public class InMemoryBrandRepository implements BrandRepository {
    List<Brand> brands;

    public InMemoryBrandRepository() {
        brands = new ArrayList<>(); //new List diyemeyiz çünkü List bir interface (interfaceler newlenemez)
        brands.add(new Brand(1,"mercedes"));
        brands.add(new Brand(2,"BMW"));
    }

    @Override
    public List<Brand> getAll() {
        return brands;
    }

    @Override
    public Brand getById(int id) {
        return brands.get(id-1);
    }

    @Override
    public Brand add(Brand brand) {
        brands.add(brand);
        return brand;
    }

    @Override
    public Brand update(int id, Brand brand) {
        return brands.set(id-1, brand);
    }

    @Override
    public void delete(int id) {
        brands.remove(id-1);
    }
}
