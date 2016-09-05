package com.zhang.myapplication;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.security.KeyRep;

public class SetIpActivity extends Activity implements View.OnClickListener {
    protected static final int PROGRESS_CHANGED = 0x101;
    private SeekBar volSeekBar;
    private TextView mVolume;
    private AudioManager mAudioManager;
    private int maxVolume, currentVolume;
    private EditText sipserverip_input;
    private EditText mqserverip_input;
    private EditText webserver_input;
    private EditText sipserverport_input;
    private EditText mqserverport_input;
    private EditText webserverport_input;
    private SharedPreferencesUtils sp;
    private String sipserver_string;
    private String mqserver_string;
    private String webserver_string;
    private String sipserver_portstring;
    private String mqserver_portstring;
    private String webserver_portstring;
    private TextView sure_btn;
    Thread myVolThread = null;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PROGRESS_CHANGED:
                    setVolum();

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip);
        initView();

    }

    private void initView() {
        sipserverip_input = (EditText) findViewById(R.id.sipserverip_edit);
        mqserverip_input = (EditText) findViewById(R.id.mqserverip_edit);
        webserver_input = (EditText) findViewById(R.id.webserverip_edit);
        sipserverport_input = (EditText) findViewById(R.id.sipserverport_edit);
        mqserverport_input = (EditText) findViewById(R.id.mqserverport_edit);
        webserverport_input = (EditText) findViewById(R.id.webserverport_edit);
        sipserverip_input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mqserverip_input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        webserver_input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        sipserverport_input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mqserverport_input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        webserverport_input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        sure_btn = (TextView) findViewById(R.id.setip_sure);
        sure_btn.setOnClickListener(this);
        if (sp.getParam(this, "sipserver", "").toString() != null) {
            sipserver_string = sp.getParam(this, "sipserver", "").toString();
            sipserverip_input.setText(sipserver_string);
        }
        if (sp.getParam(this, "mqserver", "").toString() != null) {
            mqserver_string = sp.getParam(this, "mqserver", "").toString();
            mqserverip_input.setText(mqserver_string);
        }
        if (sp.getParam(this, "webserver", "").toString() != null) {
            webserver_string = sp.getParam(this, "webserver", "").toString();
            webserver_input.setText(webserver_string);
        }
        if (sp.getParam(this, "sipserver_port", "").toString() != null) {
            sipserver_portstring = sp.getParam(this, "sipserver_port", "").toString();
            sipserverport_input.setText(sipserver_portstring);
        }
        if (sp.getParam(this, "mqserver_port", "").toString() != null) {
            mqserver_portstring = sp.getParam(this, "mqserver_port", "").toString();
            mqserverport_input.setText(mqserver_portstring);
        }
        if (sp.getParam(this, "webserver_port", "").toString() != null) {
            webserver_portstring = sp.getParam(this, "webserver_port", "").toString();
            webserverport_input.setText(webserver_portstring);
        }
        volSeekBar = (SeekBar) findViewById(R.id.volume_seekbar);
        mVolume = (TextView) findViewById(R.id.volum_val);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND|AudioManager.FLAG_SHOW_UI);

              //  mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, AudioManager.FLAG_SHOW_UI);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

        };

        volSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        new Thread(new myVolThread()).start();
    }

    private void setVolum() {

        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volSeekBar.setMax(maxVolume);
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volSeekBar.setProgress(currentVolume);
        mVolume.setText(currentVolume * 100 / maxVolume + " ");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setip_sure:
                saveSet();
        }
    }

    class myVolThread implements Runnable {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {

                Message message = new Message();
                message.what = PROGRESS_CHANGED;
                SetIpActivity.this.myHandler.sendMessage(message);
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /*
    保存设置ip地址
     */
    private void saveSet() {
        sipserver_string = sipserverip_input.getText().toString();
        mqserver_string = mqserverip_input.getText().toString();
        webserver_string = webserver_input.getText().toString();
        sipserver_portstring = sipserverport_input.getText().toString();
        mqserver_portstring = mqserverport_input.getText().toString();
        webserver_portstring = webserverport_input.getText().toString();
        if (!sipserverip_input.getText().toString().equals("") & !mqserverip_input.getText().toString().equals("") & !webserver_input.getText().toString().equals("") & !sipserverport_input.getText().toString().equals("") & !mqserverport_input.getText().toString().equals("") & !webserverport_input.getText().toString().equals("")) {
            sp.setParam(this, "sipserver", sipserver_string);
            sp.setParam(this, "mqserver", mqserver_string);
            sp.setParam(this, "webserver", webserver_string);
            sp.setParam(this, "sipserver_port", sipserver_portstring);
            sp.setParam(this, "mqserver_port", mqserver_portstring);
            sp.setParam(this, "webserver_port", webserver_portstring);
            finish();
        } else {
            Toast.makeText(this, "请正确输入ip地址和端口号", Toast.LENGTH_SHORT).show();
        }

    }
}
