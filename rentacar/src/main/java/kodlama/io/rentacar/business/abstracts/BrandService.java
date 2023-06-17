package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.CreateBrandReguest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.brand.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.brand.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
  List<GetAllBrandsResponse>  getAll();
  GetBrandResponse getById(int id);
  CreateBrandResponse add(CreateBrandReguest request);
  UpdateBrandResponse update(int id, UpdateBrandRequest reguest);
  void delete(int id);

}
