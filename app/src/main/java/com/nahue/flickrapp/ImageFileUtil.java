package com.nahue.flickrapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class ImageFileUtil {

    public Uri getBitmapImageUri(String folderPath, String fileName) {

        if (isExternalStorageWritable()) {
            String root = Environment.getExternalStorageDirectory().toString();
            //File myDir = new File(root + "/" + folderPath);
            File myDir = new File( folderPath);
            File file = new File(myDir, fileName);
            return Uri.fromFile(file);
        }else{
            return null;
        }
    }

    public boolean saveBitmapImageToExternalStorage(Bitmap bitmap, String folderPath, String fileName, Bitmap.CompressFormat format) {
        if (isExternalStorageWritable()) {
            return saveImage(bitmap, folderPath, fileName, format);
        }else{
            return false;
        }
    }

    private boolean saveImage(Bitmap finalBitmap, String folderPath, String fileName, Bitmap.CompressFormat format) {

        //String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(folderPath);
        myDir.mkdirs();

        File file = new File(myDir, fileName);
        if (file.exists()) file.delete ();
        try {
            file.createNewFile();
        }
        catch (Exception e) {
            return false;
        }
        try (FileOutputStream out = new FileOutputStream(file)) {
            finalBitmap.compress(format, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}

