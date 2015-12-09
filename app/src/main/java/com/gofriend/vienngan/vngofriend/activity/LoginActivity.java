package com.gofriend.vienngan.vngofriend.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gofriend.vienngan.vngofriend.R;
import com.gofriend.vienngan.vngofriend.fragment.ProgressDialogFragment;
import com.gofriend.vienngan.vngofriend.utils.LanguageUtils;
import com.gofriend.vienngan.vngofriend.utils.ParameterNames;

/**
 * Created by tmvien on 12/9/15.
 */
public class LoginActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText edtUsername;
    private EditText edtPassword;
    private CheckBox ckSavePassword;
    private Button btnLogin;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String systemLanguage = Resources.getSystem().getConfiguration().locale.getLanguage();
        LanguageUtils.setLanguage(this, systemLanguage);

        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        ckSavePassword = (CheckBox) findViewById(R.id.ckSavePawwsord);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        displayInputData(getIntent());
    }

    private void displayInputData(Intent intent) {
        if (mPrefs == null) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        }

        String userLogin = mPrefs.getString(ParameterNames.USER_LOGIN, "");
        String userPass = mPrefs.getString(ParameterNames.USER_PASS, "");

        edtUsername.setText(userLogin);
        edtPassword.setText(userPass);
        ckSavePassword.setChecked(mPrefs.getBoolean(ParameterNames.SAVE_PASS, false));
    }

    private void saveInputData(String userLogin, String userPass, boolean savePassword) {
        boolean canSavePass = true;


    }

    @Override
    protected void onNewIntent(Intent intent) {
        displayInputData(intent);
        checkAutoRelogin();
        super.onNewIntent(intent);
    }

    private void checkAutoRelogin() {
        boolean loggedIn = mPrefs.getBoolean(ParameterNames.LOGGED_IN, false);
        String userLoginId = mPrefs.getString(ParameterNames.USER_LOGIN, null);
        boolean savePass = mPrefs.getBoolean(ParameterNames.SAVE_PASS, false);
        if (loggedIn
                && userLoginId != null
                && savePass
                && edtUsername.getText() != null
                && edtPassword.getText().length() != 0) {
            login(edtUsername.getText().toString(), edtPassword
                    .getText()
                    .toString(), true);
            return;
        }
    }

    private void login(String username, String password, boolean isAutoLogin) {
        Log.d(TAG, isAutoLogin ? "Auto login" : "Login manually");
        final boolean savePasswordChecked = ckSavePassword.isChecked();
//      InternalDataPipe.unregister(App.class);
        final ProgressDialogFragment dialog = new ProgressDialogFragment();
        dialog.setMessage(getString(R.string.ProgressTitle_Login));
    }

    @Override
    public void onClick(View v) {
        if (R.id.btnLogin == v.getId()) {
            login(edtUsername.getText().toString(), edtPassword
                    .getText()
                    .toString(), false);
        }
    }
}
