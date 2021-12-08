package com.nimvb.app.purecqrswithaxon.query.service;

import com.nimvb.app.purecqrswithaxon.domain.aggregate.Category;
import com.nimvb.app.purecqrswithaxon.query.GetAllCategoriesQuery;
import com.nimvb.app.purecqrswithaxon.query.GetCategoryById;
import com.nimvb.app.purecqrswithaxon.repository.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {

    private final CategoryQueryRepository repository;


    @QueryHandler
    public List<Category> on(GetAllCategoriesQuery query){
        return repository.findAll();
    }

    @QueryHandler
    public Category on(GetCategoryById query){
        return repository.findById(query.getId()).orElseThrow(RuntimeException::new);
    }

}
