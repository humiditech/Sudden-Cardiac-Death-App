package com.example.scdapp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scdapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

public class ChatFragment extends Fragment implements View.OnClickListener{


    public ChatFragment() {
        // Required empty public constructor
    }

    private TextView patientNickName,doctorName;
    private ImageView doctorImage;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        if (container == null) return null;
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_chat,container,false);
        patientNickName = (TextView) view.findViewById(R.id.patient_chat_name);
        doctorName = (TextView) view.findViewById(R.id.doctor_chat_name);
        doctorImage = (ImageView) view.findViewById(R.id.doctor_image_chat);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                patientNickName.setText(value.getString("nName"));
                doctorName.setText(value.getString("dName"));
            }
        });

        doctorImage.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        Fragment fragment = null;
        switch (view.getId())
        {
            case R.id.doctor_image_chat:
                fragment = new DoctorListFragment();
                replaceFragment(fragment);
                break;
            default:
                break;
        }
    }

    public void replaceFragment(Fragment someFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}