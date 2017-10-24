package fi.jamk.golfcourseswishlistexample;

import android.content.Context;

/**
 * Created by seide on 23.10.2017.
 */

public class Place
{
    public String name;
    public String imageName;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
    }
}
