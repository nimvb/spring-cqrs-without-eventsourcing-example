package com.nimvb.app.purecqrswithaxon.command;

import com.nimvb.app.purecqrswithaxon.command.common.AbstractCommand;
import lombok.Getter;

@Getter
public class UpdateCategoryCommand extends AbstractCommand<String> {
    private final String target;
    public UpdateCategoryCommand(String id, String target) {
        super(id);
        this.target = target;
    }
}
