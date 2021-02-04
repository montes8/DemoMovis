package pe.meria.demovideos.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.databinding.ActivitySplashBinding
import pe.meria.demovideos.ui.AppViewModel
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.demovideos.ui.home.HomeActivity
import pe.meria.demovideos.ui.signing.SigningActivity

class SplashActivity : BaseActivity() {

    private val appViewModel: AppViewModel by viewModel(clazz = AppViewModel::class)
    private lateinit var activitySplashBinding: ActivitySplashBinding

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.activity_splash

    override fun setUpView() {
        bindLayout()
        appViewModel.getLogin()
    }

    private fun bindLayout() {
        activitySplashBinding = DataBindingUtil.setContentView(this, getLayout())
        activitySplashBinding.let {
            it.lifecycleOwner = this
        }

    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    override fun observeViewModel() {
       appViewModel.successLoginLiveData.observe(this, Observer {
            it.apply {
                initSplash(this)
            }
        })
    }

    private fun initSplash(value: Boolean) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (value) {
                HomeActivity.newInstance(this)
            } else {
                HomeActivity.newInstance(this)

            }
            finish()
        }, 3000)
    }


}