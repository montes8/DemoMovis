package pe.meria.demovideos.ui.signing

import android.content.Context
import android.content.Intent
import pe.meria.demovideos.R
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.BaseViewModel

class SigningActivity : BaseActivity() {


    companion object {
        fun newInstance(context: Context){
            val intent = Intent(context, SigningActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return  context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.activity_signing

    override fun setUpView() {
    }

    override fun getViewModel(): BaseViewModel? {
       return null
    }

    override fun observeViewModel() {
    }

}