package com.nimvb.app.purecqrswithaxon.command;

import com.nimvb.app.purecqrswithaxon.command.common.AbstractCommand;

public class CreateCategoryCommand extends AbstractCommand<String> {
    public CreateCategoryCommand(String id) {
        super(id);
    }
}
