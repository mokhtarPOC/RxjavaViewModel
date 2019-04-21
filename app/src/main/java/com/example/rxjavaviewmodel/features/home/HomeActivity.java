package com.example.rxjavaviewmodel.features.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rxjavaviewmodel.R;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;

public class HomeActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText editText;

    private HomeAdapter adapter;
    private HomeViewModel viewModel;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.editText);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);

        bindViewModel();

        bindInstantSearch();
    }

    private void bindViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        viewModel.loading.observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.categories.observe(this, categories -> adapter.updateList(categories));

        viewModel.getAllCategories();
    }

    private void bindInstantSearch() {
        disposables.add(
                RxTextView.textChangeEvents(editText)
                        .skipInitialValue()
                        .distinctUntilChanged()
                        .debounce(400, TimeUnit.MILLISECONDS)
                        .map(TextViewTextChangeEvent::getText)
                        .map(CharSequence::toString)
                        .subscribe(viewModel::getCategoryByName)
        );
    }

    @Override
    protected void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }
}
