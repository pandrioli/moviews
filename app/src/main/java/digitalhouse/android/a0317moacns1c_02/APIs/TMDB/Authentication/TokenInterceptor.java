package digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

public class TokenInterceptor implements Interceptor {

    private String token;

    public TokenInterceptor(String token){
        this.token = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain)
            throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("request_token", this.token)
                .build();

        Response response = chain.proceed(request);
        return response;
    }


}
