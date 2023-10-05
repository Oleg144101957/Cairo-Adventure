package jp.arby.solo.adventure

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import jp.arby.solo.adventure.screen.Content3
import jp.arby.solo.adventure.ui.theme.CairoAdventureTheme

class DreiActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CairoAdventureTheme {
                Content3()
            }
        }
    }

    fun navigate2(){
        val intent = Intent(this, ZweiActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //fixed
    }
}