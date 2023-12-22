package dev.mobile.showroom;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import java.util.List;

public interface CarApi {
    @GET("showroom/List.php")
    Call<List<Car>> getCars();

    @FormUrlEncoded
    @POST("showroom/insert.php")
    Call<Car> insertCar(@Field("marque") String name,
                        @Field("model") String username,
                        @Field("prix") String password,
                        @Field("paiement") String email);

    @FormUrlEncoded
    @POST("showroom/update.php")
    Call<Car> updateCar(@Field("id") int id,
                        @Field("marque") String marque,
                        @Field("model") String model,
                        @Field("prix") String prix,
                        @Field("paiement") String paiement);

    @FormUrlEncoded
    @POST("showroom/delete.php")
    Call<Car> deleteUser(@Field("id") int id);

    @GET("showroom/CarDetails.php")
    Call<Car> getCarDetails(@Field("marque") String marque); // Assuming you have a method for fetching details by marque
}
