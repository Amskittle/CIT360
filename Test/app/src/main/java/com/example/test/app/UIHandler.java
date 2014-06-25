package com.example.test.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Amy Billeter on 6/23/2014.
 */
public class UIHandler extends Handler {
    public void handleMessage(Message msg) {
        Bundle bundle = msg.getData();
        String string = bundle.getString("println");
//        TextView myTextView =
//                (TextView) findViewById(R.id.textView);
//        myTextView.setText(string);
    }

}
