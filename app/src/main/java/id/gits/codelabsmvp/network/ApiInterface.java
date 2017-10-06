package id.gits.codelabsmvp.network;

import id.gits.codelabsmvp.model.Detail;
import id.gits.codelabsmvp.model.Login;
import id.gits.codelabsmvp.model.Register;
import id.gits.codelabsmvp.model.Wisata;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Firman on 10/6/2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("post/user/login")
    Call<Login> getLogin(@Field("username") String username,
                         @Field("password") String password);

    @FormUrlEncoded
    @POST("post/user/account")
    Call<Register> getRegister(@Field("first_name") String firstname,
                               @Field("last_name") String lastname,
                               @Field("username") String username,
                               @Field("password") String password,
                               @Field("bdate") String birthdate,
                               @Field("gender") String gender,
                               @Field("phone") String numberPhone);

    @GET("get/filter/dataalam")
    Call<Wisata> getTravelList(
            @Query("kategori") int id);

    @GET("get/detil/dataalam")
    Call<Detail> getDetail(
            @Query("itemid") int id);

}
