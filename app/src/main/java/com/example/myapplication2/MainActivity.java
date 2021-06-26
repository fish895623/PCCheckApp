package com.example.myapplication2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
  Button bt;
  NotificationManager manager;
  NotificationCompat.Builder builder;
  private static final String channelID = "channel1";
  private static final String channelName = "Channel1";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bt = findViewById(R.id.bt);
    // Show Notification
    bt.setOnClickListener(v -> pingNotification());
  }

  // Notification
  public void pingNotification() {
    builder = null;
    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      manager.createNotificationChannel(
              new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
      );
      builder = new NotificationCompat.Builder(this, channelID);
    } else {
      // Under SDK version 26
      //noinspection deprecation
      builder = new NotificationCompat.Builder(this);
    }
    builder
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("알림")
            .setContentText("메세지");

    Notification notification = builder.build();

    manager.notify(1, notification);
  }
}