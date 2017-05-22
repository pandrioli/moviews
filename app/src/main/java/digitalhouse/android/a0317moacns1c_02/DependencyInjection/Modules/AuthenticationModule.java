package digitalhouse.android.a0317moacns1c_02.DependencyInjection.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.Authenticator;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticatorImpl;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

@Module
public class AuthenticationModule {

    @Singleton
    @Provides
    public Authenticator providerRequestToken(){
        return new AuthenticatorImpl();
    }
}
