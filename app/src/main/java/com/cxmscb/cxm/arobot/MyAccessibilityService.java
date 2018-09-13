package com.cxmscb.cxm.arobot;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxm on 2016/10/26.
 */
public class MyAccessibilityService extends AccessibilityService {


    String nowPackageName;
    private boolean isComplete;
    private boolean isComplete2;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        nowPackageName = event.getPackageName().toString();
        if (nowPackageName.equals("com.eg.android.AlipayGphone") && MyApplication.getInstance().getFlag()) {
            if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                AccessibilityNodeInfo rootNode = this.getRootInActiveWindow();
                iterateNodesAndHandle(rootNode);

            }
        }

    }

    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private boolean iterateNodesAndHandle(AccessibilityNodeInfo info) {
//        if (info.getChildCount() == 0) {
//            if(info.getText() != null&&info.getText().toString().contains("搜索")){
//                if("输入商家、分类或商圈".equals(info.getText().toString())&&"android.widget.TextView".equals(info.getClassName())){
//                    Toast.makeText(this," "+info.getText().toString(),Toast.LENGTH_SHORT).show();
//                    AccessibilityNodeInfo parent = info;
//                    while (parent!=null){
//                        if(parent.isClickable()){
//                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            break;
//                        }
//                        parent = parent.getParent();
//                    }
//                }else if("输入商家、分类或商圈".equals(info.getText().toString())&&"android.widget.EditText".equals(info.getClassName())){
//                    Toast.makeText(this," "+info.getText().toString(),Toast.LENGTH_SHORT).show();
//                    ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
//                    ClipData clipData =ClipData.newPlainText("scb", MyApplication.getInstance().getParams());
//                    clipboardManager.setPrimaryClip(clipData);
//                    info.performAction(AccessibilityNodeInfo.ACTION_PASTE);
//                }else if("搜索".equals(info.getText().toString())&&"android.widget.TextView".equals(info.getClassName())){
//                    Toast.makeText(this," "+info.getText().toString(),Toast.LENGTH_SHORT).show();
//                    AccessibilityNodeInfo parent = info;
//                    while (parent!=null){
//                        if(parent.isClickable()){
//                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            break;
//                        }
//                        parent = parent.getParent();
//                    }
//                    MyApplication.getInstance().setFlag(false);
//                    return true;
//                }else {
//                    MyApplication.getInstance().setFlag(false);
//                }
//            }
//
//        } else {
//            for (int i = 0; i < info.getChildCount(); i++) {
//                if(info.getChild(i)!=null){
//                    iterateNodesAndHandle(info.getChild(i));
//                }
//            }
//        }
//        return false;
//    }
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean iterateNodesAndHandle(final AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            if (info.getText() != null && info.getText().toString().contains("设置金额")) {
                AccessibilityNodeInfo parent = info;
                while (parent != null) {
                    if (parent.isClickable()) {
                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        break;
                    }
                    parent = parent.getParent();
                }
            } else if ("android.widget.EditText".equals(info.getClassName())) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Bundle arguments = new Bundle();
//                    arguments.putCharSequence(
//                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "1.25");
//                    info.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//                }else{
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Label", "1.25");
                    clipboard.setPrimaryClip(clip);
                    info.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                    info.performAction(AccessibilityNodeInfo.ACTION_PASTE);
//                }
                isComplete = true;
            }
            if (info.getText() != null && info.getText().toString().contains("添加收款理由")) {
                if (isComplete) {
                    AccessibilityNodeInfo parent = info;
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }
                }

            } else if ("android.widget.EditText".equals(info.getClassName())) {
                if (isComplete) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Bundle arguments = new Bundle();
//                        arguments.putCharSequence(
//                                AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "6666666.25");
//                        info.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//                    }else{
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Label2", "6666666");
                        clipboard.setPrimaryClip(clip);
                        info.getParent().performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                        info.getParent().performAction(AccessibilityNodeInfo.ACTION_PASTE);
//                    }

                    isComplete2 = true;
                }
            }
//            } else if (info.getText() != null && info.getText().toString().contains("确定")) {
//                if(isComplete2) {
//                    AccessibilityNodeInfo parent = info;
//                    while (parent != null) {
//                        if (parent.isClickable()) {
//                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            break;
//                        }
//                        parent = parent.getParent();
//                    }
//                }
//            }

        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if (info.getChild(i) != null) {
                    iterateNodesAndHandle(info.getChild(i));
                }
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {
        Toast.makeText(this, "zz", Toast.LENGTH_SHORT).show();
    }
}
