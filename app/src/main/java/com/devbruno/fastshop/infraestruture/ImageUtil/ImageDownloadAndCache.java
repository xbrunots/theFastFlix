package com.devbruno.fastshop.infraestruture.ImageUtil;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.devbruno.fastshop.infraestruture.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by bsilvabr on 11/02/2018.
 */
public class ImageDownloadAndCache {

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Activity context;

        public DownloadImageTask(ImageView bmImage, Activity context) {
            this.bmImage = bmImage;
            this.context = context;
        }

        protected Bitmap doInBackground(String... params) {
            BitmapCache cache = new BitmapCache(this.context.getApplication());
            String urlStr = params[0];
            Bitmap img = null;

            if (!cache.isBitmapInCache(urlStr)) {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(urlStr);
                HttpResponse response;
                try {
                    response = (HttpResponse) client.execute(request);
                    HttpEntity entity = response.getEntity();
                    BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                    InputStream inputStream = bufferedEntity.getContent();
                    img = BitmapFactory.decodeStream(inputStream);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cache.addBitmapToCache(urlStr, img);
            } else {
                img = cache.getBitmapFromCache(urlStr);
            }
            return img;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    static class BitmapCache extends SQLiteOpenHelper {

        public BitmapCache(Context context) {
            super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Constants.SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(Constants.SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public void addBitmapToCache(String key, Bitmap bitmap) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("key", key);
            values.put("bitmap", DbBitmapUtility.getBytes(bitmap));
            db.insert("bitmaps", null, values);
        }

        public Boolean isBitmapInCache(String key) {
            Cursor c = getKeyCursor(key);

            return (c.moveToFirst() && c.getCount() != 0);
        }

        public Bitmap getBitmapFromCache(String key) {
            Cursor c = getKeyCursor(key);

            c.moveToFirst();
            byte[] tmp = c.getBlob(c.getColumnIndex("bitmap"));
            Bitmap img = DbBitmapUtility.getImage(tmp);
            c.close();
            return img;
        }

        private Cursor getKeyCursor(String key) {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] projection = {"key", "bitmap"};
            String sortOrder = "key DESC";

            return db.query(
                    "bitmaps",                           // The table to query
                    projection,                                // The columns to return
                    "key = ?",                        // The columns for the WHERE clause
                    new String[]{key},                         // The values for the WHERE clause
                    null,                             // don't group the rows
                    null,                              // don't filter by row groups
                    sortOrder                                 // The sort order
            );
        }
    }
}
