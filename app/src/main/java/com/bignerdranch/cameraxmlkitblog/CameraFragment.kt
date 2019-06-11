package com.bignerdranch.cameraxmlkitblog

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Rational
import android.util.Size
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.fragment.app.Fragment

class CameraFragment : Fragment() {

  private lateinit var cameraView: TextureView
  private lateinit var overlayView: FacePointsView

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_camera, container, false)

    cameraView = view.findViewById(R.id.camera_view)
    overlayView = view.findViewById(R.id.face_points_view)

    cameraView.post {
      setUpCameraX()
    }

    return view
  }

  private fun setUpCameraX() {
    CameraX.unbindAll()

    val displayMetrics = DisplayMetrics().also { cameraView.display.getRealMetrics(it) }
    val screenSize = Size(displayMetrics.widthPixels, displayMetrics.heightPixels)
    val aspectRatio = Rational(displayMetrics.widthPixels, displayMetrics.heightPixels)
    val rotation = cameraView.display.rotation

    val autoFitPreviewAnalysis = AutoFitPreviewAnalysis.build(screenSize, aspectRatio, rotation, cameraView, overlayView)

    CameraX.bindToLifecycle(this, autoFitPreviewAnalysis.previewUseCase, autoFitPreviewAnalysis.analysisUseCase)
  }
}