package io.github.dielsonsales.experiments.spanned

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.dielsonsales.experiments.R
import kotlinx.android.synthetic.main.activity_spanned.*

/**
 * Created by dielsonsales on 26/12/17.
 */
class SpannedActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spanned)

        spanned_text.text = "Okay, hehe"
    }

}