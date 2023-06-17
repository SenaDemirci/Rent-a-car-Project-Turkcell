package kodlama.io.rentacar.api.contollers;

import jakarta.validation.Valid;
import kodlama.io.rentacar.business.abstracts.InvoiceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandReguest;
import kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetInvoiceResponse;
import kodlama.io.rentacar.business.dto.responses.get.brand.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.brand.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;


    @GetMapping
    public List<GetAllInvoicesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable int id){
        return service.getById(id);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest request){
        return service.add(request);

    }
    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@PathVariable int id,@Valid @RequestBody UpdateInvoiceRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }



}
