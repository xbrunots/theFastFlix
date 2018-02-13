package com.devbruno.fastshop.infraestruture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.devbruno.fastshop.model.Movie;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class SharedUtils {

    public static void shareItem(Context context, String text, String imageUrl) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, imageUrl);
        sendIntent.setType("image/*");
        context.startActivity(Intent.createChooser(sendIntent, text));
    }

    public static void shareMovie(Context context, Movie movies) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, movies.getPosterPath());
        sendIntent.setType("image/*");
        context.startActivity(Intent.createChooser(sendIntent, "Compartilhar ".concat(movies.getTitle()).concat("?")));

    }

    //////// this method share your image
    public static void shareIt(Context context, Movie movies, Bitmap bitmap) {
        try {
            File file = new File(context.getCacheDir(), movies.getId() + ".png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
