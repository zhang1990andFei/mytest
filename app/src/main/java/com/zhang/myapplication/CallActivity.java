package com.zhang.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.TransitionManager;
import android.view.Window;

public class CallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
    }
}
