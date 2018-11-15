package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.jar.JarException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich = null;
     ImageView ingredientsIv ;
     TextView  also_known_tv , ingredients_tv , origin_tv , description_tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {

            Log.d("json " , "error close here");
            // Sandwich data unavailable
            closeOnError();
            return;
        }



        setTitle(sandwich.getMainName());
        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        ingredientsIv = (ImageView) findViewById(R.id.image_iv);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        also_known_tv = (TextView) findViewById(R.id.also_known_tv);
        String also_known_String = sandwich.getAlsoKnownAs().toString().replace("[" ,"").replace("]" , "");
        also_known_tv.setText(also_known_String);

        ingredients_tv = (TextView) findViewById(R.id.ingredients_tv);
            Typeface typeface = ResourcesCompat.getFont(this , R.font.allemademo);
            ingredients_tv.setTypeface(typeface);

        String ingredients_String = sandwich.getIngredients().toString().replace("[" ,"").replace("]" , "");
        ingredients_tv.setText(ingredients_String);

        origin_tv = (TextView) findViewById(R.id.origin_tv);
        String origin_String = sandwich.getPlaceOfOrigin().toString();
        origin_tv.setText(origin_String);

        description_tv = (TextView) findViewById(R.id.description_tv);
        Typeface typeface2 = ResourcesCompat.getFont(this , R.font.adleryproblockletter);
        description_tv.setTypeface(typeface2);
        String desc_String = sandwich.getDescription().toString();
        description_tv.setText(desc_String);


    }
}
