package com.example.xwalkembed;

import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkSettings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.Manifest;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
  private XWalkView mXWalkView;
  private static final String[] permissions = {Manifest.permission.RECORD_AUDIO, android.Manifest.permission.CAMERA};
  private List<String> requiredPermissions = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    for (String permission : permissions) {
        requiredPermissions.add(permission);
    }
    if (requiredPermissions.size() != 0) {
      String[] runtimePermissions = new String[requiredPermissions.size()];
      runtimePermissions = requiredPermissions.toArray(runtimePermissions);
      requestPermissions(runtimePermissions, 100);
    }

    XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
    setContentView(R.layout.activity_main);
    mXWalkView = (XWalkView) findViewById(R.id.activity_main);
    XWalkSettings webSettings = mXWalkView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    mXWalkView.addJavascriptInterface(new TelmateJSInterface(), "TelmateJSInterface");
    mXWalkView.load("http://crosswalk-project.org/", null);
  }
}
