package com.nimvb.app.purecqrswithaxon.service;

import com.nimvb.app.purecqrswithaxon.command.CreateCategoryCommand;
import com.nimvb.app.purecqrswithaxon.command.DeleteCategoryCommand;
import com.nimvb.app.purecqrswithaxon.command.UpdateCategoryCommand;
import com.nimvb.app.purecqrswithaxon.domain.aggregate.Category;
import com.nimvb.app.purecqrswithaxon.query.GetAllCategoriesQuery;
import com.nimvb.app.purecqrswithaxon.query.GetCategoryById;
import com.nimvb.app.purecqrswithaxon.query.service.CategoryQueryService;
import com.nimvb.app.purecqrswithaxon.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryQueryService queryService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;
    private final Repository<Category> categoryRepository;

    public CompletableFuture<List<Category>> findAll() {
        return queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(Category.class));
    }

    public CompletableFuture<Category> find(String name) {
        return queryGateway.query(new GetCategoryById(name), ResponseTypes.instanceOf(Category.class));
    }

    public CompletableFuture<String> create(CreateCategoryRequest request) {
        return commandGateway.send(new CreateCategoryCommand(request.getName()));
    }

    public CompletableFuture<String> delete(String name){
        return commandGateway.send(new DeleteCategoryCommand(name));
    }

    public CompletableFuture<String> update(String name,String target){
        return commandGateway.send(new UpdateCategoryCommand(name,target));
    }
}
