package io.easyfreelance.app.nycschools.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;
import java.util.Observable;

import io.easyfreelance.app.nycschools.R;
import io.easyfreelance.app.nycschools.model.SatResult;
import io.easyfreelance.app.nycschools.model.School;

public class SchoolActivity extends AppCompatActivity {
    NYCViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String dbn = getIntent().getStringExtra("dbn");
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        getSupportActionBar().setTitle(name);

        ((TextView)findViewById(R.id.school_description)).setText(description);

        viewModel = ViewModelProviders.of(this).get(NYCViewModel.class);
        viewModel.init();
        viewModel.getSatResults(dbn).observe(SchoolActivity.this, satResults -> {
            if(satResults.size() > 0) {
                SatResult sat = satResults.get(0);

                ((TextView)findViewById(R.id.num_of_sat_test_takers)).setText(String.valueOf(sat.numOfSatTestTakers));
                ((TextView)findViewById(R.id.sat_critical_reading_avg_score)).setText(String.valueOf(sat.satCriticalReadingAvgScore));
                ((TextView)findViewById(R.id.sat_math_avg_score)).setText(String.valueOf(sat.satMathAvgScore));
                ((TextView)findViewById(R.id.sat_writing_avg_score)).setText(String.valueOf(sat.satWritingAvgScore));
            } else {
                ((TextView)findViewById(R.id.num_of_sat_test_takers)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.sat_critical_reading_avg_score)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.sat_math_avg_score)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.sat_writing_avg_score)).setText(R.string.unknown);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
