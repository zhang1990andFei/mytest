package com.zhang.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zhang.myapplication.utils.Common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    long m_newVerCode; //最新版的版本号
    String m_newVerName; //最新版的版本名
    String m_appNameStr; //下载到本地要给这个APP命的名字

    Handler m_mainHandler;
    ProgressDialog m_progressDlg;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
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
                        //  overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                        //淡入淡出
                        // overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        //左边进入
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        //放大缩小
                        break;
                    case "123458":
                        IntentActivity(MainActivity.this, AboutActivity.class);
                        break;
                    case "123459":
                        IntentActivity(MainActivity.this, SpalshActivity.class);
                        break;
                    case "987654":
                        IntentActivity(MainActivity.this, TestActivity.class);
                        break;
                }

            }
        });
        setContentView(pwdView);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initVariable() {
        m_mainHandler = new Handler();
        m_progressDlg = new ProgressDialog(this);
        m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        m_progressDlg.setIndeterminate(false);
        m_appNameStr = "haha.apk";
        new checkNewestVersionAsyncTask().execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.zhang.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.zhang.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class checkNewestVersionAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO Auto-generated method stub
            if (postCheckNewestVersionCommand2Server()) {
                int vercode = Common.getVerCode(getApplicationContext()); // 用到前面第一节写的方法
                if (m_newVerCode > vercode) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            if (result) {//如果有最新版本
                doNewVersionUpdate(); // 更新新版本
            } else {
                notNewVersionDlgShow(); // 提示当前为最新版本
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
    }

    /**
     * 从服务器获取当前最新版本号，如果成功返回TURE，如果失败，返回FALSE
     *
     * @return
     */
    private Boolean postCheckNewestVersionCommand2Server() {
        StringBuilder builder = new StringBuilder();
        JSONArray jsonArray = null;
        try {
            // 构造POST方法的{name:value} 参数对
            List<NameValuePair> vps = new ArrayList<NameValuePair>();
            // 将参数传入post方法中
            vps.add(new BasicNameValuePair("action", "checkNewestVersion"));
            builder = Common.post_to_server(vps);
            jsonArray = new JSONArray(builder.toString());
            if (jsonArray.length() > 0) {
                if (jsonArray.getJSONObject(0).getInt("id") == 1) {
                    m_newVerName = jsonArray.getJSONObject(0).getString("verName");
                    m_newVerCode = jsonArray.getJSONObject(0).getLong("verCode");

                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            Log.e("msg", e.getMessage());
            m_newVerName = "";
            m_newVerCode = -1;
            return false;
        }
    }

    /**
     * 提示更新新版本
     */
    private void doNewVersionUpdate() {
        int verCode = Common.getVerCode(getApplicationContext());
        String verName = Common.getVerName(getApplicationContext());

        String str = "当前版本：" + verName + " Code:" + verCode + " ,发现新版本：" + m_newVerName +
                " Code:" + m_newVerCode + " ,是否更新？";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                m_progressDlg.setTitle("正在下载");
                                m_progressDlg.setMessage("请稍候...");
                                downFile(Common.UPDATESOFTADDRESS);  //开始下载
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    /**
     * 提示当前为最新版本
     */
    private void notNewVersionDlgShow() {
        int verCode = Common.getVerCode(this);
        String verName = Common.getVerName(this);
        String str = "当前版本:" + verName + " Code:" + verCode + ",/n已是最新版,无需更新!";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新")
                .setMessage(str)// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    private void downFile(final String url) {
        m_progressDlg.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    m_progressDlg.setMax((int) length);//设置进度条的最大值

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                m_appNameStr);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                m_progressDlg.setProgress(count);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void down() {
        m_mainHandler.post(new Runnable() {
            public void run() {
                m_progressDlg.cancel();
                update();
            }
        });
    }

    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), m_appNameStr)),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }


    private void IntentActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        startActivity(intent);
        finish();

    }


}
