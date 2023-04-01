package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();//List<Brand> getAll();
    GetBrandResponse getById(int id); //Brand getById(int id);
    CreateBrandResponse add(CreateBrandRequest request); //Brand add(Brand brand);
    UpdateBrandResponse update(int id, UpdateBrandRequest request); //Brand update(int id, Brand brand);
    void delete(int id);


    /*
    List<Brand> getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    void delete(int id);
    */
}
