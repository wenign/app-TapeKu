package com.example.tapeku;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    public static final String TAG = "MyTag";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    DatabaseReference dataHistory;

    ArrayList<DatabaseHistory> list = new ArrayList<>();
    AdapterItem adapterItem;
    RecyclerView recyclerView;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        
        dataHistory = databaseReference.child("ProgressTapeKu");

        processdata();
    }

    private void processdata() {

        dataHistory.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) { showList(snapshot);
        }
        @Override
        public void onCancelled (@NonNull DatabaseError error) {
        }
        });
    }

    private void showList(DataSnapshot snapshot) {
        list.clear();
        for (DataSnapshot item : snapshot.getChildren()) {
        DatabaseHistory history = item.getValue(DatabaseHistory.class);
        list.add(history);
        }

        adapterItem = new AdapterItem(context, list);
        recyclerView.setAdapter(adapterItem);
    }
}