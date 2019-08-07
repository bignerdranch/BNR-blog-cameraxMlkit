package com.bignerdranch.cameraxmlkitblog

import android.content.Context
import android.graphics.*
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
      transformPoints()
    }

  var transform = Matrix()
    set(value) {
      field = value
      transformPoints()
    }

  private var drawingPoints = listOf<PointF>()
    set(value) {
      field = value
      invalidate()
    }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    canvas.apply {
      for (point in drawingPoints) {
        drawCircle(point.x, point.y, 8f, pointPaint)
      }
    }
  }

  private fun transformPoints() {
    // build src and dst
    val transformInput = points.flatMap { listOf(it.x, it.y) }.toFloatArray()
    val transformOutput = FloatArray(transformInput.size)

    // apply the matrix transformation
    transform.mapPoints(transformOutput, transformInput)

    // convert transformed FloatArray to List<Point>
    drawingPoints = transformOutput.asList()
      .chunked(size = 2, transform = { (x, y) -> PointF(x, y) })
  }
}