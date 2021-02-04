package pe.meria.demovideos.ui.signing

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.databinding.ActivityHomeBinding
import pe.meria.demovideos.databinding.ActivitySigningBinding
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.demovideos.ui.home.HomeActivity
import pe.meria.demovideos.ui.home.HomeViewModel

class SigningActivity : BaseActivity() {

    private val signingViewModel             : SigningViewModel by viewModel(clazz = SigningViewModel::class)
    private lateinit var activitySigningBinding : ActivitySigningBinding


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
    }

    private fun bindLayout() {
        activitySigningBinding = DataBindingUtil.setContentView(this, getLayout())
        activitySigningBinding.let {
            it.signingViewModel = signingViewModel
            it.lifecycleOwner = this }
    }

    override fun getViewModel() = signingViewModel

    override fun observeViewModel() {
        signingViewModel.successLoginLiveData.observe(this, Observer {
            it.apply {
                if (this){
                    HomeActivity.newInstance(this@SigningActivity)
                }else{
                    toastGeneric(getString(R.string.error_login))
                }
            }
        })
    }

}