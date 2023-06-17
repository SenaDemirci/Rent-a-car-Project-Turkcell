package com.kodlamaio.filterservice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.dto.responses.GetFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;
import com.kodlamaio.filterservice.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
   private final FilterRepository repository;
   private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
     var filters=repository.findAll();
     var response=filters.stream()
             .map(filter -> mapper.forResponse().map(filter,GetAllFiltersResponse.class))
             .collect(Collectors.toList());

       return response;
    }

    @Override
    public GetFilterResponse getById(String id) {
     var filter=repository.findById(id).orElseThrow();
     var response=mapper.forResponse().map(filter,GetFilterResponse.class);

     return response;
    }

    @Override
    public void add(Filter filter) {
       filter.setId(null);
       repository.save(filter);
    }


    @Override
    public void deleteByCarId(UUID carId) {
        repository.deleteByCarId(carId);
    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
        repository.deleteAllByBrandId(brandId);

    }

    @Override
    public void deleteAllByModelId(UUID modelId) {
         repository.deleteAllByModelId(modelId);
    }

    @Override
    public Filter getByCarId(UUID carId) {
        var filter=repository.getByCarId(carId);
        return filter;
    }
}
