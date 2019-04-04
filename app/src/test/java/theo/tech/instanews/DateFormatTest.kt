package theo.tech.instanews

import android.util.Log
import org.junit.Test
import theo.tech.instanews.app.AppControllers
import java.time.format.DateTimeFormatter

/**
 * created by theop
 * on 4/4/2019 at 1:48 PM
 */
class DateFormatTest {
    @Test
    fun date(args:Array<String>){
        val dateFormatTest="2019-04-03T15:35:48Z"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = dateFormatTest.format(formatter)
        println("formatted date/time is: $formatted")
        Log.d(AppControllers.TAG,formatted+"originalDate"+dateFormatTest)
    }
}