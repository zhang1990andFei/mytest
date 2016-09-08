package com.zhang.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;

public class TestActivity extends Activity implements View.OnClickListener{

    private CircleProgress mProgressView;
    private View mStartBtn;
    private View mStopBtn;
    private View mResetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mProgressView = (CircleProgress) findViewById(R.id.progress);
        mProgressView.startAnim();
        mStartBtn = findViewById(R.id.start_btn);
        mStartBtn.setOnClickListener(this);
        mStopBtn = findViewById(R.id.stop_btn);
        mStopBtn.setOnClickListener(this);
        mResetBtn = findViewById(R.id.reset_btn);
        mResetBtn.setOnClickListener(this);

        SeekBar mSeekBar = (SeekBar) findViewById(R.id.out_seek);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float factor = seekBar.getProgress() / 100f;
                mProgressView.setRadius(factor);
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == mStartBtn) {
            mProgressView.startAnim();
        } else if (v == mStopBtn) {
            mProgressView.stopAnim();
        } else if (v == mResetBtn) {
            mProgressView.reset();
        }
    }
}
