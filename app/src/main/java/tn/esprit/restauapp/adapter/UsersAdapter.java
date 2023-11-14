package tn.esprit.restauapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context mContext;
    private List<User> users;

    public UsersAdapter(Context mContext, List<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    public void notifyChange(List<User> users){
        this.users = users;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_users_admin, parent, false);
        return new UsersAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final User singleItem = users.get(position);

        holder.fname.setText("Name:"+" "+singleItem.getName());
        holder.lname.setText("Email"+" "+singleItem.getEmail());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase.getAppDatabase(mContext).userDao().delete(singleItem);
                UsersAdapter.this.notifyChange(
                        AppDataBase.getAppDatabase(mContext).userDao().getAll());
            }
        });

    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fname, lname;
        private Button btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.txtName);
            lname = itemView.findViewById(R.id.txtEmail);
            btn_delete = itemView.findViewById(R.id.btn_user_delete);

        }
    }

}
