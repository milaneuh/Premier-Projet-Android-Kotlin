package com.example.premierprojet

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.premierprojet.databinding.ActivityWebExBinding
import com.example.premierprojet.utils.OkhttpUtils

class WebExActivity : AppCompatActivity(), View.OnClickListener {
    /*------*/
    //MENU ID
    /*------*/
    private val WEATHER_ACTIVITY = 26
    private val MAIN_ACTIVITY = 27

    /*------*/
    //COMPOSANTS GRAPHIQUES
    /*------*/
    lateinit var binding: ActivityWebExBinding



    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("Tag", "Coucou les amis !")
        //Chargement de l'interface graphique
        binding = ActivityWebExBinding.inflate(layoutInflater)

        //Affichage
        setContentView(binding.root)

        //OnClickListener
        binding.btWeb.setOnClickListener(this)

        /*--------------------------------*/
        // WEBVIEW
        /*--------------------------------*/
        //On assigne la webView
        var myWebView: WebView = binding.webView

        //Sinon cela lance le navigateur du téléphone
        myWebView.webViewClient = WebViewClient()

        //Activier le Javascript (Attention aux performances)
        val webviewSettings: WebSettings = myWebView.settings
        webviewSettings.javaScriptEnabled = true
    }


    /*------*/
    //MENU
    /*------*/
    /**Crée le meny, MAIN_ACTIVITY,WEATHER_ACTIVITY est une id pour retrouver l'Item**/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, MAIN_ACTIVITY, 0, "Main Activity")
        menu?.add(0, WEATHER_ACTIVITY, 0, "Weather Activity")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MAIN_ACTIVITY) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else if (item.itemId == WEATHER_ACTIVITY) {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    /*------*/
    //ON CLICK
    /*------*/

    override fun onClick(p0: View?) {
        if (p0 == binding.btWeb) {
                //Si Permission
                loadWebPage()
        }
    }

    private fun loadWebPage() {
        //On récupère l'URL
        val webUrl: String = binding.tvCodehtml.text.toString()
        binding.webView.loadUrl(webUrl)
        Thread {
            Log.w("TAG","Thread lancé.")
            try {
                runOnUiThread {
                    binding.tvCodehtml.text = OkhttpUtils.sendGetOkHttpRequest(webUrl)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

