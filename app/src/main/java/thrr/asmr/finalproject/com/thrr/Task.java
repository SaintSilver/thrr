package thrr.asmr.finalproject.com.thrr;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by pc-07 on 2018-01-04.
 */

public class Task extends AsyncTask<String, Void, String> {
    public static String ip = "192.168.0.27"; //자신의 IP번호
    public static String sendMsg, receiveMsg;
    String serverip = "http://192.168.0.18:8080/Thrr/AndCom?"; // 연결할 jsp주소


    Task(String sendmsg) {
        this.sendMsg = sendmsg;
    }

    Task() {
    }

    ;

    @Override
    protected String doInBackground(String... strings) {
        try {

            Log.v("hhd", "hello2");
            //String rst = new Task(sendmsg).execute(result,"vision_write").get();
            String str;
            URL url = new URL(serverip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            Log.v("hhd", "hello3");
            if (sendMsg.equals("vision_write")) {
                sendMsg = "type=vision_write&vision_write=" + strings[0] + "&id=" + strings[1] + "&pw=" + strings[2];
            } else if (sendMsg.equals("vision_delete")) {
                sendMsg = "type=vision_write&vision_write=" + strings[0] + "&id=" + strings[1];
            } else if (sendMsg.equals("vision_sleep")){
                sendMsg = "type=vision_write&vision_write=" + strings[0] + "&id=" + strings[1] + "&sleep_time=" + strings[2] + "&wakeup_time=" + strings[3] + "&sleep_amount=" + strings[4] + "&select_ASMR=" + strings[5] + "&db=" + strings[6] + "&light=" + strings[7];
            } else if (sendMsg.equals("vision_focus")){
                sendMsg = "type=vision_write&vision_write=" + strings[0] + "&id=" + strings[1] + "&focus_time=" + strings[2] + "&select_ASMR=" + strings[3] + "&db=" + strings[4];
            } else if (sendMsg.equals("vision_select")){

            }

            osw.write(sendMsg);
            osw.flush();
            Log.v("hhd", "hello4");
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();

                Log.v("response", receiveMsg);
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
            Log.v("hhd", "hello5");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }

}