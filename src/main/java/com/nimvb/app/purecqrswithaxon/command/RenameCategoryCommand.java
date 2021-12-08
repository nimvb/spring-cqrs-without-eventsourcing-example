package com.nimvb.app.purecqrswithaxon.command;

import com.nimvb.app.purecqrswithaxon.command.common.AbstractCommand;
import lombok.Getter;


@Getter
public class RenameCategoryCommand extends AbstractCommand<String> {
    private final String name;
    public RenameCategoryCommand(String id, String name) {
        super(id);
        this.name = name;
    }
}
