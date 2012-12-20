package com.example.demo.test;

import java.util.ArrayList;

import android.view.KeyEvent;
import android.widget.Button;

import com.baidu.cafe.CafeTestCase;
import com.baidu.cafe.local.LocalLib;
import com.baidu.cafe.local.Log;
import com.baidu.cafe.local.NetworkUtils;

/**
 * @author luxiaoyu01@baidu.com
 * @date 2012-6-27
 * @version
 * @todo
 */
public class TestCafe extends CafeTestCase {
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.baidu.news.MainActivity";
    private static Class<?>     launcherActivityClass;
    private static final String TARGET_PACKAGE                   = "com.baidu.news";
    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TestCafe() {
        super(TARGET_PACKAGE, launcherActivityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * NOTICE: 运行case前需要开启cafe_setup.bat
     */
    public void test_sample() {
        local.beginRecordCode();
        local.sleep(2000000);
    }

    private void history() {
        Log.i("###", "" + new NetworkUtils().getPackageRcv("com.baidu.BaiduMap"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("###", "" + new NetworkUtils().getPackageRcv("com.baidu.BaiduMap"));
        remote.pressKey(KeyEvent.KEYCODE_HOME);
        //点击锁屏键
        remote.pressKey(KeyEvent.KEYCODE_POWER);
        //点击音量键
        remote.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        //点击菜单键
        remote.pressKey(KeyEvent.KEYCODE_MENU);
        //点击返回键
        remote.pressKey(KeyEvent.KEYCODE_BACK);
        //点击搜索键
        remote.pressKey(KeyEvent.KEYCODE_SEARCH);
        //卸载sd卡
        remote.unmount();
        //安装sd卡
        remote.mount();
        //杀后台进程
        remote.killBackgroundProcesses("com.android.launcher");
        //启动其他应用
        remote.launchActivity("com.android.launcher");
        //输入字符
        local.enterText(0, "Cafe");//0表示第一个搜索框
        //长按
        //        local.clickLongOnView(fakeView);
        local.clickLongInList(1);
        local.clickLongOnText("Cafe");
        local.clickLongOnScreen(100, 100);
        //点击文字（任何控件上的文字）
        local.clickOnText("Cafe");
        //获取控件
        local.getCurrentViews(Button.class);
        //获取logcat
        String[] logs = remote.getLog();
        //获取电量
        remote.getBatteryLevel();
        //获取亮度
        remote.getScreenBrightness();
        //从(0,0)拖动到(100,100)
        local.drag(0, 100, 0, 100, 10);
        //放大缩小
        /**
         * zoom screen
         * 
         * @param start
         *            the start position e.g. new int[]{0,0,1,2}; means two
         *            pointers start at {0,0} and {1,2}
         * @param end
         *            the end position e.g. new int[]{100,110,200,220}; means
         *            two pointers end at {100,110} and {200,220}
         */
        local.zoom(new int[] { 0, 0, 1, 2 }, new int[] { 100, 110, 200, 220 });
        //跨进程点击
        remote.clickViewByText("通讯录");
        if (remote.waitForActivity("com.android.contacts.DialtactsContactsEntryActivity", 5000)) {
            Log.i("at com.android.contacts.DialtactsContactsEntryActivity");
        }
    }
}
