package pe.meria.demovideos.ui.signing

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.component.editNormal.CustomEditText
import pe.meria.demovideos.databinding.ActivitySigningBinding
import pe.meria.demovideos.extensions.animForm
import pe.meria.demovideos.extensions.imageAnimation
import pe.meria.demovideos.model.ImageLogoModel
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.home.HomeActivity
import pe.meria.demovideos.ui.signing.adapter.LogosPageAdapter
import pe.meria.demovideos.ui.signing.adapter.ViewPagerTransformer
import java.util.*
import kotlin.collections.ArrayList

class SigningActivity : BaseActivity(), CustomEditText.ITCSimpleEditText {

    private val signingViewModel                : SigningViewModel by viewModel(clazz = SigningViewModel::class)
    private lateinit var activitySigningBinding : ActivitySigningBinding

    var mAdapter: LogosPageAdapter? = null
    var swipeTimer: Timer? = null
    private var currentPage = 0

    companion object {
        fun newInstance(context: Context){
            val intent = Intent(context, SigningActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return  context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.activity_signing

    override fun setUpView() {
        bindLayout()
        initViewPager()
        firstAnimations()

    }

    private fun initViewPager(){
        mAdapter = LogosPageAdapter(supportFragmentManager,testList())
        activitySigningBinding.viewPager.adapter = mAdapter
        activitySigningBinding.viewPager.setPageTransformer(true,
            ViewPagerTransformer(ViewPagerTransformer.TransformType.ZOOM)
        )

        swipe()
    }

    private fun swipe() {
        val handler = Handler(Looper.getMainLooper())
        val Update = Runnable {
            if (currentPage == LogosPageAdapter.MAX_PAGE) {
                currentPage = 0
            }
            activitySigningBinding.viewPager.setCurrentItem(currentPage++, true)
        }
        swipeTimer = Timer()
        swipeTimer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 500, 3000)
    }

    private fun testList():ArrayList<ImageLogoModel>{
        val list : ArrayList<ImageLogoModel> = ArrayList()
        list.add(ImageLogoModel(1,"logo_movie"))
        list.add(ImageLogoModel(2,"ic_tv_movi"))
        return list
    }

    private fun firstAnimations() {
        this.activitySigningBinding.viewPager.imageAnimation
        this.activitySigningBinding.linearLayout3.startAnimation(animForm)
        this.activitySigningBinding.edNameUser.startAnimation(animForm)
        this.activitySigningBinding.edPassUser.startAnimation(animForm)
        this.activitySigningBinding.btnEnter.startAnimation(animForm)

    }

    private fun bindLayout() {
        activitySigningBinding = DataBindingUtil.setContentView(this, getLayout())
        activitySigningBinding.let {
            it.signingViewModel = signingViewModel
            it.lifecycleOwner = this }

        this.activitySigningBinding.edNameUser.etListener = this
        this.activitySigningBinding.edPassUser.etListener = this
        signingViewModel.setEditUse(this.activitySigningBinding.edNameUser, this.activitySigningBinding.edPassUser)
    }

    override fun onRestart() {
        super.onRestart()
        this.activitySigningBinding.linearLayout3.startAnimation(animForm)
    }

    override fun getViewModel() = signingViewModel

    override fun observeViewModel() {
        signingViewModel.successLoginLiveData.observe(this, Observer {
            it.apply {
                if (this){
                    overridePendingTransition(R.anim.left_in, R.anim.left_out)
                    HomeActivity.newInstance(this@SigningActivity)
                    finish()
                }else{ toastGeneric(getString(R.string.error_login)) }
            }
        })
    }

    override fun onchangeListener(editText: CustomEditText) {
        editText.hideError
    }


    override fun doWhenFocusGone(editText: CustomEditText) {

    }

    override fun doOnFocus(editText: CustomEditText) {

    }
}