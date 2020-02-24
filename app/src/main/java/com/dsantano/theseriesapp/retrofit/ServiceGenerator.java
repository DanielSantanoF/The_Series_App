package com.dsantano.theseriesapp.retrofit;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.common.MyApp;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = Constants.THE_MOVIEDB_BASIC_API_URL;

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = null;

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClientBuilder =
            new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {

        if (retrofit == null) {
            httpClientBuilder.addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();
                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter(Constants.QUERY_PARAM_API_KEY, Constants.API_KEY_THE_MOVIEDB)
                            .addQueryParameter(Constants.QUERY_PARAM_LANGUAGE, MyApp.getContext().getResources().getString(R.string.tmdb_api_language))
                            .build();
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            retrofitBuilder.client(httpClientBuilder.build());
            retrofit = retrofitBuilder.build();
        }
        return retrofit.create(serviceClass);
    }

}