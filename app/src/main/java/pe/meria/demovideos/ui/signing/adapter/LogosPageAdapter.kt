package pe.meria.demovideos.ui.signing.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pe.meria.demovideos.model.ImageLogoModel

class LogosPageAdapter(fm: FragmentManager,var list :ArrayList<ImageLogoModel>) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return LogoFragment.newInstance(list[position])
    }

    override fun getCount(): Int {
        return MAX_PAGE
    }

    companion object {
        const val MAX_PAGE = 2
    }
}