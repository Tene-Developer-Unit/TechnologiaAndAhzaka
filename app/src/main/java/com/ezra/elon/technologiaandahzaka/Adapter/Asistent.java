package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Asistent {

    //"jsonFiles/maslulim.json"
   public static String loadJSONFromAsset(Context context, String path)
   {
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


   public <T> T loadObjectFile(Context context, String fileName)
   {

       T simpleClass = null;
       try {
           FileInputStream fis = context.openFileInput(fileName);
           ObjectInputStream is = new ObjectInputStream(fis);
            simpleClass = (T) is.readObject();
           is.close();
           fis.close();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }


       return  simpleClass;
   }


    public <T> T WriteToFile(Context context, String fileName, T object)
    {



        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }




   /*
   *
   * function that will check the internet connection
   *
   * */
    public static boolean isNetworkConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public List<itemHolder> RandomCoures(Context context)
    {
        ArrayList<itemHolder> temp = new ArrayList<>();
        ArrayList<itemHolder> courseListArray = new ArrayList<>();
        String megama = "";
        JSONObject root = null;

            try {
                root = new JSONObject(Asistent.loadJSONFromAsset((context), "jsonFiles/shibutzim.json"));
                JSONArray maslulim = root.getJSONArray(megama);
                String name;
                String url;

                for (int i = 0; i < maslulim.length(); i++) {
                    JSONObject maslul = maslulim.getJSONObject(i);//get single object
                    courseListArray.add(new itemHolder(maslul.optString("videoUrl"),maslul.getString("name")));
                    Log.i("JsonToArryList", maslul.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return temp;
    }

  public static class itemHolder///holde the text and the title of the main screen course menu
    {
        String title;
        String image;
        public itemHolder(String image, String title)
        {
            this.image = image;
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }
    }


}
