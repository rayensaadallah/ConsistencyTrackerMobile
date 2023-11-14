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
import tn.esprit.restauapp.entity.Restaurant;
import tn.esprit.restauapp.ui.activities.QrCodeActivity;

public class ListRestaurantAdapter extends RecyclerView.Adapter<ListRestaurantAdapter.ViewHolder> {
    private Context mContext;
    private List<Restaurant> restaurants;

    public ListRestaurantAdapter(Context mContext, List<Restaurant> restaurants) {
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

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_restaurant_user, parent, false);
        return new ListRestaurantAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Restaurant singleItem = restaurants.get(position);

        holder.nameresto.setText("Name :"+singleItem.getName());
        holder.lieuRes.setText("Adress :"+singleItem.getLieu());

        holder.imgres.setImageBitmap(BitmapFactory.decodeFile(singleItem.getImagePathResto()));
        holder.imgres.setVisibility(View.VISIBLE);
        holder.btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, QrCodeActivity.class);
                intent.putExtra("name",singleItem.getName());
                mContext.startActivity(intent);
            }
        });

        System.out.println(singleItem.getImagePathResto());







    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameresto, lieuRes;
        private Button btn_qr;
        private ImageView imgres;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgres = itemView.findViewById(R.id.imgResto);
            nameresto = itemView.findViewById(R.id.NameRest);
            lieuRes = itemView.findViewById(R.id.lieuRest);
            btn_qr = itemView.findViewById(R.id.btn_qr);


        }
    }

}

