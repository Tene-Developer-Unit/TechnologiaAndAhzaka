package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Asistent {

    //"jsonFiles/maslulim.json"
   public static String loadJSONFromAsset(Context context, String path) {
       String json = null;
     //  boolean fileExists =  new File(path).isFile();

       //if(fileExists) {
           try {

               InputStream is = context.getAssets().open(path);
               int size = is.available();
               byte[] buffer = new byte[size];
               is.read(buffer);
               is.close();
               json = new String(buffer, "UTF-8");
           } catch (IOException ex) {
               Toast.makeText(context,"error, no file found",Toast.LENGTH_SHORT).show();

               return null;
           }
      // }
//       else
//       {
//           Log.e("Asistent loadFromAsset", "the file " + path + " is not exsist");
//       }
       return json;
   }





}
