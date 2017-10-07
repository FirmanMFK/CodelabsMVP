package id.gits.codelabsmvp.login;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import id.gits.codelabsmvp.R;

import id.gits.codelabsmvp.model.Login;
import id.gits.codelabsmvp.network.ApiService;
import id.gits.codelabsmvp.utilities.SessionManager;
import id.gits.codelabsmvp.wisata.WisataActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Firman on 10/6/2017.
 */
public class LoginPresenter {

    private LoginView loginView;
    private Activity activity;
    private SessionManager sessionManager;

    public LoginPresenter(LoginView loginView, Activity activity) {
        this.loginView = loginView;
        this.activity = activity;
    }

    public void requestLogin(final String username,
                             String password) {

        sessionManager = new SessionManager(activity);

        final String passwordWithMD5 = convertPassMD5(password);

        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setMessage(activity.getString(R.string.please_wait));
        dialog.show();

        
        ApiService
                .getInstance()
                .getApiInterface()
                .getLogin(username, passwordWithMD5)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            String idUser = response.body().loginData.get(0)
                                    .idUserLogin;
                            Log.d("Debug_idUser", idUser);
                            sessionManager.setIdUser(idUser);
                            sessionManager.createSessionByUsername(username);
                            activity.startActivity(new Intent(activity,
                                    WisataActivity.class));
                            activity.finish();
                        } else {
                            dialog.dismiss();
                            loginView.onLoginError(response.body().msgLogin);
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        t.getMessage();
                    }
                });
    }

    public static String convertPassMD5(String pass) {
        String password = null;
        MessageDigest msgDigest;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, msgDigest.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }
}

