package com.nimvb.app.purecqrswithaxon.command.service;

import com.nimvb.app.purecqrswithaxon.command.CreateCategoryCommand;
import com.nimvb.app.purecqrswithaxon.command.DeleteCategoryCommand;
import com.nimvb.app.purecqrswithaxon.command.UpdateCategoryCommand;
import com.nimvb.app.purecqrswithaxon.domain.aggregate.Category;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryExternalCommandHandler {

    private final Repository<Category> repository;
    private final CommandGateway commandGateway;


    public CategoryExternalCommandHandler(@Lazy Repository<Category> repository, @Lazy CommandGateway commandGateway) {
        this.repository = repository;
        this.commandGateway = commandGateway;
    }


    @CommandHandler
    @Transactional
    public void handle(UpdateCategoryCommand command){
        final Aggregate<Category> aggregate = repository.load(command.getId());
        aggregate.invoke(category -> {
            category.handle(new DeleteCategoryCommand(command.getId()));
            return aggregate;
        }).execute(category -> {
            commandGateway.sendAndWait(new CreateCategoryCommand(command.getTarget()));
        });
    }

}
