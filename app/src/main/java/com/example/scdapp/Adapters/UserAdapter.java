package com.example.scdapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scdapp.Models.UsersModel;
import com.example.scdapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    Context context;
    List<UsersModel> usersList;

    public UserAdapter(Context context,List<UsersModel> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_of_users,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        UsersModel user = usersList.get(position);
        holder.nickname.setText(user.getnName());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView nickname;
        CircleImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.username_userfrag);
            imageView = itemView.findViewById(R.id.image_user_userfrag);
        }
    }
}
