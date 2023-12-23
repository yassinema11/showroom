/* package dev.mobile.showroom;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>
{
    List<Car> listecar;
    Context context;

    public CarAdapter(Context context,List<Car> listecar)
    {
        this.listecar = listecar;
        this.context = context;
    }


    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater. from (parent.getContext()).inflate(R.layout.recycleviewitem, parent, false);
        CarViewHolder carViewHolder = new CarViewHolder(view);
        return carViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, @SuppressLint("RecyclerView") int position)
    {

        Car car = listecar.get (position);
        holder.id.setText (String.valueOf(car.getId()));
        holder.name.setText (car.getMarque());
        holder.username.setText (car.getModel());
        holder.password.setText (car.getPrix());
        holder.email.setText (car.getPaiement());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, listecar.get(position).getMarque()+" "+listecar.get(position).getModel(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.listecar.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder
    {
        TextView id, name, username, password, email;
        public CarViewHolder (View itemView)
        {
            super (itemView);
            id = itemView.findViewById(R.id.id);
            name =itemView.findViewById(R.id.txtnom);
            username = itemView.findViewById(R.id.txtuser);
            password= itemView.findViewById(R.id.txtpass);
            email = itemView.findViewById(R.id.txtemail);
        }
    }

}*/
