package com.zhang.myapplication;

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Belong to the Project —— MyPayUI
 * Created by WangJ on 2015/11/25 15:39.
 */
public class PasswordView extends RelativeLayout implements View.OnClickListener {
    Context context;
    private MainActivity mainActivity;
    private String strPassword;     //输入的密码
    private TextView[] tvList;      //用数组保存6个TextView，为什么用数组？
    private TextView passwordTextview;
    private List<String> numList;     //用数组保存数值
    private List<String> list_pwd;                                //因为就6个输入框不会变了，用数组内存申请固定空间，比List省空间（自己认为）
    private GridView gridView;    //用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能
    private ArrayList<Map<String, String>> valueList;    //有人可能有疑问，为何这里不用数组了？
    //因为要用Adapter中适配，用数组不能往adapter中填充
    private TextView textOne, textTwo, textThree, textFour, textFive, textSix, textSeven, textEight, textNine, textZero, textCancel, textSure;
    private ImageView imgCancel;
    private ImageView delete_one;
    private TextView tvForget;
    private int currentIndex = 0;    //用于记录当前输入密码格位置

    public PasswordView(Context context, OnPasswordInputFinish pass) {
        this(context, null, pass);
    }

    public PasswordView(Context context, AttributeSet attrs, OnPasswordInputFinish pass) {
        super(context, attrs);
        this.context = context;
        this.pass = pass;
        View view = View.inflate(context, R.layout.layout_popup_bottom, null);
        initContorl(view);
        //  gridView = (GridView) view.findViewById(R.id.gv_keybord);
        //  setView();
        //必须要，不然不显示控件
        addView(view);
    }

    private void initContorl(final View view) {
        valueList = new ArrayList<Map<String, String>>();
        tvList = new TextView[6];
        numList = new ArrayList<String>();
        list_pwd = new ArrayList<String>();
        imgCancel = (ImageView) view.findViewById(R.id.img_cancel);
        imgCancel.setOnClickListener(this);
        passwordTextview = (TextView) view.findViewById(R.id.tv_password);
        textOne = (TextView) view.findViewById(R.id.btn_one);
        textOne.setOnClickListener(this);
        textTwo = (TextView) view.findViewById(R.id.btn_two);
        textTwo.setOnClickListener(this);
        textThree = (TextView) view.findViewById(R.id.btn_three);
        textThree.setOnClickListener(this);
        textFour = (TextView) view.findViewById(R.id.btn_four);
        textFour.setOnClickListener(this);
        textFive = (TextView) view.findViewById(R.id.btn_five);
        textFive.setOnClickListener(this);
        textSix = (TextView) view.findViewById(R.id.btn_six);
        textSix.setOnClickListener(this);
        textSeven = (TextView) view.findViewById(R.id.btn_seven);
        textSeven.setOnClickListener(this);
        textEight = (TextView) view.findViewById(R.id.btn_eight);
        textEight.setOnClickListener(this);
        textNine = (TextView) view.findViewById(R.id.btn_nine);
        textNine.setOnClickListener(this);
        textZero = (TextView) view.findViewById(R.id.btn_zero);
        textZero.setOnClickListener(this);
        textSure = (TextView) view.findViewById(R.id.btn_sure);
        textSure.setOnClickListener(this);
        textCancel = (TextView) view.findViewById(R.id.btn_cancel);
        textCancel.setOnClickListener(this);
        delete_one = (ImageView) view.findViewById(R.id.delete_one);
        delete_one.setOnClickListener(this);
////        passwordTextview.setOnTouchListener(new OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                //getCompoundDrawables() 可以获取一个长度为4的数组，
////                //存放drawableLeft，Right，Top，Bottom四个图片资源对象
////                //index=2 表示的是 drawableRight 图片资源对象
////                Drawable drawable = passwordTextview.getCompoundDrawables()[2];
////                if (drawable == null)
////                    return false;
////                if (event.getAction() != MotionEvent.ACTION_UP)
////                    return false;
////                //drawable.getIntrinsicWidth() 获取drawable资源图片呈现的宽度
////                if (event.getX() > passwordTextview.getWidth() - passwordTextview.getPaddingRight()
////                        - drawable.getIntrinsicWidth()) {
////                    //进入这表示图片被选中，可以处理相应的逻辑了
////                    //清除一位
////                    passwordTextview.setText("");
////                    strPassword = "";
////                    if (currentIndex - 1 > -1) {
////                        list_pwd.remove(currentIndex - 1);
////                        currentIndex--;
////                        for (int i = 0; i < list_pwd.size(); i++) {
////                            strPassword += list_pwd.get(i).toString().trim();
////                        }
////                        tvList[0].setText(strPassword);
////                    }
////                    // 清除所有数据
//////                   tvList[0].setText("");
////                    //strPassword = "";
//////                   list_pwd = new HashMap<Integer, String>();
////                }
////
////
////                return false;
////            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cancel:
                //context.startActivity(new Intent(context,MainActivity.class));
                // mainActivity.finish();
                ((Activity) context).finish();
                break;
            case R.id.btn_one:
                setNum("1");
                break;
            case R.id.btn_two:
                setNum("2");
                break;
            case R.id.btn_three:
                setNum("3");
                break;
            case R.id.btn_four:
                setNum("4");
                break;
            case R.id.btn_five:
                setNum("5");
                break;
            case R.id.btn_six:
                setNum("6");
                break;
            case R.id.btn_seven:
                setNum("7");
                break;
            case R.id.btn_eight:
                setNum("8");
                break;
            case R.id.btn_nine:
                setNum("9");
                break;
            case R.id.btn_zero:
                setNum("0");
                break;
            case R.id.btn_cancel:
                clearNum();
                break;
            case R.id.btn_sure:
                getPwd();

                break;
            case R.id.delete_one:
                removeNum();
                break;
        }
    }

    private void setView() {
        /* 初始化按钮上应该显示的数字 */
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<String, String>();
            if (i < 10) {
                map.put("name", String.valueOf(i));
            } else if (i == 10) {
                map.put("name", "取消");
            } else if (i == 11) {
                map.put("name", String.valueOf(0));
            } else if (i == 12) {
                map.put("name", "确定");
            }
            valueList.add(map);
        }

        // gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position < 11 && position != 9) {    //点击0~9按钮
