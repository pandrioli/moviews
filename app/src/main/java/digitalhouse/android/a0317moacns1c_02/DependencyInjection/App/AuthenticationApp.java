package digitalhouse.android.a0317moacns1c_02.DependencyInjection.App;

import android.app.Application;

import digitalhouse.android.a0317moacns1c_02.DependencyInjection.Components.AuthenticationComponent;
import digitalhouse.android.a0317moacns1c_02.DependencyInjection.Components.DaggerAuthenticationComponent;
import digitalhouse.android.a0317moacns1c_02.DependencyInjection.Modules.AuthenticationModule;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */

public class AuthenticationApp extends Application {

    private AuthenticationComponent authenticationComponent;

    @Override
    public void onCreate(){
        super.onCreate();         //Dagger AuthenticationComponent .builder() . authenticationModule(
        authenticationComponent = DaggerAuthenticationComponent.builder().authenticationModule(new AuthenticationModule()).build();
    }

    public AuthenticationComponent getAuthenticationComponent(){
        return authenticationComponent;
    }
}
