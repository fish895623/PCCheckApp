package com.example.myapplication2;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

public class ExThread extends Thread {
  private final MainActivity mainActivity;
  boolean flags;

  public ExThread(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  public void run() {
    if (mainActivity.checkBox.isChecked()) {
      System.out.println("alive");
    } else {
      System.out.println("dead");
    }
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    if (!mainActivity.checkBox.isChecked()) {
//      mainActivity.thread.interrupt();
//    }
//    mainActivity.handler.post(this);
  }
}

class LoopThread {
  Thread t = new Thread(() -> {
    Looper.prepare();
    Handler handler = new Handler(Looper.getMainLooper());
    Looper.loop();
  });
}