//                    if (currentIndex > -1) {      //判断输入位置————要小心数组越界
//                        ++currentIndex;
//                        list_pwd.add(valueList.get(position).get("name"));
//                        tvList[0].append(valueList.get(position).get("name"));
//                    }
//
//                } else {
//                    if (position == 11) {      //点击确认键
//                        if (currentIndex == 0) {      //判断是否删除完毕————要小心数组越界
//                            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
//                        } else {
//                            setOnFinishInput();
//                        }
//                    } else if (position == 10) {
//
//                    } else if (position == 9) {
//                        tvList[0].setText("");
//                        list_pwd = new ArrayList<String>();
//                    }
//                }
        //        }
        //    });
    }

    OnPasswordInputFinish pass;

    //设置监听方法，在第6位输入完成后触发
    public void setOnFinishInput() {
        strPassword = "";
        for (int i = 0; i < list_pwd.size(); i++) {
            strPassword += list_pwd.get(i).toString().trim();
        }
        pass.inputFinish(strPassword);    //接口中要实现的方法，完成密码输入完成后的响应逻辑
    }

    /* 获取输入的密码 */
    public String getStrPassword() {
        return strPassword;
    }

    /* 暴露取消支付的按钮，可以灵活改变响应 */
    public ImageView getCancelImageView() {
        return imgCancel;
    }

    /* 暴露忘记密码的按钮，可以灵活改变响应 */
    public TextView getForgetTextView() {
        return tvForget;
    }

//    //GrideView的适配器
//    BaseAdapter adapter = new BaseAdapter() {
//        @Override
//        public int getCount() {
//            return valueList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return valueList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder;
//            if (convertView == null) {
//                convertView = View.inflate(context, R.layout.item_gride, null);
//                viewHolder = new ViewHolder();
//                viewHolder.btnKey = (TextView) convertView.findViewById(R.id.btn_keys);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.btnKey.setText(valueList.get(position).get("name"));
//            if (position == 9) {
//                viewHolder.btnKey.setBackgroundResource(R.drawable.selector_key_del);
//
//            }
//            if (position == 11) {
//                viewHolder.btnKey.setBackgroundResource(R.drawable.selector_key_del);
//            }
//            return convertView;
//        }
//    };

    /**
     * 存放控件
     */
//    public final class ViewHolder {
//        public TextView btnKey;
//    }
    public void setNum(String number) {
        if (currentIndex > -1) {      //判断输入位置————要小心数组越界
            ++currentIndex;
            list_pwd.add(number);
            Log.e("asd", "数组长度" + list_pwd.size());
            if (strPassword != null) {
                strPassword += list_pwd.get(list_pwd.size() - 1).toString().trim();
                passwordTextview.setText(strPassword);
                Log.e("asd", "添加后" + strPassword);
            } else {
                strPassword = list_pwd.get(list_pwd.size() - 1).toString().trim();
                passwordTextview.setText(strPassword);
            }
        }
    }

    //移除一位
    public void removeNum() {
        passwordTextview.setText("");
        strPassword = "";
        if (currentIndex - 1 > -1) {
            list_pwd.remove(currentIndex - 1);
            currentIndex--;
            for (int i = 0; i < list_pwd.size(); i++) {
                strPassword += list_pwd.get(i).toString().trim();
            }
            passwordTextview.setText(strPassword);
        }
        Log.e("asd", "删除后" + strPassword);
    }

    //清空密码
    public void clearNum() {
        list_pwd.clear();
        strPassword = "";
        passwordTextview.setText("");
        currentIndex = 0;
    }

    //获取密码
    public void getPwd() {
//        for (int i = 0; i < list_pwd.size(); i++) {
//            strPassword += list_pwd.get(i).toString().trim();
//
//        }
        if (strPassword != null ) {
//            switch (strPassword){
//
//            }
            Log.e("asd", "正确密码" + strPassword);
            setOnFinishInput();
            clearNum();
            currentIndex = 0;


        } else {
            clearNum();
            Toast.makeText(context, "请输入正确密码", Toast.LENGTH_LONG).show();
            currentIndex = 0;
        }
        Log.e("asd", "确定时" + strPassword);
    }
}
