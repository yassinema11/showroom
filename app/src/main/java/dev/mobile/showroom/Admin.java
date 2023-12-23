package dev.mobile.showroom;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigListener =
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.addfrag)
                    {
                        selectedFragment = new add();
                    }



                    if (item.getItemId() == R.id.update)
                    {
                        selectedFragment = new update();
                    }

                    if (item.getItemId() == R.id.delete)
                    {
                        selectedFragment = new deleteFragment();
                    }




                    if (selectedFragment != null)
                    {
                        String URL="http://192.168.1.51:80";
                        Bundle bundle = new Bundle();

                        bundle.putString("url", URL);

                        selectedFragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.addplaceholer, selectedFragment)
                                .commit();
                    }

                    return true;
                }
            };
}
