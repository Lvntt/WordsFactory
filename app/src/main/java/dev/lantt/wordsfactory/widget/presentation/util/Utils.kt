package dev.lantt.wordsfactory.widget.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.core.content.res.ResourcesCompat
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.ContentScale

fun spToPx(sp: Float, context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        context.resources.displayMetrics
    )
}

fun Context.textAsBitmap(
    text: String,
    fontSize: TextUnit,
    color: Color = Color.Black,
    font: Int,
    letterSpacing: Float
): Bitmap {
    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    with(paint) {
        textSize = spToPx(fontSize.value, this@textAsBitmap)
        setColor(color.toArgb())
        setLetterSpacing(letterSpacing)
        typeface = ResourcesCompat.getFont(this@textAsBitmap, font)
    }

    val baseline = -paint.ascent()
    val width = (paint.measureText(text)).toInt()
    val height = (baseline + paint.descent()).toInt()
    val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(image)
    canvas.drawText(text, 0f, baseline, paint)

    return image
}

fun GlanceModifier.linearGradientBackground(
    startColor: Color,
    endColor: Color
): GlanceModifier {
    val size = 100f

    val linearGradientBitmap = Bitmap.createBitmap(size.toInt(), size.toInt(), Bitmap.Config.ARGB_8888)
    val canvas = Canvas(linearGradientBitmap)
    val gradient = LinearGradient(
        0f,
        size / 2,
        size,
        size / 2,
        startColor.toArgb(), endColor.toArgb(),
        Shader.TileMode.CLAMP
    )

    val paint = Paint()
    paint.shader = gradient

    canvas.drawRect(0f, 0f, size, size, paint)

    return this.background(
        imageProvider = ImageProvider(linearGradientBitmap),
        contentScale = ContentScale.FillBounds
    )
}