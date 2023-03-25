package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private final BrandRepository repository;

    @Override
    public List<Brand> getAll() {
        return repository.findAll(); //findAll() gibi Jpa Repo fonksiyonlarını kullanabiliriz.
    }

    @Override
    public Brand getById(int id) {
        checkIfBrandExists(id);
        return repository.findById(id).orElseThrow(); //findById()nin null dönme ihtimali olduğu için hata verir, bu yüzden orElseThrow() veya get() çağrılır.
        //return repository.findById(id).get(); //id ye göre bul varsa 1 tanesini getir.
        //findById() kullanıyorsan getById() fonk Optional<Brand> döndürmeli veya yukarıdaki yöntemler izelenebilir.
    }

    @Override
    public Brand add(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        checkIfBrandExists(id);
        brand.setId(id);
        return repository.save(brand); //save() nesneyi yeni oluştururken değiştirmek istenen id atanır.
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }

    private void checkIfBrandExists(int id) {
        if(!repository.existsById(id)) throw new IllegalArgumentException("Bu marka yok!");
    }


    /*
    private final BrandRepository repository;

    public BrandManager(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Brand> getAll() {
        if (repository.getAll().size() == 0) throw new RuntimeException("Marka bulunamadı");
        return repository.getAll();
    }

    @Override
    public Brand getById(int id) {
        return repository.getById(id);
    }

    @Override
    public Brand add(Brand brand) {
        return repository.add(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        return repository.update(id, brand);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
    */
}
