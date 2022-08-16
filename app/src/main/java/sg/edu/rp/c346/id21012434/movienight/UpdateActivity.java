package sg.edu.rp.c346.id21012434.movienight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    Button BtnUpdate,BtnDelete ,BtnCancel;
    EditText etContentgenre, etContenttitle, etContentYear;
    Spinner ratingSpinner;
    ArrayList<Movie> al;
    ArrayAdapter<Movie> aa;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        BtnUpdate = findViewById(R.id.BtnUpdate);
        BtnDelete = findViewById(R.id.BtnDelete);
        BtnCancel = findViewById(R.id.buttonCancel);

        etContentYear = findViewById(R.id.etContentYear);
        etContenttitle = findViewById(R.id.etContentSongtitle);
        etContentgenre = findViewById(R.id.etContentSinger);
        ratingSpinner = findViewById(R.id.rating_spinner_edit);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");
        String title = data.getTitle();
        String singer = data.getGenre();
        int year = data.getYear();
        String rating = data.getRating();
        etContenttitle.setText(title);
        etContentgenre.setText(singer);
        etContentYear.setText(year);

        if(rating.equals("G")){
            ratingSpinner.setSelection(0);
        }else if(rating.equals("PG")){
            ratingSpinner.setSelection(1);
        }else if(rating.equals("PG13")){
            ratingSpinner.setSelection(2);
        }else if(rating.equals("NC16")){
            ratingSpinner.setSelection(3);
        }else if(rating.equals("M18")){
            ratingSpinner.setSelection(4);
        }else if(rating.equals("R21")){
            ratingSpinner.setSelection(5);
        }else{
            ratingSpinner.setSelection(5);
        }

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etContenttitle.getText().toString();
                String genre = etContentgenre.getText().toString();
                String Year = etContentYear.getText().toString();
                String agelimit = "";
                if(ratingSpinner.getSelectedItemPosition() == 0){
                    agelimit = "G";
                }else if(ratingSpinner.getSelectedItemPosition() == 1){
                    agelimit = "PG";
                }else if(ratingSpinner.getSelectedItemPosition() == 2){
                    agelimit = "PG13";
                }else if(ratingSpinner.getSelectedItemPosition() == 3){
                    agelimit = "NC16";
                }else if(ratingSpinner.getSelectedItemPosition() == 4){
                    agelimit = "M18";
                }else if(ratingSpinner.getSelectedItemPosition() == 5){
                    agelimit = "R21";
                }
                DbHelper dbh = new DbHelper(UpdateActivity.this);
                if(title.isEmpty() || genre.isEmpty() || Year.isEmpty() || agelimit.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Please do not leave any blank",
                            Toast.LENGTH_SHORT).show();
                }else {
                    if(Integer.parseInt(Year) > 1900) {
                        data.setTitle(title);
                        data.setGenre(genre);
                        data.setYear(Year);
                        data.setRating(agelimit);
                        dbh.updateMovie(data);
                        dbh.close();
                        finish();
                    }else{
                        Toast.makeText(UpdateActivity.this, "Only accept movies from 1900 onwards",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create the Dialog Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(UpdateActivity.this);

                //Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete " + data.getTitle() +"?");
                myBuilder.setCancelable(false);


                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbHelper dbh = new DbHelper(UpdateActivity.this);
                        dbh.deleteMovie(data.getId());
                        finish();
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create the Dialog Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(UpdateActivity.this);

                //Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);


                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                myBuilder.setNegativeButton("Do Not Discard", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }
}