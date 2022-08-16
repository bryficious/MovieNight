package sg.edu.rp.c346.id21012434.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnShow, btnRetrieve;
    TextView tvDBContent;
    EditText etGenre, etMovieTitle, etYear;
    Spinner rating;
    ArrayList<Movie> al;
    ArrayAdapter<Movie> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.BtnInsert);
        btnShow = findViewById(R.id.BtnShow);

        etYear = findViewById(R.id.etContentYear);
        etMovieTitle = findViewById(R.id.etContentSongtitle);
        etGenre = findViewById(R.id.etContentSinger);
        rating = (Spinner)findViewById(R.id.rating_spinner);
        //initialize the variables with UI here

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etMovieTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String Year = etYear.getText().toString();
                String agelimit = "";
                if(rating.getSelectedItemPosition() == 0){
                    agelimit = "G";
                }else if(rating.getSelectedItemPosition() == 1){
                    agelimit = "PG";
                }else if(rating.getSelectedItemPosition() == 2){
                    agelimit = "PG13";
                }else if(rating.getSelectedItemPosition() == 3){
                    agelimit = "NC16";
                }else if(rating.getSelectedItemPosition() == 4){
                    agelimit = "M18";
                }else if(rating.getSelectedItemPosition() == 5){
                    agelimit = "R21";
                }

                if(title.isEmpty() || genre.isEmpty() || Year.isEmpty() || agelimit.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please do not leave any blank",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if(Integer.parseInt(Year) > 1900){
                        DbHelper dbh = new DbHelper(MainActivity.this);
                        long inserted_id = dbh.insertMovie(title,genre,Year,agelimit);

                        if (inserted_id != -1) {
                            Toast.makeText(MainActivity.this, "Insert successful",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Only accept movies from 1900 onwards",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        List.class);
                startActivity(i);
            }
        });


    }


}