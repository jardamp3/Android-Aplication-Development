package fi.jamk.picassodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);

        Picasso.with(this)
                .load("https://static.pexels.com/photos/145939/pexels-photo-145939.jpeg")
                .into(imageView);


        //rotate
        Picasso.with(this)
                .load("https://static.pexels.com/photos/35600/road-sun-rays-path.jpg")
                .rotate(180)
                .into(imageView2);
    }
}
