package com.zhang.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /************* 第一种用法————开始 ***************/
//        setContentView(R.layout.activity_main);
//
//        final PasswordView pwdView = (PasswordView) findViewById(R.id.pwd_view);
//
//        //添加密码输入完成的响应
//        pwdView.setOnFinishInput(new OnPasswordInputFinish() {
//            @Override
//            public void inputFinish() {
//            	//输入完成后我们简单显示一下输入的密码
//            	//也就是说——>实现你的交易逻辑什么的在这里写
//                Toast.makeText(MainActivity.this, pwdView.getStrPassword(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        
//        /**
//         *  可以用自定义控件中暴露出来的cancelImageView方法，重新提供相应
//         *  如果写了，会覆盖我们在自定义控件中提供的响应
//         *  可以看到这里toast显示 "Biu Biu Biu"而不是"Cancel"*/
//        pwdView.getCancelImageView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Biu Biu Biu", Toast.LENGTH_SHORT).show();
//            }
//        });

        /************ 第一种用法————结束 ******************/

        /************* 第二种用法————开始 *****************/
        final PasswordView pwdView = new PasswordView(this, new OnPasswordInputFinish() {
            @Override
            public void inputFinish(String passWord) {
                Toast.makeText(MainActivity.this, passWord, Toast.LENGTH_SHORT).show();
                String password = null;
                password = passWord;
                switch (password) {
                    case "123456":
                        IntentActivity(MainActivity.this, SetIpActivity.class);
                        break;
                    case "654321":
                        IntentActivity(MainActivity.this, RegisterActivity.class);
                        break;
                    case "123457":
                        IntentActivity(MainActivity.this, CallActivity.class);
                        break;
                    case "123458":
                        IntentActivity(MainActivity.this, AboutActivity.class);
                        break;
                    case "123459":
                        IntentActivity(MainActivity.this, SpalshActivity.class);
                }

            }
        });
        setContentView(pwdView);


    }

    private void IntentActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);

    }


}
