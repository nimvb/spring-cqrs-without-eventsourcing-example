package com.nimvb.app.purecqrswithaxon.controller;

import com.nimvb.app.purecqrswithaxon.domain.aggregate.Category;
import com.nimvb.app.purecqrswithaxon.request.CreateCategoryRequest;
import com.nimvb.app.purecqrswithaxon.request.UpdateCategoryRequest;
import com.nimvb.app.purecqrswithaxon.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public CompletableFuture<List<Category>> all(){
        return service.findAll().exceptionally(throwable -> new ArrayList<>());
    }

    @GetMapping("{name}")
    public CompletableFuture<ResponseEntity<Category>> find(@PathVariable("name") String name){
        final CompletableFuture<ResponseEntity<Category>> result = service.find(name).thenApply(ResponseEntity::ok);
        return result.exceptionally(throwable -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<?>> create(@RequestBody CreateCategoryRequest request){
        final CompletableFuture<ResponseEntity<?>> result = service.create(request).thenApply(id -> {
            final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{name}").buildAndExpand(id).toUri();
            return ResponseEntity.created(uri).build();
        });
        return result.exceptionally(throwable -> ResponseEntity.internalServerError().body(throwable.getMessage()));
    }

    @PutMapping
    public CompletableFuture<ResponseEntity<?>> update(@RequestBody UpdateCategoryRequest request){
        final CompletableFuture<ResponseEntity<?>> result = service.update(request.getName(), request.getTarget()).thenApply(s -> ResponseEntity.noContent().build());
        return result.exceptionally(throwable -> ResponseEntity.internalServerError().body(throwable.getMessage()));
    }

    @DeleteMapping("{name}")
    public CompletableFuture<ResponseEntity<?>> delete(@PathVariable("name") String name){
        final CompletableFuture<ResponseEntity<?>> result = service.delete(name).thenApply(s -> ResponseEntity.noContent().build());
        return result.exceptionally(throwable -> ResponseEntity.internalServerError().body(throwable.getMessage()));
    }
}
