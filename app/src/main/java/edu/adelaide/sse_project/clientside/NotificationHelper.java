package edu.adelaide.sse_project.clientside;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

public class NotificationHelper {
    @FunctionalInterface
    public interface NotificationPermissionCallback {
        void callback(boolean notificationsEnabled);
    }

    public static NotificationCompat.Builder buildNotification(NotificationPermissionCallback callback, Activity context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String channelID = context.getClass().getCanonicalName();
        if (channelID == null || channelID.isEmpty())
            channelID = "SSE_PROJECT_CLIENTSIDE";
        manager.createNotificationChannel(new NotificationChannel(channelID, context.getClass().getSimpleName(), NotificationManager.IMPORTANCE_DEFAULT));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID);

        boolean notificationPermissionGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        if (notificationPermissionGranted)
            callback.callback(true);
        else
            context.requestPermissions(new String[] { Manifest.permission.POST_NOTIFICATIONS }, context.getClass().hashCode());

        return builder;
    }
}
