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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Report extends AppCompatActivity implements View.OnClickListener {

    EditText zcode1;

    TextView pname, bname;

    Button searchb1, importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        pname = findViewById(R.id.namesearch);
        bname = findViewById(R.id.birdname1);
        zcode1 = findViewById(R.id.zipcodesearch);

        searchb1 = findViewById(R.id.searchbutton);
        importance = findViewById(R.id.buttonAddImportance);

        searchb1.setOnClickListener(this);
        importance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");

        if (view == searchb1) {

            //integer is a string in my class, thus using an if function to check length

            int strln = zcode1.length();

            if (strln == 5) {

                String zipcodesearch1 = zcode1.getText().toString();
                myRef.orderByChild("zipcode").equalTo(zipcodesearch1).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Bird found = dataSnapshot.getValue(Bird.class);

                        String name = found.birdname;
                        String person = found.email;

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
            } else {
                Toast.makeText(Report.this, "Invalid Zipcode", Toast.LENGTH_SHORT).show();

            }
        }


        else if (view == importance) {

            String zipcodesearch1 = zcode1.getText().toString();

            myRef.orderByChild("zipcode").equalTo(zipcodesearch1).limitToLast(1).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    Bird found = dataSnapshot.getValue(Bird.class);

                    found.importance++;

                    myRef.child(key).child("importance").setValue(found.importance);

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
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.landing) {


            Intent landingintent = new Intent(Report.this, Landing.class);

            startActivity(landingintent);

        }

        else if (item.getItemId() == R.id.report) {

            Intent mainintent = new Intent(Report.this, MainActivity.class);

            startActivity(mainintent);
        }

        else if (item.getItemId() == R.id.search) {

        }

        else if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent logoutintent = new Intent(Report.this, Login.class);

            startActivity(logoutintent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
