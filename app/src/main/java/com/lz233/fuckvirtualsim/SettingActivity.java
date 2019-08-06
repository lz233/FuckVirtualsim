package com.lz233.fuckvirtualsim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class SettingActivity extends Activity {
    private Switch crack_root;
    private Switch hide_discovery;
    private LinearLayout re_linearlayout;
    private Button re_button;
    private Button github_button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //状态栏透明
        setTranslucentStatus(this);
        //状态栏icon黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //fb
        crack_root = findViewById(R.id.crack_root);
        hide_discovery = findViewById(R.id.hide_discovery);
        re_button = findViewById(R.id.re_button);
        re_linearlayout = findViewById(R.id.re_linearlayout);
        github_button = findViewById(R.id.github_button);
        //初始化
        if (ReadStringFromFile(Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/crack_root.txt").equals("0")) {
            crack_root.setChecked(false);
        }else {
            crack_root.setChecked(true);
        }
        if (ReadStringFromFile(Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/hide_discovery.txt").equals("0")) {
            hide_discovery.setChecked(false);
        }else {
            hide_discovery.setChecked(true);
        }
        //监听器
        crack_root.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                if (b){
                    //editor.putBoolean("crack_root",true);
                    WriteStringToFile("1",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/","crack_root.txt");
                }else {
                    //editor.putBoolean("crack_root",false);
                    WriteStringToFile("0",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/","crack_root.txt");
                }
                //editor.commit();
                re_linearlayout.setVisibility(View.VISIBLE);
            }
        });
        hide_discovery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    WriteStringToFile("1",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/","hide_discovery.txt");
                }else {
                    WriteStringToFile("0",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/","hide_discovery.txt");
                }
                re_linearlayout.setVisibility(View.VISIBLE);
            }
        });
        re_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.fromParts("package", "com.miui.virtualsim", null));
                startActivity(intent);

            }
        });
        github_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/lz233/FuckVirtualsim");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
    //写数据到文件
    private static void WriteStringToFile(String string,String path,String fileName){
        try {
            File file = new File(path);
            if (!file.isDirectory()) {
                file.mkdir();
            }
            FileOutputStream out = new FileOutputStream(path+fileName);
            byte[] b = string.getBytes();
            for (int i = 0; i < b.length; i++) {
                out.write(b[i]);
            }
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //读数据
    private String ReadStringFromFile (String path) {
        String re = "";
        try {
            FileReader reader = new FileReader(path);
            int ch ;
            while ((ch = reader.read()) != -1) {
                re = String.valueOf((char)ch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return re;
    }
    /**
     * 设置状态栏透明
     */
    public static void setTranslucentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //导航栏颜色也可以正常设置
            //window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            attributes.flags |= flagTranslucentStatus;
            //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            //attributes.flags |= flagTranslucentNavigation;
            window.setAttributes(attributes);
        }
    }
}
