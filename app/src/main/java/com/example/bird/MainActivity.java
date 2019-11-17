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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText biname, pername, zipcode;
    Button searchb, submitb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        biname = findViewById(R.id.bname);
        pername = findViewById(R.id.pname);
        zipcode = findViewById(R.id.zcode);

        searchb = findViewById(R.id.gtsbutton);
        submitb = findViewById(R.id.submitbutton);

        searchb.setOnClickListener(this);
        submitb.setOnClickListener(this);


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

        if (view == searchb) {

            Intent searchintent = new Intent(this, Report.class);

            startActivity(searchintent);

        } else if (view == submitb) {

            String birdname = biname.getText().toString();

            String personname = pername.getText().toString();

            int zipcode1 = Integer.parseInt(zipcode.getText().toString());

            Bird bird1 = new Bird(birdname, zipcode1, personname);

            myRef.push().setValue(bird1);







        }
    }

}
