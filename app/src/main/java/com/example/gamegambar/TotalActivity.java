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
    ArrayList<String> stock = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        point = findViewById(R.id.txtPoin);
        finish = findViewById(R.id.btnFinish);
        ulang = findViewById(R.id.btnUlang);


        Intent arr = getIntent();
        stock = arr.getStringArrayListExtra("total");


        callQuiz();

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
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });

    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;

    public void callQuiz() {

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(TotalActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<QuizModel> call3 = apiInterface.getGame();
        call3.enqueue(new Callback<QuizModel>() {
            @Override
            public void onResponse(Call<QuizModel> call, Response<QuizModel> response) {
                progressDialog.dismiss();
                QuizModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data != null) {


                    Integer poin = 0;
                    Integer nilai = 0;

                    for (int x = 0; x < data.getData().getSoalQuizAndroid().size(); x++) {

                        ArrayList<String> hope = new ArrayList<String>(3);
                        hope.add("4");
                        hope.add("1");
                        hope.add("1");

                        if (stock.get(x).equalsIgnoreCase(hope.get(x))) {
                            poin = Integer.parseInt(data.getData().getSoalQuizAndroid().get(x).getPoint());
                            nilai += poin;
                        }


                        point.setText(String.valueOf(nilai));

                    }

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TotalActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TotalActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<QuizModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

}

