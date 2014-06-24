package com.example.test.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.Socket;
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

            //add the on click listener to the button
            Button sendButton =
                    (Button)this.findViewById(R.id.button);
            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //connect to the server
                    Thread athread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Socket toServer = new Socket("10.0.2.2", 80);
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
                                    //Create handler for UI
                                    Handler handler = new Handler() {
                                        @Override
                                        public void handleMessage(Message msg) {
                                            Bundle bundle = msg.getData();
                                            String string = bundle.getString("println");
                                            TextView myTextView =
                                                    (TextView)findViewById(R.id.textView);
                                            myTextView.setText(string);
                                        }
                                    };


                                    //send the bean
                                    outToServer.writeObject(aBean);
                                    HashMap aMap =
                                          (HashMap) inFromServer.readObject();

                                    //responseView.setText(aMap.toString());
                                    System.out.println(aMap.toString());
                                    Message msg = handler.obtainMessage();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("println", aMap.toString());
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

                    athread.start();


                }
            });

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
