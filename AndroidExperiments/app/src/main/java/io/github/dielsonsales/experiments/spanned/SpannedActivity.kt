package io.github.dielsonsales.experiments.spanned

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.BaseMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.dielsonsales.experiments.R
import kotlinx.android.synthetic.main.activity_spanned.*

/**
 * Created by dielsonsales on 26/12/17.
 */
class SpannedActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spanned)

        val spannedText = SpannableString("Hello, @dielsonsales")
        spannedText.setSpan(CustomClickableSpan(this@SpannedActivity), 7, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanned_text.highlightColor = Color.TRANSPARENT
        spanned_text.movementMethod = CustomLinkMovementMethod() //LinkMovementMethod.getInstance()
        spanned_text.text = spannedText
    }

    class CustomLinkMovementMethod: BaseMovementMethod() {

        // Based on the LinkMovementMethod source code
        override fun onTouchEvent(widget: TextView?, text: Spannable?, event: MotionEvent?): Boolean {
            if (event != null) {

                val x = event.x.toInt()
                val y = event.y.toInt()

                val layout = widget?.layout
                val line = layout?.getLineForVertical(y)
                val off = layout?.getOffsetForHorizontal(line!!, x.toFloat())

                val link = text?.getSpans(off!!, off, ClickableSpan::class.java)

                if (event.action == MotionEvent.ACTION_DOWN) {
                    Log.d("SpannedActivity", "ACTION_DOWN")
                    if (link != null && link.isNotEmpty()) {
                        val clickableSpan = link[0] as CustomClickableSpan
                        clickableSpan.ispressed = true
                        widget?.invalidate()
                    }
                } else if (event.action == MotionEvent.ACTION_UP) {
                    Log.d("SpannedActivity", "ACTION_UP")
                    if (link != null && link.isNotEmpty()) {
                        val clickableSpan = link[0] as CustomClickableSpan
                        clickableSpan.ispressed = false
                        widget?.invalidate()
                        if (widget != null) clickableSpan.onClick(widget);
                    }
                }
            }
            return super.onTouchEvent(widget, text, event)
        }
    }

    class CustomClickableSpan(private val context: Context): ClickableSpan() {

        var ispressed: Boolean = false

        override fun onClick(widget: View) {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = if (ispressed) Color.BLUE else Color.RED
            ds.isUnderlineText = false
        }
    }

}