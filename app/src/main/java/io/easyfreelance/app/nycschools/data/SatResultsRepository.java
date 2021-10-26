package io.easyfreelance.app.nycschools.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.easyfreelance.app.nycschools.api.NYCService;
import io.easyfreelance.app.nycschools.model.SatResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SatResultsRepository {
    private NYCService service;
    private static SatResultsRepository repository;

    public static SatResultsRepository getInstance() {
        if (repository == null){
            repository = new SatResultsRepository();
        }
        return repository;
    }

    public SatResultsRepository(){
        service = NYCService.create();
    }

    public MutableLiveData<List<SatResult>> requestSatResults(String dbn) {
        MutableLiveData<List<SatResult>> data = new MutableLiveData<>();

        service.getSatResults(dbn).enqueue(new Callback<List<SatResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<SatResult>> call, @NonNull Response<List<SatResult>> response) {
                if (response.isSuccessful()){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SatResult>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
