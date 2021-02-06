package pe.meria.demovideos.ui.signing.adapter

import android.os.Bundle
import kotlinx.android.synthetic.main.row_fragment.*
import pe.meria.demovideos.R
import pe.meria.demovideos.extensions.setDrawableStartPosition
import pe.meria.demovideos.model.ImageLogoModel
import pe.meria.demovideos.ui.BaseFragment
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.demovideos.utils.MODEL

class LogoFragment : BaseFragment() {

    private lateinit var img : ImageLogoModel

    override fun getLayout() = R.layout.row_fragment

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    override fun setBundle() {
        arguments.apply {
            img = this?.getParcelable(MODEL)?: ImageLogoModel()
        }

    }

    override fun setUpView() {
        profileImage.setDrawableStartPosition(img.nameIcon)
    }

    override fun observeViewModel() {
       //not code
    }

    companion object {
        fun newInstance(model: ImageLogoModel): LogoFragment {
            val args = Bundle()
            args.putParcelable(MODEL, model)
            val fragment = LogoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}