package com.example.bird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.actions.ItemListIntents;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText biname, pername, zipcode;
    Button submitb;

    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        biname = findViewById(R.id.bname);
        pername = findViewById(R.id.pname);
        zipcode = findViewById(R.id.zcode);

        submitb = findViewById(R.id.submitbutton);

        submitb.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");

       if (view == submitb) {

            String birdname = biname.getText().toString();

            String personname = pername.getText().toString();

            int importance = 0;

            String zipcode1 = zipcode.getText().toString();

            String email = mauth.getInstance().getCurrentUser().getEmail().toString();

           Bird bird1 = new Bird(birdname, zipcode1, personname, email, importance);

           myRef.push().setValue(bird1);

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.landing) {


            Intent landingintent = new Intent(MainActivity.this, Landing.class);

            startActivity(landingintent);

        }


       else if (item.getItemId() == R.id.report) {

        }

        else if (item.getItemId() == R.id.search) {

            Intent mainintent = new Intent(MainActivity.this, Report.class);

            startActivity(mainintent);

        }

        else if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent logoutintent = new Intent(MainActivity.this, Login.class);

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
