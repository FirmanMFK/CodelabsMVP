package id.gits.codelabsmvp.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import id.gits.codelabsmvp.R;
import id.gits.codelabsmvp.login.LoginActivity;
import id.gits.codelabsmvp.model.Register;
import id.gits.codelabsmvp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Firman on 10/6/2017.
 */

public class RegisterPresenter {
    private RegisterView mRegisterView;
    private Context mContext;

    public RegisterPresenter(RegisterView registerView, Context context) {
        this.mRegisterView = registerView;
        this.mContext = context;
    }

    public void requestRegister(final String firstname,
                                final String lastname,
                                final String username,
                                final String password,
                                final String birthdate,
                                final String gender,
                                final String phone) {

        ApiService.getInstance()
                .getApiInterface()
                .getRegister(firstname, lastname, username, password, birthdate, gender, phone)
                .enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        if (response.isSuccessful()) {
                            Log.d("onFailure_Register", username + "\n" + password);
                            mContext.startActivity(new Intent(mContext, LoginActivity.class));
                            mRegisterView.onSuccessResponse(mContext.getString(R.string.register_success));
                        } else {
                            mRegisterView.onErrorResponse(response.body().msgRegister);
                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Log.d("onFailure_Register", t.getMessage());
                    }
                });
    }
}

