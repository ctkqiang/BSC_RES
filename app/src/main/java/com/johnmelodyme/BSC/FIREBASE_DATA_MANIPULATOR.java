package com.johnmelodyme.BSC;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @DEVELOPER: JOHN MELODY MELISSA
 * @PROJECT_NAME : BLOOD SUGAR CONTROL REGISTRATION
 * @DATE_COMPLETED : 23/12/2019
 * @CLASS_NAME : FIREBASE_DATA_MANIPULATOR
 */

public class FIREBASE_DATA_MANIPULATOR {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<DATA> BSC_CLIENT = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<DATA>BSC_CLIENT, List<String>KEYS);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FIREBASE_DATA_MANIPULATOR(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BSC_CLIENT");
    }

    public void READ_DATA(final DataStatus ds){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BSC_CLIENT.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    DATA data = keyNode.getValue(DATA.class);
                    BSC_CLIENT.add(data);
                }
                ds.DataIsLoaded(BSC_CLIENT,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void ADD_DATA(DATA bsc_data, final DataStatus dataStatus){
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(BSC_CLIENT)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dataStatus.DataIsInserted();
                    }
                });
    }
}
