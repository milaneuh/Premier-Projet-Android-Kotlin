package com.example.premierprojet

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.premierprojet.beans.WeatherBean
import com.example.premierprojet.databinding.WeatherActivityBinding
import com.example.premierprojet.utils.WSUtils
import java.lang.Exception
import java.util.jar.Manifest


class WeatherActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: WeatherActivityBinding
    val MAIN_ACTIVITY = 27;
    val WEBEX_ACTIVITY = 28;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeatherActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //passage de paramètre
        val param = intent.getStringExtra("toto")
        binding.tv.text = param

        binding.bt.setOnClickListener(this)
    }

    /*------*/
    //MENU
    /*------*/
    /**Crée le meny, MAIN_ACTIVITY est une id pour retrouver l'Item**/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, MAIN_ACTIVITY, 0, "Main Activity")
        menu?.add(0,WEBEX_ACTIVITY,0,"Webex Activity")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MAIN_ACTIVITY) {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else if (item.itemId == WEBEX_ACTIVITY) {
            val intent: Intent = Intent(this, WebExActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /*------*/
    //CLICK
    /*------*/


    override fun onClick(p0: View?) {
        if (p0 == binding.bt) {
            showErrorMessage("");
            //Verifier la permission
            /**Etape 1: Est-ce que on a déjà la permission ?**/
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                //Si permission
                showWeather();
            } else {
                //Sinon on la demande*
                /**Etape 2: On affiche la fenêtre de demande de permission**/
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    0
                )

            }
        }
    }


    fun showWeather() {
        //Récupérer la localisation à l'aide getLastKnowLocation
        var locallisation = getLastKnownLocation()

        showProgressBar(true)
        Thread {
            try {
                //Si on a pas de localisation ->erreur
                if (locallisation == null) {
                    throw Exception("La localisation est nulle")
                } else {
                    //Tout va bien j'affiche ma donnée
                    showWeatherBean(
                        WSUtils.loadWeather(
                            locallisation.longitude,
                            locallisation.longitude
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace() //Ca affiche dans la console le detail de l'erreur
                //un problème j'affiche mon erreur
                e.message?.let { showErrorMessage(it) }
            }
            showProgressBar(false)
        }.start()
    }


    fun getLastKnownLocation(): Location? {
        //Contrôle de la permission
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            return null
        }

        var lm = getSystemService(LOCATION_SERVICE) as LocationManager
        //on teste chaque provider( réseau, GPS..) et on garde la localisation avec la meilleure précision
        return lm.getProviders(true).map { lm.getLastKnownLocation(it) }
            .minByOrNull { it?.accuracy ?: Float.MAX_VALUE }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Verifier la permission
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Si permission
            showWeather()
        }
        //Sinon on affiche un message qu'il faut la permission
        else {
            binding.tv.setText("Il faut autoriser la localisation")
        }
    }
    /* -------------------------------- */
    // Mise àn jour UI
    /* -------------------------------- */

    private fun showErrorMessage(errorMessage: String) {
        runOnUiThread {
            binding.tvError.setText(errorMessage)
            binding.tvError.setVisibility(if (errorMessage.isBlank()) View.GONE else View.VISIBLE)
        }
    }

    //Met à jour la donnée sur l'UIThread
    private fun showWeatherBean(data: WeatherBean) {
        runOnUiThread { binding.tv.text = data.name }
    }

    //Affiche masque la progressBar
    private fun showProgressBar(visible: Boolean) {
        runOnUiThread { binding.pb.setVisibility(if (visible) View.VISIBLE else View.GONE) }
    }
}

