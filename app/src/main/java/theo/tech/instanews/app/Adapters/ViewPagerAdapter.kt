package theo.tech.instanews.app.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * created by theop
 * on 4/3/2019 at 10:34 PM
 */
class ViewPagerAdapter(manager:FragmentManager):FragmentPagerAdapter(manager) {
   private val mFragmentList=ArrayList<Fragment>()
    private val mFragmentTitleList=ArrayList<String>()

    override fun getItem(position: Int): Fragment {
       return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment,Title:String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(Title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

}