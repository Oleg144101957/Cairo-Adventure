package jp.arby.solo.adventure

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import jp.arby.solo.adventure.screen.Content2
import jp.arby.solo.adventure.ui.theme.CairoAdventureTheme

class ZweiActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CairoAdventureTheme {
                Content2()
            }
        }
    }

    fun navigate3(){
        val intent = Intent(this, DreiActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //fixed
    }

}