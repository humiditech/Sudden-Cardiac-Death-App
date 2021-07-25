package com.example.scdapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scdapp.ChatActivity;
import com.example.scdapp.Models.UsersModel;
import com.example.scdapp.R;
import com.example.scdapp.fragments.ChatFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    Context context;
    List<UsersModel> usersList;
    String friendId;

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
        friendId = user.getUid();
        holder.nickname.setText(user.getnName());

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nickname;
        CircleImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.username_userfrag);
            imageView = itemView.findViewById(R.id.image_user_userfrag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            UsersModel user = usersList.get(getAdapterPosition());
            friendId = user.getUid();
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("friendId",friendId);
            Toast.makeText(context, friendId, Toast.LENGTH_SHORT).show();
            context.startActivity(intent);
        }
    }

}
