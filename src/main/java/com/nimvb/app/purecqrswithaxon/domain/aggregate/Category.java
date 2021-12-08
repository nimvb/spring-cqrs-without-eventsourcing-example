package com.nimvb.app.purecqrswithaxon.domain.aggregate;

import com.nimvb.app.purecqrswithaxon.command.CreateCategoryCommand;
import com.nimvb.app.purecqrswithaxon.command.DeleteCategoryCommand;
import com.nimvb.app.purecqrswithaxon.command.RenameCategoryCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Aggregate
@NoArgsConstructor
@Data
public class Category {

    @Id
    private String id;

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.ALWAYS)
    public void handle(CreateCategoryCommand command) {
        this.id = command.getId();
    }

    @CommandHandler
    public void handle(DeleteCategoryCommand command){
        AggregateLifecycle.markDeleted();
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(RenameCategoryCommand command) {
        this.id = command.getName();
    }
}
