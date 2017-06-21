package com.misu.core;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.misu.utils.CrashHandler;

import java.io.File;



/**
 * Created by halifa on 2017/6/7.
 */

public class WBDApplication extends Application {

    private static Context context;
    private  static Handler handler;
    private static int mainThreadId;

    // 文件保存路径
    private static final File DIR = new File(
            Environment.getExternalStorageDirectory(), "baigujing");
    public static final String PICTURE = "picture";



    @Override
    public void onCreate() {//24009544
        super.onCreate();
        createDir();
        init();
//        RongIM.init(this);


//        final String APP_KEY = "23015524";
//        final String APP_KEY = "24300338";
//必须首先执行这部分代码, 如果在":TCMSSevice"进程中，无需进行云旺（OpenIM）和app业务的初始化，以节省内存;
//        SysUtil.setApplication(this);
//        if(SysUtil.isTCMSServiceProcess(this)){
//            return;
//        }
//第一个参数是Application Context
//这里的APP_KEY即应用创建时申请的APP_KEY，同时初始化必须是在主进程中
//        if(SysUtil.isMainProcess()){
//            YWAPI.init(this, APP_KEY);
//        }

    }

    private void init() {
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

/**
 * 创建log日志文件
 */
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(),getAppDir()
                .getAbsolutePath());



    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    /**
     * 创建app文件夹
     */
    private static void createDir() {
        if (!DIR.exists()) {
            DIR.mkdirs();
        }
    }

    /**
     * 获取app文件夹
     *
     * @return
     */
    public static File getAppDir() {
        if (DIR != null && DIR.exists()) {
        } else {
            createDir();
        }
        return DIR;
    }


    /**
     * 获取图片缓存文件夹
     *
     * @return
     */
    public static File getPictureDir() {
        File pictureDir = new File(getAppDir(), PICTURE);
        if (!pictureDir.exists()) {
            pictureDir.mkdirs();
        }
        return pictureDir;
    }


}
