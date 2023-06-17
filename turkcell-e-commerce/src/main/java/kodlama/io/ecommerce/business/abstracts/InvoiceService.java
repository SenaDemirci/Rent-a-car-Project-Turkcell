package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);
    void delete(int id);
}
