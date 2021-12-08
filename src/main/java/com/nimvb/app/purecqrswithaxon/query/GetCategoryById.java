package com.nimvb.app.purecqrswithaxon.query;

import com.nimvb.app.purecqrswithaxon.query.common.Query;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetCategoryById implements Query {
    private final String id;
}
