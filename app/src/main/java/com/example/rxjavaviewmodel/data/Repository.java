package com.example.rxjavaviewmodel.data;

import com.example.rxjavaviewmodel.data.remote.CategoryService;
import com.example.rxjavaviewmodel.entities.CategoryResponse;

import io.reactivex.Observable;

public class Repository {

    private final CategoryService categoryService;

    public Repository() {
        categoryService = new CategoryService();
    }

    public Observable<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
