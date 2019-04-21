package com.example.rxjavaviewmodel.data.remote;

import com.example.rxjavaviewmodel.data.RetrofitClient;
import com.example.rxjavaviewmodel.entities.CategoryResponse;

import io.reactivex.Observable;

public class CategoryService {

    private final ICategoryService service;

    public CategoryService() {
        service = RetrofitClient.getClient().create(ICategoryService.class);
    }

    public Observable<CategoryResponse> getAllCategories() {
        return service.getAllCategories();
    }
}
