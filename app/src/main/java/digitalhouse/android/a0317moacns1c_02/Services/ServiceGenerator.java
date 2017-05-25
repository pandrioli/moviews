package digitalhouse.android.a0317moacns1c_02.Services;

import android.text.TextUtils;

import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.TokenInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

/*The ServiceGenerator class uses Retrofit’s Retrofit builder to create a new REST client with the given API base url (BASE_URL).
For example, GitHub’s API base url is located at https://api.github.com/
 and you must update the provided example url with your own url to call your API instead of GitHub’s.

The createService method takes a serviceClass, which is the annotated interface for API requests,
 as a parameter and creates a usable client from it. On the resulting client you'll be able to execute your network requests.*/
public class ServiceGenerator {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();

    public static <S> S createService(Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            TokenInterceptor interceptor =
                    new TokenInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }

}
