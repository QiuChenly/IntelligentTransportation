package com.example.liunianyishi.intelligenttransportation.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.example.liunianyishi.intelligenttransportation.R;
import com.example.liunianyishi.intelligenttransportation.Util.mSharedContext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;

import static com.example.liunianyishi.intelligenttransportation.Util.mSharedContext.SDCardFile;

public class WelcomeActivity extends AppCompatActivity {


    //回调此函数,重写一下实现自定义
    //告诉客户:不给权限还想用APP?Life in dream?
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2333) {
            int i = 0;
            for (int a : grantResults) {
                //某个权限被用户拒绝了,此时嘲讽一句用户足矣
                if (a == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "SB!你刚刚拒绝了!你一定会后悔的!\n" + permissions[i],
                            Toast.LENGTH_SHORT).show();
                }
                ++i;
            }
            gotoNext();
        }
    }

    void gotoNext() {
        /*
         * 其实这个是个写文件的例子
         */
        File f = new File(SDCardFile + "/");
        if (!f.exists()) {
            boolean r = f.mkdirs();
            System.out.println("创建文件夹:" + r);
        }
        String FBIWARNING = "警告!本文档只适合18岁以上人员观看,简称:18X.无关人员请绕道.";
        f = new File(SDCardFile + "/NB.txt");
        if(f.exists()){
            f.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(FBIWARNING.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream ip = new FileInputStream(f);
            byte[] a =getByte(ip);
            System.out.println(new String(a));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, 3000);
    }


    //这里演示了如何从InputStream中读取为Bytes字节
    byte[] getByte(InputStream i) throws IOException {
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        int len;
        byte[] b = new byte[1024];
        while ((len = i.read(b)) != -1) {
            op.write(b, 0, len);
        }
        return op.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_welcome);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        //Android 6.0+ Google升级了权限管理,需要动态请求,仅限Target 23+的时候
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] 权限狗 = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,//写入文件系统
                    Manifest.permission.READ_EXTERNAL_STORAGE,//读取文件系统
                    Manifest.permission.INTERNET,//获取网络数据
                    Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS//挂载/取消挂载文件系统
            };
            //检查自己有没有这个权限,防止某些情况下用户手贱点了拒绝
            requestPermissions(权限狗, 2333);
        } else
            //当Target version < 23的时候,直接得到权限
            //当前涩会上很多手机Android版本已经升级到6.x+的版本,无须担心设备覆盖率问题
            //以上代码仅对于Android 6.0+的设备做适配
            gotoNext();
    }
}
