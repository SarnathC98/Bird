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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Landing extends AppCompatActivity implements View.OnClickListener {


    TextView birdName;
    Button find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        //linking the two textviews, one does not need to be technically need to be linked because it is just a simple text bar
        birdName = findViewById(R.id.textViewBirdName);
        find = findViewById(R.id.buttonFind);

        find.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference("Birds");

        if (view == find) {

            myRef.orderByChild("importance").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    //extract birdname from reference
                    String bdname = dataSnapshot.getValue(Bird.class).birdname;

                    //set the textView
                    birdName.setText(bdname);



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


        }

        else if (item.getItemId() == R.id.report) {

            Intent mainintent = new Intent(Landing.this, MainActivity.class);

            startActivity(mainintent);
        }

        else if (item.getItemId() == R.id.search) {

            Intent searchintent = new Intent(Landing.this, Report.class);

            startActivity(searchintent);

        }

        else if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent logoutintent = new Intent(Landing.this, Login.class);

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
