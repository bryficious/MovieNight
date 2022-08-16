package sg.edu.rp.c346.id21012434.movienight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {


    Context parent_context;
    int layout_id;
    ArrayList<Movie> MovieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource , objects);
        parent_context = context;
        layout_id = resource;
        MovieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView IvRating = rowView.findViewById(R.id.imageViewRating);

        // Obtain the Android Version information based on the position
        Movie currentVersion = MovieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvSinger.setText(currentVersion.getGenre());
        tvYear.setText(currentVersion.getYear());
        if(currentVersion.getRating().equals("G")){
            IvRating.setImageResource(R.drawable.rating_g);
        }else if(currentVersion.getRating().equals("PG")){
            IvRating.setImageResource(R.drawable.rating_pg);
        }else if(currentVersion.getRating().equals("PG13")){
            IvRating.setImageResource(R.drawable.rating_pg13);
        }else if(currentVersion.getRating().equals("NC16")){
            IvRating.setImageResource(R.drawable.rating_nc16);
        }else if(currentVersion.getRating().equals("M18")){
            IvRating.setImageResource(R.drawable.rating_m18);
        }else if(currentVersion.getRating().equals("R21")){
            IvRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }

}
