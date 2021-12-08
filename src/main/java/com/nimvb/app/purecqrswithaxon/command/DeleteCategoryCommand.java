package com.nimvb.app.purecqrswithaxon.command;

import com.nimvb.app.purecqrswithaxon.command.common.AbstractCommand;

public class DeleteCategoryCommand extends AbstractCommand<String> {
    public DeleteCategoryCommand(String id) {
        super(id);
    }
}
