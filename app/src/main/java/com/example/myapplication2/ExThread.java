package com.example.myapplication2;

class ExThread extends Thread {
  private final MainActivity mainActivity;

  public ExThread(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  public void run() {
    if (currentThread().isAlive()) {

      System.out.println("alive");
    } else {
      System.out.println("dead");
    }
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    mainActivity.handler.post(this);
  }
}
