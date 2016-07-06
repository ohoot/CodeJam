package com.example.joo.codejam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.joo.codejam.models.UserAccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String ACCESS_TOKEN_KEY = "access_token_key";

    @BindView(R.id.text_test) TextView textTest;

    UserAccessToken userAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        userAccessToken = (UserAccessToken) intent.getSerializableExtra(ACCESS_TOKEN_KEY);

        textTest.setText(userAccessToken.toString());

    }
}
