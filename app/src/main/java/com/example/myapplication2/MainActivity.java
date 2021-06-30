package com.example.myapplication2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
  Button bt;
  boolean bt_bool = false;
  NotificationManager manager;
  NotificationCompat.Builder builder;
  EditText editText;

  private static final String channelID = "channel1";
  private static final String channelName = "Channel1";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // To Socket communication need this
    // 소켓통신을 하려면 필요함
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    bt = findViewById(R.id.bt);
    // Show Notification
    bt.setOnClickListener(v -> {
      btn_Click();
      pingNotification();
    });
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
            .setContentText(socketCheck("192.168.0.2", 3389, 1000))
            .setOngoing(true); // 알림 고정

    Notification notification = builder.build();

    manager.notify(1, notification);
  }

  public void btn_Click() {
    editText = findViewById(R.id.EditText);
  }

  public static String socketCheck(String hostname, int port, int timeout) {
    SocketAddress socketAddress = new InetSocketAddress(hostname, port);

    try (Socket socket = new Socket()) {
      socket.connect(socketAddress, timeout);
      return "ALIVE";
    } catch (IOException e) {
      return "DEAD";
    }
  }
}