package com.mwdowski.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Size;

import androidx.annotation.RequiresApi;

import java.io.IOException;

public class Photo {

    public long id;
    public Uri uri;
    public String name;
    public int size;

    public Photo(Context context, long id, Uri uri, String name, int size, Uri thumbnailUri) {
        this.id = id;
        this.uri = uri;
        this.name = name;
        this.size = size;
    }

    public Bitmap generateThumbnail(Context context) {
        Bitmap thumbnail = null;

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                thumbnail = context.getContentResolver().loadThumbnail(uri, Variables.THUMBNAIL_SIZE, null);
            }
            else {
                thumbnail = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return thumbnail;
    }
}
