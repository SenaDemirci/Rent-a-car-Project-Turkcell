package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/invoices")
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
    public UpdateInvoiceResponse update(@PathVariable int id, @Valid @RequestBody UpdateInvoiceRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }


}
