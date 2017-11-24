package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WifiManagerUtils {

  public static String getPing(String url) {
    String str = "";
    try {
      Process process = Runtime.getRuntime().exec("/system/bin/ping -c 4 " + url);
      BufferedReader reader = new BufferedReader(new InputStreamReader(
          process.getInputStream()));
      int i;
      char[] buffer = new char[4096];
      StringBuilder output = new StringBuilder();
      while ((i = reader.read(buffer)) > 0)
        output.append(buffer, 0, i);
      reader.close();

      str = output.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  @SuppressLint("WifiManagerPotentialLeak")
  public static int getLinkSpeed(Context context) {
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    return wifiManager.getConnectionInfo().getRssi();
  }

  @SuppressLint("WifiManagerPotentialLeak")
  public static int getSignLevel(Context context) {
    Integer linkSpeed = 0;
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    if (wifiInfo != null)
      linkSpeed = wifiInfo.getLinkSpeed();

    return linkSpeed;
  }
}
