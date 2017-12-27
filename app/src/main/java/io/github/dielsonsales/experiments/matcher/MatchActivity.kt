package io.github.dielsonsales.experiments.matcher

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import io.github.dielsonsales.experiments.R
import kotlinx.android.synthetic.main.activity_match.*

/**
 * Created by dielsonsales on 27/12/17.
 */
class MatchActivity: AppCompatActivity() {

    var xCoord: Float = 0.0f;
    var yCoord: Float = 0.0f;
    var scrollViewLocked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val display = windowManager.defaultDisplay
        var point = Point()
        display.getSize(point)
        var displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)
        var density = displayMetrics.density

        match_scroll_view.setOnTouchListener { view, event ->
            xCoord = event.x / density
            yCoord = event.y / density
            val topPosition = 300.0 - (match_scroll_view.scrollY / density)
            when {
                event.action == MotionEvent.ACTION_DOWN -> {
                    if (yCoord < topPosition) {
                        scrollViewLocked = true
                    }
                    false
                }
                event.action == MotionEvent.ACTION_UP -> {
                    scrollViewLocked = false
                    false
                }
                event.action == MotionEvent.ACTION_MOVE -> scrollViewLocked
                else -> false
            }
        }
    }

}