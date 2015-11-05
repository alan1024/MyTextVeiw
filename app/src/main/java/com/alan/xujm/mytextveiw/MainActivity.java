package com.alan.xujm.mytextveiw;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
public class MainActivity extends Activity {

    private AutoTextView mTextView;
    private static int sCount = 10;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mTextView.next();
            mTextView.setText(msg.what%2==0 ?
                    sCount+"AAFirstAA" :
                    sCount+"BBBBBBB");
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {

        mTextView = (AutoTextView) findViewById(R.id.switcher02);
        mTextView.setText("Hello world!");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    sCount++;
                    Message message=new Message();
                    message.what=sCount;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

}