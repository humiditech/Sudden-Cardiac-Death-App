package com.example.scdapp.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scdapp.LoginActivity;
import com.example.scdapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    private TextView patientFullName,patientNickName,patientAddress,patientAge,patientDoctorName;
    private Button showQrButton,logoutButton;
    private Dialog qrDialog;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        if (container == null) return null;
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_profile, container, false);
        showQrButton = (Button) view.findViewById(R.id.show_qr_button);
        logoutButton = (Button) view.findViewById(R.id.logout_button);
        patientFullName = (TextView) view.findViewById(R.id.patient_name);
        patientNickName = (TextView) view.findViewById(R.id.patient_nickname);
        patientAddress = (TextView) view.findViewById(R.id.address);
        patientAge = (TextView) view.findViewById(R.id.age);
        patientDoctorName = (TextView) view.findViewById(R.id.doctor_name);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        qrDialog = new Dialog(getContext());

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                patientFullName.setText(value.getString("fName"));
                patientNickName.setText(value.getString("nName"));
                patientAddress.setText(value.getString("addr"));
                patientAge.setText(value.getString("age"));
                patientDoctorName.setText(value.getString("dName"));
            }
        });

        showQrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrDialog.show();
                qrDialog.setContentView(R.layout.popup_qr);
                qrDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
        return view;
    }

}