package digitalhouse.android.a0317moacns1c_02.Services;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.AuthenticationImpl;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Authentication.Session;

/**
 * Created by dh3 on 22/05/17.
 */

public class AuthenticationServiceImpl implements AuthenticationService {

    private RequestToken requestToken;
    private Session session;
    private TMDBClient tmdbClient;
    private static AuthenticationServiceImpl instance;

    public static AuthenticationServiceImpl getInstance(){
        if(instance == null){
            instance = new AuthenticationServiceImpl();
        }
        return instance;
    }

    protected AuthenticationServiceImpl(){
        tmdbClient = ServiceGenerator.createService(TMDBClient.class);
    }

    @Override
    public void logIn() {
        if(session==null)
        AuthenticationImpl
                .getInstance()
                .getSession(tmdbClient, new TMDBClient.APICallback() {
                    @Override
                    public void onSuccess(Object result) {
                        session = (Session) result;
                    }
                }, requestToken.getRequest_token());
    }

    public void logIn(final TMDBClient.APICallback callback) {
        if(session==null)
        AuthenticationImpl
                .getInstance()
                .getSession(tmdbClient, new TMDBClient.APICallback() {
                    @Override
                    public void onSuccess(Object result) {
                        session = (Session)result;
                        callback.onSuccess(null);
                    }
                }, requestToken.getRequest_token());
    }

    @Override
    public void obtainRequestToken(final TMDBClient.APICallback callback) {
         AuthenticationImpl.getInstance().getRequestToken(tmdbClient, new TMDBClient.APICallback() {
             @Override
             public void onSuccess(Object result) {
                 requestToken = (RequestToken) result;
                 callback.onSuccess(requestToken.getRequest_token());
             }
         });
    }

    @Override
    public boolean userIsLogged() {
        return session == null ? false : true;
    }
}
