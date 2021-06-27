package com.example.myapplication2;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.PrintStream;

public class telnetCheck {
  private TelnetClient telnet;
  private PrintStream out;


  boolean getConnected(String hostname, int port) {
    try {
      telnet.connect(hostname, port);
      telnet.disconnect();
      return true;
    } catch (Exception exception) {
      exception.printStackTrace();
      return false;
    }
  }
}
