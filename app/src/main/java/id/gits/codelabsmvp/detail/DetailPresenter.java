package id.gits.codelabsmvp.detail;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import id.gits.codelabsmvp.R;
import id.gits.codelabsmvp.model.Detail;
import id.gits.codelabsmvp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Firman on 10/6/2017.
 */

public class DetailPresenter {
    private DetailView detailView;
    private Context context;

    public DetailPresenter(DetailView detailView, Context context) {
        this.detailView = detailView;
        this.context = context;
    }

    public void getDetail(int itemId) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.please_wait));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        ApiService
                .getInstance()
                .getApiInterface()
                .getDetail(itemId)
                .enqueue(new Callback<Detail>() {
                    @Override
                    public void onResponse(Call<Detail> call, Response<Detail> response) {
                        boolean status = response.body().statusDetail;
                        Log.d("onResponse_Detail", "" + status);
                        if (status) {
                            dialog.dismiss();
                            detailView.onDetailResponse(response.body());
                        } else {
                            dialog.dismiss();
                            detailView.onErrorResponse(response.body().msgDetail);
                        }
                    }

                    @Override
                    public void onFailure(Call<Detail> call, Throwable t) {
                        Log.d("onFailure_Detail", t.getMessage());
                    }
                });
    }
}

