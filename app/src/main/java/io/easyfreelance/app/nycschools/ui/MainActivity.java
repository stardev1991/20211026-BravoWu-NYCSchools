package io.easyfreelance.app.nycschools.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.easyfreelance.app.nycschools.R;
import io.easyfreelance.app.nycschools.adapter.SchoolsAdapter;
import io.easyfreelance.app.nycschools.model.School;

public class MainActivity extends AppCompatActivity {
    ArrayList<School> schoolsList = new ArrayList<School>();
    SchoolsAdapter adapter;
    RecyclerView recyclerView;
    NYCViewModel viewModel;
    Boolean isRequestedData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);

        viewModel = ViewModelProviders.of(this).get(NYCViewModel.class);
        viewModel.init();
        requestMore();

        setupRecyclerView();
    }

    private void requestMore() {
        if (isRequestedData) return;

        isRequestedData = true;
        viewModel.getSchools(schoolsList.size()).observe(MainActivity.this, MainActivity.this::addSchoolsToList);
    }

    private void addSchoolsToList(List<School> schools) {
        if(schools.size() > 0) {
            schoolsList.addAll(schools);
            adapter.notifyDataSetChanged();
        }
        isRequestedData = false;
    }

    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new SchoolsAdapter(MainActivity.this, schoolsList, new SchoolsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(School school) {
                    Intent intent = new Intent(MainActivity.this, SchoolActivity.class);
                    intent.putExtra("dbn", school.dbn);
                    intent.putExtra("name", school.schoolName);
                    intent.putExtra("description", school.description);
                    startActivity(intent);
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    if (!recyclerView.canScrollVertically(1)) {
                        requestMore();
                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}