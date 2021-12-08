package com.nimvb.app.purecqrswithaxon.command.common;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class AbstractCommand<IdT> implements Command<IdT> {
    @TargetAggregateIdentifier
    protected final IdT id;

    protected AbstractCommand(IdT id) {
        this.id = id;
    }

    @Override
    public IdT getId() {
        return id;
    }
}
