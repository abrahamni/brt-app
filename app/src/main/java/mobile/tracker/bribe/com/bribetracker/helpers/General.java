package mobile.tracker.bribe.com.bribetracker.helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;


/**
 * Created by misha on 4/21/2015.
 */
public class General {

    public static void saveImageToFiles(Bitmap bmp, String filename) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            Log.e("misha", e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Log.e("misha", e.toString());
                e.printStackTrace();
            }
        }
    }


    public static void saveToFiles(InputStream srcStream, String filelDestination) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(filelDestination);
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = srcStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            srcStream.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static String convertToBase64(String filePath) {
        Bitmap bm = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }


    public static String timestampToString(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(timestamp);
    }


    public static void hideKeyboard(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (context.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static String getFormattedDateString(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd kk:mm:ss");
        return format.format(timestamp);
    }

    public static String formatDecimal(double d) {
        return Math.round(d * 100.0) / 100.0 + "";
    }


}

