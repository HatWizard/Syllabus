package space.project.syllabus;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    public static String doGet(String url)
            throws Exception {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0" );
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

//      print result
        Log.d(TAG,"Response string: " + response.toString());


        return response.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = (TextView)findViewById(R.id.textView);
        try {
            tv1.setText(doGet("https://en.uesp.net/wiki/General:Links"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}