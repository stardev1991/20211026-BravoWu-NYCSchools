package io.easyfreelance.app.nycschools.api;

import java.util.List;

import io.easyfreelance.app.nycschools.model.SatResult;
import io.easyfreelance.app.nycschools.model.School;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYCService {
    @GET("resource/s3k6-pzi2.json")
    Call<List<School>> getSchools(@Query("$offset") int offset, @Query("$limit") int limit);

    @GET("resource/f9bf-2cp4.json")
    Call<List<SatResult>> getSatResults(@Query("dbn") String dbn);

    static NYCService create() {
        final String BASE_URL = "https://data.cityofnewyork.us/";

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NYCService.class);
    }
}
