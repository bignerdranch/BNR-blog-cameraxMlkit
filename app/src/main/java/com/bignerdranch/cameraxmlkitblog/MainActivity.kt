package com.bignerdranch.cameraxmlkitblog

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

private const val CAMERA_PERMISSION_REQUEST_CODE = 101

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (hasCameraPermissions()) {
      supportFragmentManager.beginTransaction()
          .add(R.id.content_area, CameraFragment())
          .commit()
    } else {
      requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
    }
  }

  private fun hasCameraPermissions(): Boolean {
    return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        supportFragmentManager.beginTransaction()
            .add(R.id.content_area, CameraFragment())
            .commit()
      }
    }
  }
}
