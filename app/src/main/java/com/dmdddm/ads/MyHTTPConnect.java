package com.dmdddm.ads;

import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHTTPConnect {
    private String text;
    private String[] result={"","",""};

    /**获取数据
     * path 访问地址
     * item[] 为要接要拿出来的数据的key
     * Handler 对象 用来实现多线程通信
     * 时间**/
    public String[] getJson(final URL url , final String[] item, final Handler handler){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //链接属性
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type","plain/text; charset=UTF-8");
                    conn.setConnectTimeout(5000);
                    conn.setUseCaches(true);

                    //开启链接
                    conn.connect();
                    //获取数据
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                    //解析
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    //状态码为200,链接成功
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        String inputLine;
                        StringBuffer resultData = new StringBuffer();
                        while ((inputLine = bufferedReader.readLine()) != null) {
                            resultData.append(inputLine);
                        }
                        //获取json结果
                        text = resultData.toString();;
                        parseJson(item);
                    }
                    reader.close();
                    inputStream.close();
                    conn.disconnect();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**Message 使用**/
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
        return result;
    }

    /**解析json***/
    public String[] parseJson(String[] item){
        //Toast.makeText(getActivity(),"parseJson",Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(text);

            for (int i =0;i<item.length;i++){
                result[i]=jsonObject.getString(item[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
