package tn.esprit.restauapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.ui.activities.UpdateRestaurantActivity;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.Restaurant;

public class RestaurantAdapter  extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private Context mContext;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context mContext, List<Restaurant> restaurants) {
        this.mContext = mContext;
        this.restaurants = restaurants;
    }

    public void notifyChange(List<Restaurant> restaurants){
        this.restaurants = restaurants;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_restaurant_aded_admin, parent, false);
        return new RestaurantAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Restaurant singleItem = restaurants.get(position);

        holder.nameresto.setText("Name :"+singleItem.getName());
        holder.lieuRes.setText("Adress :"+singleItem.getLieu());

        holder.imgres.setImageBitmap(BitmapFactory.decodeFile(singleItem.getImagePathResto()));
        holder.imgres.setVisibility(View.VISIBLE);


        System.out.println(singleItem.getImagePathResto());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase.getAppDatabase(mContext).restaurantDao().delete(singleItem);
                RestaurantAdapter.this.notifyChange(
                        AppDataBase.getAppDatabase(mContext).restaurantDao().getAll());
                notifyDataSetChanged();
            }
        });

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(mContext, UpdateRestaurantActivity.class);
                System.out.println(singleItem.getUid());

                intent.putExtra("name",singleItem.getName());
                intent.putExtra("lieu",singleItem.getLieu());
                intent.putExtra("id",singleItem.getUid());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameresto, lieuRes;
        private Button btn_delete,btn_edit;
        private ImageView imgres;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgres = itemView.findViewById(R.id.img);
            nameresto = itemView.findViewById(R.id.txtres);
            lieuRes = itemView.findViewById(R.id.txtlieu);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit= itemView.findViewById(R.id.btn_edit);

        }
    }

}

