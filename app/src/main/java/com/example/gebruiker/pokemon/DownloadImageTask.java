package com.example.gebruiker.pokemon;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.ImageView;

        import java.io.InputStream;

/**
 * Created by Nathalie van Sterkenburg on 31-1-2018.
 * Based on DownloadImageTask by Android Developer (http://stackoverflow.com/users/1196072/android-developer) on 15-2-2012.
 *
 * source: http://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
 *
 * Takes link to Pokémon sprite and puts it in an ImageView
 */

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11;
        Bitmap mIcon112 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);

            // the sprite is small and is made bigger
            mIcon112 = Bitmap.createScaledBitmap(mIcon11, mIcon11.getWidth() * 3,
                    mIcon11.getHeight() * 3, false);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon112;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}