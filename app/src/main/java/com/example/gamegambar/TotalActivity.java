package com.example.gamegambar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamegambar.quiz.QuizModel;
import com.example.gamegambar.service.APIClient;
import com.example.gamegambar.service.APIInterfacesRest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalActivity extends AppCompatActivity {

    TextView point;
    Button finish, ulang;
    int stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        point = findViewById(R.id.txtPoin);
        finish = findViewById(R.id.btnFinish);
        ulang = findViewById(R.id.btnUlang);


        Intent arr = getIntent();
        stock = arr.getIntExtra("total",0);


        point.setText(String.valueOf(stock));


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TotalActivity.this, "TERIMA KASIH", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(TotalActivity.this, MainActivity.class);
               startActivity(intent);

            }
        });

    }



}

