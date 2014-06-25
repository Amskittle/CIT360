package com.example.test.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.*;
import android.net.*;
import android.text.Editable;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;
import org.w3c.dom.Text;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {
    int numMessagesSent = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Fix this
        //get the UI items to interact with
        final TextView responseView =
                (TextView)this.findViewById(R.id.textView);
        final EditText textInput =
                (EditText)this.findViewById(R.id.editText);

        //Create handler for UI
        //Note: created outside thread because an error was thrown:
        // "java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()"
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String string = bundle.getString("println");
                TextView myTextView =
                        (TextView)findViewById(R.id.textView);
                myTextView.setText(string);
            }
        };

            //add the on click listener to the button
            Button sendButton =
                    (Button)this.findViewById(R.id.button);
            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //connect to the server
                    //consider putting in a separate class for this thread
                    Thread aThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Socket toServer = new Socket("10.0.2.2", 9292);
                                //setup the JSON streams to be used later.
                                final JSONInputStream inFromServer =
                                        new JSONInputStream(toServer.getInputStream());
                                final JSONOutputStream outToServer =
                                        new JSONOutputStream(toServer.getOutputStream());

                                numMessagesSent++;
                                //prepare the bean
                                ArrayList aDataList = new ArrayList();
                                aDataList.add(numMessagesSent);
                                aDataList.add(textInput.getText().toString());
                                CommunicationBean aBean =
                                        new CommunicationBean();
                                aBean.setCommand("Speak");
                                aBean.setData(aDataList);

                                try {
                                    //send the bean
                                    outToServer.writeObject(aBean);
                                    HashMap aMap =
                                          (HashMap) inFromServer.readObject();

                                    //responseView.setText(aMap.toString());
                                    //Format the data coming back
                                    int index1 = aMap.toString().indexOf(",");
                                    int index2 = aMap.toString().indexOf("]");
                                    String output = aMap.toString().substring(index1+1, index2);
                                    System.out.println(output);

                                    Message msg = handler.obtainMessage();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("println", output);
                                    msg.setData(bundle);
                                    handler.sendMessage(msg);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    System.out.println("Error: Unable to trade beans with server.");
                                }
                            }catch(IOException e){
                                e.printStackTrace();
                                System.out.println("Error: Unable to connect with server.");
                            }
                        }
                    });

                    Thread bThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String img1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Smiley.svg/800px-Smiley.svg.png";
                                //Call my DownloadImage function.
                                final Bitmap bitmap = DownloadImage(img1);
                                //To change the imageView, it must be done back on the UI thread.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setImage(bitmap);
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //Start both threads
                    aThread.start();
                    bThread.start();

                }
            });

    }

//Downloads an image from HttpURLConnection
    private Bitmap DownloadImage(String img) {
        Bitmap bitmap = null;
        try {
            //New url
            URL url = new URL(img);
            //Cast as httpURLConnection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Get image, decode it, and store as bitmap.
            bitmap = BitmapFactory.decodeStream(con.getInputStream());
            con.disconnect();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    //Change the image in imageView
    private void setImage(Bitmap img){
        final ImageView imgView =
                (ImageView)this.findViewById(R.id.imageView);
        imgView.setImageBitmap(img);
    }



    // This function checks for network connectivity. access_network_state permission required in manifest.
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
}
