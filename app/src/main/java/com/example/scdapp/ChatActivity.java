package com.example.scdapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.scdapp.Adapters.UserAdapter;
import com.example.scdapp.Models.UsersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private String friendId;
    private CircleImageView imageViewOnToolbar;
    private TextView usernameOnToolbar;
    private Toolbar toolbar;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        toolbar = findViewById(R.id.toolbar_message);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageViewOnToolbar = findViewById(R.id.profile_image_toolbar_message);
        usernameOnToolbar = findViewById(R.id.username_ontoolbar_message);
//
//        friendId = getIntent().getStringExtra("friendId");
//        fAuth = FirebaseAuth.getInstance();
//        userId = fAuth.getCurrentUser().getUid();
//
//        DocumentReference reference = fStore.collection("users").document(friendId);
//        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                UsersModel user = value.toObject(UsersModel.class);
//                usernameOnToolbar.setText(user.getnName());
//            }
//        });



    }

}