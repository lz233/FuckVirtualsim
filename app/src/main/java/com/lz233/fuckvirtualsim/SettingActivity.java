package com.lz233.fuckvirtualsim;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.List;

public class SettingActivity extends Activity {
    private Switch crack_root;
    private Switch hide_discovery;
    private TextView re_text;
    private Button re_butoon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        crack_root = (Switch) findViewById(R.id.crack_root);
        hide_discovery = (Switch) findViewById(R.id.hide_discovery);
        re_text = (TextView) findViewById(R.id.re_text);
        re_butoon = (Button) findViewById(R.id.re_butoon);
        //final SharedPreferences sharedPreferences = getSharedPreferences("setting",MODE_PRIVATE);
        //Toast.makeText(this,ReadStringFromFile(Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/crack_root.txt"),Toast.LENGTH_SHORT).show();
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
        crack_root.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                if (b){
                    //editor.putBoolean("crack_root",true);
                    WriteStringToFile("1",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/crack_root.txt");
                }else {
                    //editor.putBoolean("crack_root",false);
                    WriteStringToFile("0",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/crack_root.txt");
                }
                //editor.commit();
                re_text.setVisibility(View.VISIBLE);
                re_butoon.setVisibility(View.VISIBLE);
            }
        });
        hide_discovery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    WriteStringToFile("1",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/hide_discovery.txt");
                }else {
                    WriteStringToFile("0",Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/hide_discovery.txt");
                }
                re_text.setVisibility(View.VISIBLE);
                re_butoon.setVisibility(View.VISIBLE);
            }
        });
        re_butoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.fromParts("package", "com.miui.virtualsim", null));
                startActivity(intent);

            }
        });
    }
    //写数据到文件
    private static void WriteStringToFile(String string,String path){
        try {
            FileOutputStream out = new FileOutputStream(path);
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

}
