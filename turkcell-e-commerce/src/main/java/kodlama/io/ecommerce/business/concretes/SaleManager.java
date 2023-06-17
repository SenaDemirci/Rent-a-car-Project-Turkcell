package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateInvoiceResponse;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateSaleResponse;
import kodlama.io.ecommerce.business.rules.SaleBusinessRules;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.Sale;
import kodlama.io.ecommerce.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final SaleBusinessRules rules;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;


    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales=repository.findAll();
        List<GetAllSalesResponse> responses=sales
                .stream()
                .map(sale -> mapper.map(sale, GetAllSalesResponse.class)).toList();

        return responses;
    }

    @Override
    public GetSaleResponse getById(int id) {
        rules.checkIfSaleExistsById(id);
        Sale sale=repository.findById(id).orElseThrow();
        GetSaleResponse response=mapper.map(sale,GetSaleResponse.class);

        return response;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
       StringBuffer productDescription=new StringBuffer();
       double totalPrice=0;
       List<Integer> productIds=request.getProductIds();
       for(Integer productId:productIds){
           rules.checkIfProductExistsByStatus(productId);
           rules.checkIfProductExistsByQuantity(productId);
           totalPrice+=productService.getById(productId).getUnitPrice();
           productDescription.append(productService.getById(productId).getName());

       }

        Sale sale=mapper.map(request,Sale.class);
        sale.setId(0);
        sale.setCreatedAt(LocalDateTime.now());
        sale.setTotalPrice(totalPrice);
        
        //payment created
        CreateSalePaymentRequest salePaymentRequest=new CreateSalePaymentRequest();
        mapper.map(request.getPaymentRequest(),salePaymentRequest);
        salePaymentRequest.setPrice(totalPrice);
        paymentService.processSalePayment(salePaymentRequest);

        //success sale
        repository.save(sale);
        CreateSaleResponse response=mapper.map(sale,CreateSaleResponse.class);

        //invoice created
        CreateInvoiceRequest invoiceRequest=new CreateInvoiceRequest();
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setSaleDate(sale.getCreatedAt());
        invoiceRequest.setTotalPrice(sale.getTotalPrice());
        invoiceRequest.setProductDescription(productDescription.toString());
        invoiceService.add(invoiceRequest);

        changeProductQuantity(productIds);

        return response;
    }
    @Override
    public UpdateSaleResponse update(int id, UpdateSaleRequest request) {
        rules.checkIfSaleExistsById(id);
        Sale sale=mapper.map(request,Sale.class);
        sale.setId(id);
        repository.save(sale);
        UpdateSaleResponse response=mapper.map(sale,UpdateSaleResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSaleExistsById(id);
        repository.deleteById(id);
    }
    private void changeProductQuantity(List<Integer> productIds) { //stock update
        for(Integer productId: productIds) {
            productService.changeQuantity(productId);
        }
    }

}
