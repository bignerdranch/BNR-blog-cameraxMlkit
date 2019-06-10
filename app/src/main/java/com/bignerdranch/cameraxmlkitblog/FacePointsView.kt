package com.bignerdranch.cameraxmlkitblog

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class FacePointsView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = -1
) : View(context, attrs, defStyleAttr) {

  private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.BLUE
    style = Paint.Style.FILL
  }

  var points = listOf<PointF>()
    set(value) {
      field = value
      invalidate()
    }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    canvas.apply {
      for (point in points) {
        drawCircle(point.x, point.y, 8f, pointPaint)
      }
    }
  }
}