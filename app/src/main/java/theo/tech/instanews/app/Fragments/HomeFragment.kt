package theo.tech.instanews.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import theo.tech.instanews.R

/**
 * created by theop
 * on 4/3/2019 at 10:22 PM
 */
class HomeFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView:View=inflater.inflate(R.layout.content_main,container,false)
        return rootView
    }
}