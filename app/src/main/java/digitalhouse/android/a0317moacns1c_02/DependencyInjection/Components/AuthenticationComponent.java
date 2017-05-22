package digitalhouse.android.a0317moacns1c_02.DependencyInjection.Components;

import javax.inject.Singleton;

import dagger.Component;
import digitalhouse.android.a0317moacns1c_02.Activities.LoginActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.WebClientActivity;
import digitalhouse.android.a0317moacns1c_02.DependencyInjection.Modules.AuthenticationModule;

/**
 * Created by Gregorio Martin on 21/5/2017.
 */
@Singleton
@Component(
        modules = {
                AuthenticationModule.class
        }
)

public interface AuthenticationComponent {
        void inject(WebClientActivity webClientActivity);
        void inject(LoginActivity loginActivity);
}
