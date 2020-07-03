package com.example.ferreteriaapp.api;

import com.example.ferreteriaapp.BuildConfig;
import com.example.ferreteriaapp.base.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.example.ferreteriaapp.util.AppConstants.CONNECT_TIMEOUT;
import static com.example.ferreteriaapp.util.AppConstants.READ_TIMEOUT;
import static com.example.ferreteriaapp.util.AppConstants.WRITE_TIMEOUT;

public class RetrofitInstance {

    private RetrofitInstance() {
        Timber.d("RetrofitInstance");
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor().
                        setLevel(BuildConfig.DEBUG ?
                                HttpLoggingInterceptor.Level.BODY
                                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request request = chain.request();
                            return chain.proceed(request);
                        })
                        .addInterceptor(httpLoggingInterceptor)
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                        .build();


        Retrofit provideRetrofitClient
                = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // Serialize Objects
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Set call to return {@link Observable}
                .build();
        return provideRetrofitClient.create(serviceClass);
    }
}
