package io.easyfreelance.app.nycschools.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.easyfreelance.app.nycschools.api.NYCService;
import io.easyfreelance.app.nycschools.model.School;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolsRepository {
    private NYCService service;
    private static SchoolsRepository repository;
    private final int NETWORK_PAGE_SIZE = 20;

    public static SchoolsRepository getInstance() {
        if (repository == null){
            repository = new SchoolsRepository();
        }
        return repository;
    }

    public SchoolsRepository(){
        service = NYCService.create();
    }

    public MutableLiveData<List<School>> requestSchools(int offset) {
        MutableLiveData<List<School>> data = new MutableLiveData<>();

        service.getSchools(offset, NETWORK_PAGE_SIZE).enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(@NonNull Call<List<School>> call, @NonNull Response<List<School>> response) {
                if (response.isSuccessful()){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<School>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
