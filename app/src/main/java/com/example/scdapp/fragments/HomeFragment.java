package com.example.scdapp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scdapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.HashMap;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty   public constructor
    }

    private TextView patientNickName;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userId;
    private FirebaseUser fUser;
    private Button relayButton;
    private int relayState = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (container == null) return null;
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);
        patientNickName = (TextView) view.findViewById(R.id.patient_home_name);
        relayButton = (Button) view.findViewById(R.id.relay_button);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                patientNickName.setText(value.getString("nName"));
            }
        });

        relayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relayState == 0)
                {
                    relayState = 1;
                    sendRelayCommand(relayState);
                }
                else{
                    relayState = 0;
                    sendRelayCommand(relayState);
                }
            }
        });
        return view;
    }

    private void sendRelayCommand(final int relayState)
    {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Relay");
        reference.child("relayState").setValue(relayState);
    }
}