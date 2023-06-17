package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateSaleResponse;

import java.util.List;

public interface SaleService {
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(int id);
    CreateSaleResponse add(CreateSaleRequest request);
    UpdateSaleResponse update(int id, UpdateSaleRequest request);
    void delete(int id);
}
