package pe.meria.demovideos.ui.signing

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.databinding.ActivitySigningBinding
import pe.meria.demovideos.extensions.animForm
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.home.HomeActivity

class SigningActivity : BaseActivity() {

    private val signingViewModel                : SigningViewModel by viewModel(clazz = SigningViewModel::class)
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
        firstAnimations()
    }

    private fun firstAnimations() {
        this.activitySigningBinding.profileImage.startAnimation(animForm)
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
    }

    override fun getViewModel() = signingViewModel

    override fun observeViewModel() {
        signingViewModel.successLoginLiveData.observe(this, Observer {
            it.apply {
                if (this){
                    overridePendingTransition(R.anim.left_in, R.anim.left_out)
                    HomeActivity.newInstance(this@SigningActivity)
                }else{ toastGeneric(getString(R.string.error_login)) }
            }
        })
    }
}