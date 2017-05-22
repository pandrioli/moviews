package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digitalhouse.android.a0317moacns1c_02.R;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonToLoginActivity) protected Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.buttonToLoginActivity)
    public void onClick(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
