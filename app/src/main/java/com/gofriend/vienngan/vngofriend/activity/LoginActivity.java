package com.gofriend.vienngan.vngofriend.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gofriend.vienngan.vngofriend.R;
import com.gofriend.vienngan.vngofriend.fragment.ProgressDialogFragment;
import com.gofriend.vienngan.vngofriend.utils.LanguageUtils;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by tmvien on 12/9/15.
 */
public class LoginActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView edtSignup;

    private TextInputLayout inputLayoutUsername, inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        String systemLanguage = Resources.getSystem().getConfiguration().locale.getLanguage();
        LanguageUtils.setLanguage(this, systemLanguage);

        setContentView(R.layout.activity_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtSignup = (TextView) findViewById(R.id.txtSignup);

        inputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        edtUsername.addTextChangedListener(new MyTextWatcher(edtUsername));
        edtPassword.addTextChangedListener(new MyTextWatcher(edtPassword));

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        edtSignup.setOnClickListener(this);

    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    private void login(final String username, final String password, boolean isAutoLogin) {
        Log.d(TAG, isAutoLogin ? "Auto login" : "Login manually");
//      InternalDataPipe.unregister(App.class);
        if (!validateUsername()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        final ProgressDialogFragment dialog = new ProgressDialogFragment();
        dialog.setMessage(getString(R.string.ProgressTitle_Login));
        dialog.show(getSupportFragmentManager(), null);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                // Handle the response
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSignup:
                signup();
                break;
            case R.id.btnLogin:
                login(edtUsername.getText().toString(), edtPassword
                        .getText()
                        .toString(), false);
                break;
        }


    }

    private void signup() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edtUsername:
                    validateUsername();
                    break;
                case R.id.edtPassword:
                    validatePassword();
                    break;
            }
        }
    }

    private boolean validatePassword() {
        if (edtPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.error_input_password));
            requestFocus(edtPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateUsername() {
        if (edtUsername.getText().toString().trim().isEmpty()) {
            inputLayoutUsername.setError(getString(R.string.error_input_username));
            requestFocus(edtUsername);
            return false;
        } else {
            inputLayoutUsername.setErrorEnabled(false);
        }

        return true;
    }

//    private static boolean isValidEmail(String email) {
//        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
