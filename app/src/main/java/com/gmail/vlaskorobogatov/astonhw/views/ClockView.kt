package com.gmail.vlaskorobogatov.astonhw.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.gmail.vlaskorobogatov.astonhw.R
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        context.withStyledAttributes(attrs, R.styleable.ClockView) {
            hourColor = getColor(R.styleable.ClockView_hourColor, Color.RED)
            minuteColor = getColor(R.styleable.ClockView_minuteColor, Color.BLUE)
            secondColor = getColor(R.styleable.ClockView_secondColor, Color.BLACK)

            hourLength = getDimension(R.styleable.ClockView_hourLength, radius * 0.4f)
            minuteLength = getDimension(R.styleable.ClockView_minuteLength, radius * (2f / 3f))
            secondLength = getDimension(R.styleable.ClockView_secondLength, radius * 0.5f)

            hourWidth = getDimension(R.styleable.ClockView_hourWidth, 30f)
            minuteWidth = getDimension(R.styleable.ClockView_minuteWidth, 20f)
            secondWidth = getDimension(R.styleable.ClockView_secondWidth, 15f)
        }
    }

    private var radius = 0.0f
    private var centerX = 0.0f
    private var centerY = 0.0f

    private var hourLength = 0.0f
    private var minuteLength = 0.0f
    private var secondLength = 0.0f

    private var hourWidth = 0.0f
    private var minuteWidth = 0.0f
    private var secondWidth = 0.0f

    private var hourColor = 0
    private var minuteColor = 0
    private var secondColor = 0

    private val strokeCircle = 30f
    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        val hours = Calendar.getInstance().get(Calendar.HOUR)
        val minutes = Calendar.getInstance().get(Calendar.MINUTE)
        val seconds = Calendar.getInstance().get(Calendar.SECOND)
        paint.reset()
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = strokeCircle
        }

        canvas?.drawCircle(centerX, centerY, radius, paint)
        for (i in 1..12) {
            canvas?.drawLine(
                centerX + radius * 0.8f * sin(i * PI / 6).toFloat(),
                centerY - radius * 0.8f * cos(i * PI / 6).toFloat(),
                centerX + radius * sin(i * PI / 6).toFloat(),
                centerY - radius * cos(i * PI / 6).toFloat(),
                paint
            )
        }

        paint.apply {
            color = hourColor
            strokeWidth = hourWidth
        }
        canvas?.drawLine(
            centerX - hourLength * 0.25f * sin(hours * PI / 6 + minutes * PI / 360 + seconds * PI / 21600).toFloat(),
            centerY + hourLength * 0.25f * cos(hours * PI / 6 + minutes * PI / 360 + seconds * PI / 21600).toFloat(),
            centerX + hourLength * sin(hours * PI / 6 + minutes * PI / 360 + seconds * PI / 21600).toFloat(),
            centerY - hourLength * cos(hours * PI / 6 + minutes * PI / 360 + seconds * PI / 21600).toFloat(),
            paint
        )

        paint.apply {
            color = minuteColor
            strokeWidth = minuteWidth
        }
        canvas?.drawLine(
            centerX - minuteLength * 0.25f * sin(minutes * PI / 30 + seconds * PI / 1800).toFloat(),
            centerY + minuteLength * 0.25f * cos(minutes * PI / 30 + seconds * PI / 1800).toFloat(),
            centerX + minuteLength * sin(minutes * PI / 30 + seconds * PI / 1800).toFloat(),
            centerY - minuteLength * cos(minutes * PI / 30 + seconds * PI / 1800).toFloat(),
            paint
        )

        paint.apply {
            color = secondColor
            strokeWidth = secondWidth
        }
        canvas?.drawLine(
            centerX - secondLength * 0.25f * sin(seconds * PI / 30).toFloat(),
            centerY + secondLength * 0.25f * cos(seconds * PI / 30).toFloat(),
            centerX + secondLength * sin(seconds * PI / 30).toFloat(),
            centerY - secondLength * cos(seconds * PI / 30).toFloat(),
            paint
        )

        postInvalidateDelayed(500)
        invalidate()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (minOf(w, h) - strokeCircle) / 2f
        centerX = w / 2f + paddingLeft - paddingRight
        centerY = h / 2f + paddingTop - paddingBottom
        if (hourLength == 0f) {
            hourLength = radius * 0.4f
        }
        if (minuteLength == 0f) {
            minuteLength = radius * (2f / 3f)
        }
        if (secondLength == 0f) {
            secondLength = radius * 0.5f
        }
    }
}