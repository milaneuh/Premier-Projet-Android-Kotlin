package com.example.premierprojet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.premierprojet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    /*--------------------------------*/
    // DATA
    /*--------------------------------*/
    final val WEATHER_ACTIVITY = 26
    val WEBEX_ACTIVITY = 28;
    lateinit var binding: ActivityMainBinding
    /*--------------------------------*/
    // ACTIVITY
    /*--------------------------------*/


    /*------*/
    //ON CREATE
    /*------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("Tag", "Coucou les amis !")
        //Chargement de l'interface graphique
        binding = ActivityMainBinding.inflate(layoutInflater)

        //Affichage
        setContentView(binding.root)

        binding.btValider.setOnClickListener(this)
        binding.btAnuler.setOnClickListener(this)
    }

    /*------*/
    //MENU
    /*------*/
    /**Crée le meny, WEATHER_ACTIVITY est une id pour retrouver l'Item**/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,WEATHER_ACTIVITY,0,"Weather Activity")
        menu?.add(0,WEBEX_ACTIVITY,0,"Webex Activity")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == WEATHER_ACTIVITY){
            val intent:Intent = Intent(this,WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }else if (item.itemId == WEBEX_ACTIVITY) {
            val intent: Intent = Intent(this, WebExActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    /*------*/
    //CLIC
    /*------*/
    override fun onClick(p0: View?) {
        if (p0 == binding.btValider) {
            var result: String
            if (binding.rbJaime.isChecked) {
                result = "J'aime"
            } else if (binding.rbJaimepas.isChecked) {
                result = "J'aime pas"
            } else {
                result = ""
            }
            binding.tvButton.setText(result)
            binding.imageView.setImageResource(R.drawable.ic_done)
            binding.imageView.setColorFilter(Color.CYAN)
            Log.w("Clic", "Clic sur le bouton valider")
        } else if (p0 == binding.btAnuler) {
            binding.imageView.setImageResource(R.drawable.ic_deleteforever)
            binding.tvButton.setText("")
            binding.rbGroup.clearCheck()
            binding.imageView.setColorFilter(Color.RED)
            Log.w("Clic", "Clic sur le bouton anuler")
        }
    }

}

