package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateInvoiceResponse;
import kodlama.io.ecommerce.business.rules.InvoiceBusinessRules;
import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final InvoiceBusinessRules rules;
    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> list=repository.findAll();
        List<GetAllInvoicesResponse> responses=list.stream()
                .map(invoice -> mapper.map(invoice,GetAllInvoicesResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfExistsById(id);
        Invoice invoice=repository.findById(id).orElseThrow();
        GetInvoiceResponse response=mapper.map(invoice,GetInvoiceResponse.class);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice=mapper.map(request,Invoice.class);
        invoice.setId(0);
        repository.save(invoice);
        CreateInvoiceResponse response=mapper.map(invoice,CreateInvoiceResponse.class);

        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfExistsById(id);
        Invoice invoice=mapper.map(request,Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        UpdateInvoiceResponse response=mapper.map(invoice,UpdateInvoiceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
         rules.checkIfExistsById(id);
         repository.deleteById(id);
    }
}
