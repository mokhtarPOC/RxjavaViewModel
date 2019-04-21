package com.example.rxjavaviewmodel.features.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rxjavaviewmodel.data.Repository;
import com.example.rxjavaviewmodel.entities.Category;
import com.example.rxjavaviewmodel.entities.CategoryResponse;
import com.example.rxjavaviewmodel.entities.Data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private Repository repository = new Repository();

    private CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    final LiveData<Boolean> loading = _loading;

    private MutableLiveData<List<Category>> _categories = new MutableLiveData<>();
    final LiveData<List<Category>> categories = _categories;


    public void getAllCategories() {
        _loading.postValue(true);
        disposables.add(
                repository.getAllCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                success -> {
                                    _loading.postValue(false);
                                    _categories.postValue(success.getData().getCategories());
                                }, error -> {
                                    _loading.postValue(false);
                                    //TODO: show error message
                                }
                        )
        );
    }

    public void getCategoryByName(String query) {
        _loading.postValue(true);
        disposables.add(
                repository.getAllCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(CategoryResponse::getData)
                        .map(Data::getCategories)
                        .flatMap(Observable::fromIterable)
                        .filter(category -> category.getTitle().toLowerCase().contains(query.toLowerCase()))
                        .toList()
                        .subscribe(
                                success -> {
                                    _loading.postValue(false);
                                    _categories.postValue(success);
                                }, error -> {
                                    _loading.postValue(false);
                                    //TODO: show error message
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }
}
