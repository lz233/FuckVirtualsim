package com.lz233.fuckvirtualsim;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(lpparam.packageName.equals("com.miui.virtualsim")){
            /*File file = new File("/data/data/com.lz233.fuckvirtualsim/com.lz233.fuckvirtualsim/shared_prefs/setting.xml");
            final XSharedPreferences xSharedPreferences = new XSharedPreferences(file);
            xSharedPreferences.makeWorldReadable();
            xSharedPreferences.reload();*/
            //Toast crack_root = Toast.makeText(this, String.valueOf(xSharedPreferences.getBoolean("crack_root", true)), Toast.LENGTH_SHORT).show();
            if (!ReadStringFromFile(Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/crack_root.txt").equals("0")) {
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.RootUtil", lpparam.classLoader, "isDeviceRooted", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "a", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "b", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "e", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "f", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "g", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "c", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.mimobile.utils.q", lpparam.classLoader, "d", XC_MethodReplacement.returnConstant(false));
                XposedHelpers.findAndHookMethod("com.miui.virtualsim.utils.ShellUtils", lpparam.classLoader, "a", XC_MethodReplacement.returnConstant(false));
            }
            if (!ReadStringFromFile(Environment.getExternalStorageDirectory().toString()+"/Android/data/com.lz233.fuckvirtualsim/hide_discovery.txt").equals("0")) {
                XposedHelpers.findAndHookMethod("com.miui.mimobile.ui.MmMainActivity", lpparam.classLoader,
                        "onCreate", Bundle.class,
                        new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //找到发现
                                LinearLayout linearLayout = getHookView(param, "mFindTabView");
                                // 设置属性,隐藏
                                if (linearLayout != null) {
                                    linearLayout.setVisibility(View.GONE);
                                }

                            }
                        }
                );
            }
        }
    }
    public static <T> T getHookView(XC_MethodHook.MethodHookParam param, String name) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = param.thisObject.getClass();
        // 通过反射获取控件，无论private或者public
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return  (T) field.get(param.thisObject);
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
