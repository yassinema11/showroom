package dev.mobile.showroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class Listevoitures extends AppCompatActivity {

    ListView listev;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listevoitures);

        listev = findViewById(R.id.liste);
        add = findViewById(R.id.add);

        // Create a Retrofit instance
        CarApi carApi = RetrofitClient.getClient().create(CarApi.class);

        // Make a network request to fetch car data
        Call<List<Car>> call = carApi.getCars();
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Response<List<Car>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Car> cars = response.body();

                    // Update the list view with the retrieved car data
                    ListAdapter listeAdapter = new ArrayAdapter<>(Listevoitures.this, android.R.layout.simple_list_item_1, cars);
                    listev.setAdapter(listeAdapter);
                } else {
                    // Handle error
                    Toast.makeText(Listevoitures.this, "Failed to fetch car data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Listevoitures.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for adding cars
                // This is where you can navigate to a new activity or fragment for adding cars
                // For simplicity, let's show a toast message
                Toast.makeText(Listevoitures.this, "Add button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        listev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Implement your logic for item click, e.g., navigate to detail activity
                Car selectedCar = (Car) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(Listevoitures.this, Detailvoiture.class);
                intent.putExtra("car", String.valueOf(selectedCar));
                startActivity(intent);
            }
        });
    }
}
