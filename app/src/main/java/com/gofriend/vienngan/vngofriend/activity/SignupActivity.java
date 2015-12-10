package com.gofriend.vienngan.vngofriend.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.gofriend.vienngan.vngofriend.R;

/**
 * Created by tmvien on 12/10/15.
 */
public class SignupActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
    }
}
