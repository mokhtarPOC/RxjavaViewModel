package com.example.rxjavaviewmodel.data.remote;

import com.example.rxjavaviewmodel.entities.CategoryResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ICategoryService {

    @GET("more")
    Observable<CategoryResponse> getAllCategories();
}
