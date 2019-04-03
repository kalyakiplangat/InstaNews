package theo.tech.instanews.app

import android.app.Application

/**
 * created by theop
 * on 4/3/2019 at 5:46 PM
 */
class AppControllers:Application() {
    companion object {
        lateinit var instance:AppControllers private set
        const val TAG="InstaNews"
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}