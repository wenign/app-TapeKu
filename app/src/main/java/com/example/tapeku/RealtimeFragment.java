package com.example.tapeku;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapeku.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealtimeFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    DatabaseReference dataAlkohol;
    DatabaseReference dataSuhu;
    DatabaseReference dataLembab;
    DatabaseReference dataKondisi;
    DatabaseReference dataButton;

    Button buttonOn;
    Button buttonOff;
    Button buttonAlkohol;
    Button buttonSuhuLemb;

    TextView hasilAlkohol;
    TextView hasilSuhu;
    TextView hasilLembab;
    TextView hasilKondisi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_realtime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        dataAlkohol = databaseReference.child("DataTapeKu").child("DataAlkohol");
        dataSuhu = databaseReference.child("DataTapeKu").child("DataSuhu");
        dataLembab = databaseReference.child("DataTapeKu").child("DataLembab");
        dataKondisi = databaseReference.child("DataTapeKu").child("Kondisi");
        dataButton = databaseReference.child("DataTapeKu").child("Tombol");

        buttonOn = view.findViewById(R.id.buttonOn);
        buttonOff = view.findViewById(R.id.buttonOff);
        buttonAlkohol = view.findViewById(R.id.buttonAlkohol);
        buttonSuhuLemb = view.findViewById(R.id.buttonSuhu);

        hasilAlkohol = view.findViewById(R.id.textHasilAlokohol);
        hasilSuhu = view.findViewById(R.id.textHasilSuhu);
        hasilLembab = view.findViewById(R.id.textHasilLembab);
        hasilKondisi = view.findViewById(R.id.textKondisi);

        getdata();
    }

    private void getdata() {

        buttonAlkohol.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dataAlkohol.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            String cekAlkohol = snapshot.getValue().toString();
            hasilAlkohol.setText(cekAlkohol +" %");
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {
            Toast.makeText(getActivity(), "Please try again!", Toast.LENGTH_SHORT).show();
            }
            });
            }});

        buttonSuhuLemb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dataSuhu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            String cekSuhu = snapshot.getValue().toString();
            hasilSuhu.setText(cekSuhu +" °C");
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {
            Toast.makeText(getActivity(), "Please try again!", Toast.LENGTH_SHORT).show();
            }
            });

            dataLembab.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            String cekLembab = snapshot.getValue().toString();
            hasilLembab.setText(cekLembab +" %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getActivity(), "Please try again!", Toast.LENGTH_SHORT).show();
            }
            });

            }
            });

        dataKondisi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            String cekKondisi = snapshot.getValue().toString();
            hasilKondisi.setText("  " + cekKondisi + "  ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
            });

        buttonOn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v) {
        dataButton.setValue(1);
        Toast.makeText(getActivity(), "The lamp is on!", Toast.LENGTH_SHORT).show();
        }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v) {
        dataButton.setValue(0);
        Toast.makeText(getActivity(), "The lamp is off!", Toast.LENGTH_SHORT).show();
        }
        });

    }
}