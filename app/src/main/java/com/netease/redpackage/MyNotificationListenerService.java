package com.netease.redpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyNotificationListenerService extends NotificationListenerService {
    @Override
    public void onListenerConnected() {
        //当连接成功时调用，一般在开启监听后会回调一次该方法
        Log.e("onListenerConnected", "666666");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Notification notification = sbn.getNotification();
        if (notification == null) {
            return;
        }
        if (sbn.getPackageName().equals("com.tencent.mm")) {
            //打开对应的聊天界面
            PendingIntent pendingIntent = notification.contentIntent;
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
        Log.e("StatusBarNotification", sbn.toString());
        //当收到一条消息时回调，sbn里面带有这条消息的具体信息
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        //当移除一条消息的时候回调，sbn是被移除的消息
    }

}
