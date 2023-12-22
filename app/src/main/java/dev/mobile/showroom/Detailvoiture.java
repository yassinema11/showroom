package dev.mobile.showroom;

import static dev.mobile.showroom.Car.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class Detailvoiture extends AppCompatActivity {

    TextView marqv, modv, prixv, optionsv;
    Button btnTotal;

    // Assuming you have a CarApi and RetrofitClient set up
    private CarApi carApi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailvoiture);

        // Assuming you have a Retrofit instance created
        carApi = RetrofitClient.getClient().create(CarApi.class);

        btnTotal = findViewById(R.id.tot);
        
        marqv = findViewById(R.id.marque);
        modv = findViewById(R.id.modele);
        prixv = findViewById(R.id.price);
        optionsv = findViewById(R.id.options);

        Intent intent = getIntent();
        String marque = intent.getStringExtra("marque");

        // Use Retrofit to get car details from the database
        Call<Car> call = carApi.getCarDetails(marque); // Assuming you have a method in CarApi for fetching details by marque
        call.enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Response<Car> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Car car = response.body();
                    updateUI(car);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

        btnTotal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Car Car = null;
                // Use Retrofit to update the car details
                Call<Car> updateCall = carApi.updateCar(
                        Car.getId(), // Assuming you have access to the Car object
                        Car.getMarque(),
                        Car.getModel(),
                        Car.getPrix(),
                        Car.getPaiement()
                );

                updateCall.enqueue(new Callback<Car>() {
                    @Override
                    public void onResponse(Response<Car> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Toast.makeText(Detailvoiture.this, "Car details updated successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Detailvoiture.this, "Car details not updated", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(Detailvoiture.this, "Error", Toast.LENGTH_SHORT).show();

                    }


                });
            }
        });
    }

    // Helper method to update UI with car details
    private void updateUI(Car car) {
        // Assuming you have appropriate getters in the Car class
        marqv.setText("Marque: " + car.getMarque());
        modv.setText("Modèle: " + car.getModel());
        prixv.setText("Prix: " + car.getPrix());
        optionsv.setText("Options: " + car.getPaiement());
    }
}
