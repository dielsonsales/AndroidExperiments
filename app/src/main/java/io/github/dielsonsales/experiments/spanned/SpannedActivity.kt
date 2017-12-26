package io.github.dielsonsales.experiments.spanned

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import io.github.dielsonsales.experiments.R
import kotlinx.android.synthetic.main.activity_spanned.*

/**
 * Created by dielsonsales on 26/12/17.
 */
class SpannedActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spanned)

        val clickableSpan = object: ClickableSpan() {
            override fun onClick(widget: View?) {
                Toast.makeText(this@SpannedActivity, "Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint?) {
                super.updateDrawState(ds)
                ds?.color = Color.RED
                ds?.isUnderlineText = false
            }
        }

        val spannedText = SpannableString("Hello, @dielsonsales")
        spannedText.setSpan(clickableSpan, 7, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanned_text.highlightColor = Color.TRANSPARENT
        spanned_text.movementMethod = LinkMovementMethod.getInstance()
        spanned_text.text = spannedText
    }

}