package com.ezra.elon.technologiaandahzaka.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Acivities.MainActivity;
import com.ezra.elon.technologiaandahzaka.R;

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
import java.util.Random;

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

    public List<CourseButton> RandomCoures(Context context)
    {
        ArrayList<CourseButton> temp = new ArrayList<>();
        ArrayList<itemHolder> courseListArray = new ArrayList<>();
        String megama = "";
        JSONObject root = null;

        temp.clear();
        int megama_id;
        int course_id;
        int megama_image = 0;
        Random random = new Random();

        for(int d=0; d<4; d++)
        {

            megama_id = random.nextInt(4);
            switch (megama_id) {

                case 0://mechenic
                    megama = "Mechanic";
                    megama_image = R.drawable.sufa;
                    break;
                case 1://electronic
                    megama = "Electronic";
                    megama_image = R.drawable.background2;
                    break;
                case 2://electricity
                    megama = "Electricity";
                    megama_image = R.drawable.bareket;
                    break;
                case 3://toon
                    megama = "toon";
                    megama_image = R.drawable.toon;
                    break;
                case 4://cars
                    megama = "Cars";
                    megama_image = R.drawable.sufa;
                    break;

            }

            //now i have a megama now i need to pick random course


            try {
                root = new JSONObject(Asistent.loadJSONFromAsset((context), "jsonFiles/shibutzim.json"));
                JSONArray maslulim = root.getJSONArray(megama);
                int i;
                if(megama == "toon")
                {
                    i = 0;
                }
               else
                {
                    i = random.nextInt(maslulim.length());
                }

                String name;
                String url;

                JSONObject maslul = maslulim.getJSONObject(i);//get single object

                boolean isnotexsist = true;
                for (int x = 0;temp.size() < x; x++)
                {
                    if(temp.get(i).getTitle() == maslul.getString("name") )
                    {   isnotexsist = false;
                        break;
                    }


                }

                if(isnotexsist)
                {
                    temp.add(new CourseButton(megama_image, maslul.getString("name")));
                }


                Log.i("JsonToArryList", maslul.getString("name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

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
