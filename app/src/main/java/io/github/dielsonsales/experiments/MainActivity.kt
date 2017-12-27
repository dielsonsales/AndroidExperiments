package io.github.dielsonsales.experiments

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.util.Log
import android.widget.ArrayAdapter
import io.github.dielsonsales.experiments.matcher.MatchActivity
import io.github.dielsonsales.experiments.spanned.SpannedActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        mainAdapter.addAll(arrayListOf(
                "Spanned Activity",
                "Match Activity"
        ))

        list_view.adapter = mainAdapter

        list_view.setOnItemClickListener { parent, view, position, id ->
            Log.d("MainActivity", "On item click, position: " + position)
            when(position) {
                0 -> startActivity(Intent(this@MainActivity, SpannedActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, MatchActivity::class.java))
            }
        }
    }
}
