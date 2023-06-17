package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.request.create.CreateCategoryRequest;

import kodlama.io.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import kodlama.io.ecommerce.business.rules.CategoryBusinessRules;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryBusinessRules rules;
    private final ModelMapper mapper;
    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> list=repository.findAll();
        List<GetAllCategoriesResponse> responses=list.stream().
                map(category ->  mapper.map(category, GetAllCategoriesResponse.class)).
                collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetCategoryResponse getById(int id) {
        rules.checkIfCategoryExistsById(id);
        Category category=repository.findById(id).orElseThrow();
        GetCategoryResponse response=mapper.map(category,GetCategoryResponse.class);
        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        rules.checkIfCategoryExistsByName(request.getName());
        Category category=mapper.map(request,Category.class);
        category.setId(0);
        Category createdCategory=repository.save(category);
        CreateCategoryResponse response=mapper.map(createdCategory,CreateCategoryResponse.class);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(int id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExistsById(id);
        Category category=mapper.map(request,Category.class);
        category.setId(id);
        Category updateCategory=repository.save(category);
        UpdateCategoryResponse response=mapper.map(updateCategory,UpdateCategoryResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfCategoryExistsById(id);
        repository.deleteById(id);
    }



}
