package np.cnblabs.accountdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by sanjogstha on 11/21/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class PicassoImage extends AppCompatActivity{
    ImageView imageView;
    String url = "https://www.epxworldwide.com/wp-content/uploads/photo-gallery/Mount-Everest-at-sunset-639486254_4743x2104%20(1).jpeg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        imageView = findViewById(R.id.imageView);
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.cricket)
                .into(imageView);

    }
}
