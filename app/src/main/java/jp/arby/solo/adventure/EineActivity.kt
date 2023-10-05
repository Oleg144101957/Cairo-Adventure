package jp.arby.solo.adventure

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import jp.arby.solo.adventure.screen.Content1
import jp.arby.solo.adventure.ui.theme.CairoAdventureTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CairoAdventureTheme {
                // A surface container using the 'background' color from the theme
                Content1()
            }
        }

        lifecycleScope.launch {
            delay(2099)
            navigate2()
        }
    }

    private fun navigate2(){
        val intent = Intent(this, ZweiActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        //fixed
    }
}
