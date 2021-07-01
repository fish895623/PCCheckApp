package com.example.myapplication2;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity {
  Button bt;
  Button bt2;
  NotificationManager manager;
  NotificationCompat.Builder builder;
  EditText editText;
  CheckBox checkBox;
  Handler handler;
  Thread thread;

  boolean isThread = false;

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

    // Show Notification
    bt = findViewById(R.id.bt);
    bt.setOnClickListener(v -> isThread = false);

    bt2 = findViewById(R.id.bt2);
    bt2.setOnClickListener(v -> {
      isThread = true;
      thread = new Thread(() -> {
        while (isThread) {
          System.out.println("Works!");
          pingNotification();
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
      thread.start();
    });
  }

  // Notification 알림
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
//            .setContentText(socketCheck(this.btn_Click(), 3389, 10000))
            .setContentText(socketCheck("192.168.0.2", 3389, 10000))
            .setOngoing(true); // 알림 고정

    Notification notification = builder.build();

    manager.notify(1, notification);
  }

  // 텍스트의 데이터 부르기
  public String btn_Click() {
    editText = findViewById(R.id.EditText);
    return editText.getText().toString();
  }

  // Socket 통신
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