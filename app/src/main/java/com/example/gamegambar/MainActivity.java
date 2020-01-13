package com.example.gamegambar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamegambar.quiz.QuizModel;
import com.example.gamegambar.quiz.SoalQuizAndroid;
import com.example.gamegambar.service.APIClient;
import com.example.gamegambar.service.APIInterfacesRest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtPertanyaan;
    ImageView imgPertanyaan;
    RadioGroup radioGroup;
    Button ba,bi,bu,be;
    int x = 0;
    int totalSemua =0;

    List<String> jawaban;



    List<SoalQuizAndroid> listQuiz ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPertanyaan = findViewById(R.id.txtPertanyaan);
        imgPertanyaan = findViewById(R.id.imageView);
        ba = findViewById(R.id.btnA);
        bi = findViewById(R.id.btnB);
        bu = findViewById(R.id.btnC);
        be = findViewById(R.id.btnD);

        ba.setId(Integer.parseInt("1"));
        bi.setId(Integer.parseInt("2"));
        bu.setId(Integer.parseInt("3"));
        be.setId(Integer.parseInt("4"));
        radioGroup = findViewById(R.id.radioGroup);
        callQuiz();
        ba.setOnClickListener(this);
        bi.setOnClickListener(this);
        bu.setOnClickListener(this);
        be.setOnClickListener(this);


    }



    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callQuiz(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<QuizModel> call3 = apiInterface.getGame();
        call3.enqueue(new Callback<QuizModel>() {
            @Override
            public void onResponse(Call<QuizModel> call, Response<QuizModel> response) {
                progressDialog.dismiss();
                QuizModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {

                    jawaban = new ArrayList<String>();
                    listQuiz = data.getData().getSoalQuizAndroid();

                    runGame();

                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<QuizModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });



    }



    public void runGame(){

        if (x<listQuiz.size()){


            if (listQuiz.get(x).getJenisPertanyaan().equalsIgnoreCase("gambar")){

                txtPertanyaan.setText(listQuiz.get(x).getPertanyaan());
                String postImage =  listQuiz.get(x).getGambar();
                Picasso.get().load(postImage).into(imgPertanyaan);
                ba.setText(listQuiz.get(x).getA());
                bi.setText(listQuiz.get(x).getB());
                bu.setText(listQuiz.get(x).getC());
                be.setText(listQuiz.get(x).getD());
            } else {
                txtPertanyaan.setText(listQuiz.get(x).getPertanyaan());
                String postImage =  listQuiz.get(x).getGambar();
                Picasso.get().load(postImage).into(imgPertanyaan);
                ba.setText(listQuiz.get(x).getA());
                bi.setText(listQuiz.get(x).getB());
                bu.setText(listQuiz.get(x).getC());
                be.setText(listQuiz.get(x).getD());
            }



        }


    }

    @Override
    public void onClick(View v) {
        x++;

       switch (v.getId()){
           case 1 : jawaban.add("a");
                    break;
           case 2 : jawaban.add("b");
               break;
           case 3 : jawaban.add("c");
               break;
           case 4 : jawaban.add("d");
               break;



       }






        runGame();
        if (x == listQuiz.size()) {

            totalSemua += totalNilai();


            Intent i = new Intent(MainActivity.this, TotalActivity.class);

            i.putExtra("total",totalSemua);

            startActivity(i);
            finish();
        }


    }



    public int totalNilai (){

        int total =0 ;

        for (int i =0 ; i < listQuiz.size();i++){

            if(listQuiz.get(i).getJawaban().equalsIgnoreCase(jawaban.get(i))){

                total += Integer.parseInt(listQuiz.get(i).getPoint());


            }


        }


        return total;
    }


}
