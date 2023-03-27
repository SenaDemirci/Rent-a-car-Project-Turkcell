package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;
        /*
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = new ArrayList<>(); //başta boş bir liste olarak başlasın.

        for (Brand brand : brands) {
            response.add(new GetAllBrandsResponse(brand.getId(), brand.getName())); //consyructor içerisinde yaptık. Aslında set işelmi yaptık.
        }
        return response;
        */
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand, GetBrandResponse.class); //Dönüşmesini istediğim şey brand
        return response;
        /*
        checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = new GetBrandResponse();
        //GetBrandResponse response = new GetBrandResponse(brand.getId(), brand.getName()); //aşağıdaki 2 satır ile bu satır aynı şey demek
        response.setId(brand.getId());
        response.setName(brand.getName());
        return response;
        */
    }

//    @Override
//    public List<Brand> getAll() {
//        return repository.findAll(); //findAll() gibi Jpa Repo fonksiyonlarını kullanabiliriz.
//    }
//
//    @Override
//    public Brand getById(int id) {
//        checkIfBrandExists(id);
//        return repository.findById(id).orElseThrow(); //findById()nin null dönme ihtimali olduğu için hata verir, bu yüzden orElseThrow() veya get() çağrılır.
//        //return repository.findById(id).get(); //id ye göre bul varsa 1 tanesini getir.
//        //findById() kullanıyorsan getById() fonk Optional<Brand> döndürmeli veya yukarıdaki yöntemler izelenebilir.
//    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0); //id ye 0 yazmaz, olan id yazınca güncellemesin diye yeni id yi kendi oto oluşturur.
        repository.save(brand);
        CreateBrandResponse response =mapper.map(brand, CreateBrandResponse.class);
        //brand'i CreateBrandResponse'a çevirir.
        return response;
        /*
        Brand brand = new Brand();
        brand.setName(request.getName());
        repository.save(brand); //istek brand olduğu için requesti brande çevirdik.

        CreateBrandResponse response = new CreateBrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        return response;
        */
    }

//    @Override
//    public Brand add(Brand brand) {
//        return repository.save(brand);
//    }

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
