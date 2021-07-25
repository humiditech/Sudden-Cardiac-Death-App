package com.example.scdapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scdapp.Adapters.UserAdapter;
import com.example.scdapp.Models.UsersModel;
import com.example.scdapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorListFragment extends Fragment {

    public DoctorListFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private List<UsersModel> usersList;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userId;
    private FirestoreRecyclerAdapter<UsersModel, UserViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doctor__list, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_doctors);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        displayUsers();

        return view;
    }

    private void displayUsers() {
        Query query = fStore.collection("users");
        FirestoreRecyclerOptions<UsersModel> options = new FirestoreRecyclerOptions.Builder<UsersModel>()
                .setQuery(query, UsersModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<UsersModel, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull UsersModel model) {

                if (model.getUid().equals(userId)) {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }else {
                    holder.setUserName(model.getnName());
                    holder.itemView.setVisibility(View.VISIBLE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }

            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_of_users, parent, false);
                return new UserViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        private View view;

        UserViewHolder(View itemView) {
            super(itemView);
            view = itemView;

        }

        void setUserName(String userName) {
            TextView tv = view.findViewById(R.id.username_userfrag);
            tv.setText(userName);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}