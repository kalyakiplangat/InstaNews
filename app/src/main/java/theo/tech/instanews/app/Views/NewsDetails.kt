package theo.tech.instanews.app.Views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import theo.tech.instanews.Entinties.Article
import theo.tech.instanews.R

/**
 * created by theop
 * on 4/4/2019 at 8:50 AM
 */
class NewsDetails:AppCompatActivity() {
    private var webView: WebView? = null
    private var m_downX: Float = 0.toFloat()
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView= findViewById(R.id.wv_news_details)
        progressBar = findViewById(R.id.progressBar)
        val article = intent?.extras?.get("extra_article")as? Article
        initWebView()
        webView?.loadUrl(article?.url)
    }

    @SuppressLint("ClickableViewAccessibility", "SetJavaScriptEnabled")
    private fun initWebView()
    {
        webView?.webChromeClient = MyWebChromeClient(this)
        webView?.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar?.setVisibility(View.VISIBLE)
                invalidateOptionsMenu()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                webView!!.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                    progressBar?.setVisibility(View.GONE)
                invalidateOptionsMenu()
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
                    progressBar?.setVisibility(View.GONE)
                invalidateOptionsMenu()
            }
        }
        webView!!.clearCache(true)
        webView!!.clearHistory()
        webView!!.getSettings().javaScriptEnabled = true
        webView!!.getSettings().allowFileAccess = true
        webView!!.getSettings().allowFileAccessFromFileURLs = true
        webView!!.setHorizontalScrollBarEnabled(false)
        webView?.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.pointerCount > 1) {
                //Multi touch detected
                return@OnTouchListener true
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // save the x
                    m_downX = event.x
                }

                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    // set x so that it doesn't move
                    event.setLocation(m_downX, event.y)
                }
            }

            false
        })
    }

    private inner class MyWebChromeClient(internal var context: Context) : WebChromeClient()
    override fun onOptionsItemSelected(item: MenuItem?)=when(item?.itemId) {
        android.R.id.home->{
            onBackPressed()
            true
        }else->false
    }
}