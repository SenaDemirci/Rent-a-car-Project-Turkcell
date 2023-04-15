package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper; //ModelMapper framework adı

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        //<Model> tipindeki değişkeni response tipine dünüştürüyorum
        List<GetAllModelsResponse> response = models.stream().map(model -> mapper.map(model, GetAllModelsResponse.class)).toList();
        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExistsById(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model, GetModelResponse.class);
        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        checkIfModelExistsByName(request.getName());
        Model model = mapper.map(request, Model.class);
        model.setId(0); //0 create anlamına gelir. Yeni bir id oluşturması için 0 yaptık.
        Model createdCar = repository.save(model);
        CreateModelResponse response = mapper.map(createdCar, CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        Model updatedModel = repository.save(model);
        UpdateModelResponse response = mapper.map(updatedModel, UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    private void checkIfModelExistsById(int id) {
        if (!repository.existsById(id)) { //false dönerse (id yoksa)
            throw new RuntimeException("Böyle bir model mevcut değil!");
        }
    }

    private void checkIfModelExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Böyle bir model sistemde zaten var!");
        }
    }

}
