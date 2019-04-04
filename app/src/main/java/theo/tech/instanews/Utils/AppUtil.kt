package theo.tech.instanews.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import theo.tech.instanews.R
import theo.tech.instanews.app.Fragments.HomeFragment
import java.lang.reflect.Array
import java.util.*
import java.util.regex.Pattern

/**
 * created by theop
 * on 4/3/2019 at 5:46 PM
 */
class AppUtil {
    companion object {

        val HOME_FRAGMENT = "HomeFragment"


        private lateinit var encodeString: String

        private var CURRENT_TAG: String? = null
        fun switchFragmentWithAnimation(id: Int, fragment: Fragment,
                                        activity: FragmentActivity, TAG: String, transitionStyle: AnimationType?) {

            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager
                .beginTransaction()

            if (transitionStyle != null) {
                when (transitionStyle) {
                    AppUtil.AnimationType.SLIDE_DOWN ->

                        // Exit from down
                        fragmentTransaction.setCustomAnimations(R.anim.slide_up,
                            R.anim.slide_down)

                    AppUtil.AnimationType.SLIDE_UP ->

                        // Enter from Up
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
                            R.anim.slide_out_up)

                    AppUtil.AnimationType.SLIDE_LEFT ->

                        // Enter from left
                        fragmentTransaction.setCustomAnimations(R.anim.slide_left,
                            R.anim.slide_out_left)

                    // Enter from right
                    AppUtil.AnimationType.SLIDE_RIGHT -> fragmentTransaction.setCustomAnimations(R.anim.slide_right,
                        R.anim.slide_out_right)

                    AppUtil.AnimationType.FADE_IN -> {
                        fragmentTransaction.setCustomAnimations(R.anim.fade_in,
                            R.anim.fade_out)
                        fragmentTransaction.setCustomAnimations(R.anim.fade_in,
                            R.anim.donot_move)
                    }

                    AppUtil.AnimationType.FADE_OUT -> fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.donot_move)

                    AppUtil.AnimationType.SLIDE_IN_SLIDE_OUT ->

                        fragmentTransaction.setCustomAnimations(R.anim.slide_left,
                            R.anim.slide_out_left)

                    else -> {
                    }
                }
            }

            CURRENT_TAG = TAG

            fragmentTransaction.replace(id, fragment)
            fragmentTransaction.addToBackStack(TAG)
            fragmentTransaction.commit()
        }

        fun switchContent(id: Int, TAG: String,
                          baseActivity: FragmentActivity, transitionStyle: AnimationType?) {

            var fragmentToReplace: Fragment? = null

            val fragmentManager = baseActivity
                .supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            // If our current fragment is null, or the new fragment is different, we
            // need to change our current fragment
            if (CURRENT_TAG == null || TAG != CURRENT_TAG) {

                if (transitionStyle != null) {
                    when (transitionStyle) {
                        AppUtil.AnimationType.SLIDE_DOWN ->
                            // Exit from down
                            transaction.setCustomAnimations(R.anim.slide_up,
                                R.anim.slide_down)
                        AppUtil.AnimationType.SLIDE_UP ->
                            // Enter from Up
                            transaction.setCustomAnimations(R.anim.slide_in_up,
                                R.anim.slide_out_up)
                        AppUtil.AnimationType.SLIDE_LEFT ->
                            // Enter from left
                            transaction.setCustomAnimations(R.anim.slide_left,
                                R.anim.slide_out_left)
                        // Enter from right
                        AppUtil.AnimationType.SLIDE_RIGHT -> transaction.setCustomAnimations(R.anim.slide_right,
                            R.anim.slide_out_right)
                        AppUtil.AnimationType.FADE_IN -> {
                            transaction.setCustomAnimations(R.anim.fade_in,
                                R.anim.fade_out)
                            transaction.setCustomAnimations(R.anim.fade_in,
                                R.anim.donot_move)
                        }
                        AppUtil.AnimationType.FADE_OUT -> transaction.setCustomAnimations(R.anim.fade_in, R.anim.donot_move)
                        AppUtil.AnimationType.SLIDE_IN_SLIDE_OUT -> transaction.setCustomAnimations(R.anim.slide_left,
                            R.anim.slide_out_left)
                        else -> {
                        }
                    }
                }

                // Try to find the fragment we are switching to
                val fragment = fragmentManager.findFragmentByTag(TAG)

                // If the new fragment can't be found in the manager, create a new
                // one
                if (fragment == null) {

                    if (TAG == HOME_FRAGMENT) {
                        fragmentToReplace = HomeFragment()
                    }

                } else {
                    if (TAG == HOME_FRAGMENT) {
                        fragmentToReplace = fragment
                    }
                }

                CURRENT_TAG = TAG

                // Replace our current fragment with the one we are changing to
                transaction.replace(id, fragmentToReplace!!, TAG)
                transaction.commit()

            }

            // Do nothing since am are already on the fragment being changed to

        }

        fun extractYTId(ytUrl: String?): String? {
            var vId: String? = null
            val pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(ytUrl)
            if (matcher.matches()) {
                vId = matcher.group(1)
            }
            return vId
        }



        // put the image file path into this method
    }

    enum class AnimationType {
        SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN, FADE_IN, SLIDE_IN_SLIDE_OUT, FADE_OUT
    }
}