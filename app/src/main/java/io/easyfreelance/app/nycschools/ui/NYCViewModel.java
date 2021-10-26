package io.easyfreelance.app.nycschools.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.easyfreelance.app.nycschools.data.SatResultsRepository;
import io.easyfreelance.app.nycschools.data.SchoolsRepository;
import io.easyfreelance.app.nycschools.model.SatResult;
import io.easyfreelance.app.nycschools.model.School;

public class NYCViewModel extends ViewModel {
    private SchoolsRepository schoolRepository;
    private SatResultsRepository satRepository;

    public void init() {
        if (schoolRepository == null){
            schoolRepository = SchoolsRepository.getInstance();
        }

        if (satRepository == null){
            satRepository = SatResultsRepository.getInstance();
        }
    }

    public LiveData<List<School>> getSchools(int offset) {
        return schoolRepository.requestSchools(offset);
    }

    public LiveData<List<SatResult>> getSatResults(String dbn) {
        return satRepository.requestSatResults(dbn);
    }
}
