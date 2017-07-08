package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import digitalhouse.android.a0317moacns1c_02.Helpers.Toaster;
import digitalhouse.android.a0317moacns1c_02.R;

public class WelcomeActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Toaster.getInstance().init(this);
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        starItemTabsActivity();
                    }

                    @Override
                    public void onCancel() {
                        Toaster.getInstance().toast("NO Loageado");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toaster.getInstance().toast("NO Loageado");
                    }
                });
    }

    private void starItemTabsActivity(){
        Intent intent = new Intent(this, ItemTabsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
