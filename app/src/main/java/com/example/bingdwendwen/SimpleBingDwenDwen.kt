package com.example.bingdwendwen

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class SimpleBingDwenDwen(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val strokePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
        textSize = 48f
    }
    val strokePaintWhite = Paint().apply {
        color = Color.WHITE
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }
    val strokePaintBlue = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }
    val strokePaintRed = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }
    val strokePaintGreen = Paint().apply {
        color = Color.GREEN
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    val fillPaint = Paint(Color.BLACK).apply {
        strokeWidth = 5f
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    val fillPaintWhite = Paint().apply {
        color = Color.WHITE
        strokeWidth = 5f
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    val fillPaintRed = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    val strokePath = Path()
    val strokePathBlue = Path()
    val strokePathRed = Path()
    val strokePathGreen = Path()
    val fillPath = Path()
    val fillPathRed = Path()

    // body
    val startPoint = PointF(200f, 600f)
    val topLeftControlPoint = PointF(250f, 100f)
    val topRightControlPoint = PointF(750f, 100f)
    val endPoint = PointF(800f, 600f)
    val bottomLeftControlPoint = PointF(750f, 1200f)
    val bottomRightControlPoint = PointF(250f, 1200f)

    val leftEyeSocketRectF = RectF(372.96387f, 380.97656f, 451.96655f, 444.96094f)
    val leftEyeRectF = RectF(384.96094f, 391.9336f, 431.96045f, 426.97266f)

    val rightEyeSocketRectF = RectF(572.96387f, 380.97656f, 651.96655f, 444.96094f)
    val rightEyeRectF = RectF(589.9658f, 385.95703f, 624.96826f, 415.95703f)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        // body
        strokePath.moveTo(startPoint.x, startPoint.y)
        strokePath.cubicTo(topLeftControlPoint.x, topLeftControlPoint.y, topRightControlPoint.x, topRightControlPoint.y, endPoint.x, endPoint.y)
        strokePath.cubicTo(bottomLeftControlPoint.x, bottomLeftControlPoint.y, bottomRightControlPoint.x, bottomRightControlPoint.y, startPoint.x, startPoint.y)

        // ears
        canvas.drawArc(300f, 250f, 400f, 350f, 160f, 180f, true, fillPaint)
        canvas.drawArc(650f, 250f, 750f, 350f, 200f, 180f, true, fillPaint)

        // hands
        fillPath.moveTo(202f, 632f)
        fillPath.cubicTo(123f, 644f, 137f, 734f, 211f, 685f)

        fillPath.moveTo(786f, 517f)
        fillPath.cubicTo(832f, 509f, 853f, 550f, 797f, 573f)

        // legs
        fillPath.moveTo(398f, 1028f)
        fillPath.cubicTo(384f, 1095f, 448f, 1110f, 445f, 1045f)

        fillPath.moveTo(583f, 1031f)
        fillPath.cubicTo(571f, 1072f, 636f, 1069f, 621f, 1001f)

        // mufflers
        strokePathRed.moveTo(322.9651f, 567.9492f)
        strokePathRed.cubicTo(187.96509f, 127.96875f, 827.96265f, 133.94531f, 668.9685f, 588.9258f)
        canvas.drawLine(322.9651f, 567.9492f, 668.9685f, 588.9258f, strokePaintRed)

        strokePathGreen.moveTo(357.96753f, 550.95703f)
        strokePathGreen.cubicTo(149.96338f, 124.98047f, 918.9624f, 177.94922f, 636.96533f, 547.96875f)
        canvas.drawLine(357.96753f, 550.95703f, 636.96533f, 547.96875f, strokePaintGreen)

        strokePathBlue.moveTo(348.96973f, 556.9336f)
        strokePathBlue.cubicTo(161.99341f, 221.95312f, 926.97144f, 163.94531f, 662.97f, 547.96875f)
        canvas.drawLine(348.96973f, 556.9336f, 662.97f, 547.96875f, strokePaintBlue)


        // eyes
        canvas.drawArc(leftEyeSocketRectF, 0f, 360f, true, fillPaint)
        canvas.drawArc(leftEyeRectF, 0f, 360f, false, strokePaintWhite)
        canvas.drawCircle(407.9663f, 400.95703f, 6f, fillPaintWhite)
        canvas.drawArc(rightEyeSocketRectF, 0f, 360f, true, fillPaint)
        canvas.drawArc(rightEyeRectF, 0f, 360f, false, strokePaintWhite)
        canvas.drawCircle(604.96216f, 394.98047f, 6f, fillPaintWhite)


        // mouse
        fillPath.moveTo(475.9607f, 470.97656f)
        fillPath.cubicTo(425.9619f, 532.96875f, 612.9712f, 570.9375f, 551.9641f, 491.95312f)
        fillPath.cubicTo(539.96704f, 509.9414f, 486.969f, 500.97656f, 475.9607f, 470.97656f)

        // tongue
        fillPathRed.moveTo(489.96826f, 520.95703f)
        fillPathRed.cubicTo(492.96753f, 503.96484f, 524.9707f, 509.9414f, 530.96924f, 529.9219f)

        // Beijing 2022
        canvas.drawText("Beijing 2022", 351.969f, 778.9453f, strokePaint)

        // TODO: 5 circles

        canvas.drawPath(strokePathRed, strokePaintRed)
        canvas.drawPath(strokePathGreen, strokePaintGreen)
        canvas.drawPath(strokePathBlue, strokePaintBlue)
        canvas.drawPath(strokePath, strokePaint)
        canvas.drawPath(fillPath, fillPaint)
        canvas.drawPath(fillPathRed, fillPaintRed)
    }

    override fun setOnTouchListener(l: OnTouchListener?) {
        super.setOnTouchListener(l)
        Log.d("~~~", "")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("~~~", "${event?.x}f, ${event?.y}f,")
        return super.onTouchEvent(event)
    }
}