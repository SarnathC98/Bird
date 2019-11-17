package com.example.bird;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Report extends AppCompatActivity implements View.OnClickListener {

    EditText zcode1;

    TextView pname, bname;

    Button searchb1, report1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        pname = findViewById(R.id.namesearch);
        bname = findViewById(R.id.birdname1);
        zcode1 = findViewById(R.id.zipcodesearch);

        searchb1 = findViewById(R.id.searchbutton);
        report1 = findViewById(R.id.reportbutton);

        searchb1.setOnClickListener(this);
        report1.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.report) {

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);

        }

        else if (item.getItemId() == R.id.search);
        {

            Intent intent = new Intent(this, Report.class);

            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");

        if (view == searchb1) {

            int zipcodesearch1 = Integer.parseInt(zcode1.getText().toString());

            myRef.orderByChild("zipcode").equalTo(zipcodesearch1).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Bird found = dataSnapshot.getValue(Bird.class);

                    String name = found.birdname;
                    String person = found.personame;

                    bname.setText(name);
                    pname.setText(person);



                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        else if (view == report1) {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
    }
}
