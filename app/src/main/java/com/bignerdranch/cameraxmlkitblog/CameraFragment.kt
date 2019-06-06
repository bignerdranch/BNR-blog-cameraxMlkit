package com.bignerdranch.cameraxmlkitblog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CameraFragment : Fragment() {

  private lateinit var cameraView: TextureView

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_camera, container, false)

    cameraView = view.findViewById(R.id.camera_view)

    return view
  }
}