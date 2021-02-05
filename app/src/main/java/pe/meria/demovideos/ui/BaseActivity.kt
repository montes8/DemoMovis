package pe.meria.demovideos.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.Observer
import pe.meria.demovideos.R
import pe.meria.demovideos.component.DemoMovieProgressBar
import pe.meria.demovideos.ui.splash.SplashActivity
import pe.meria.demovideos.utils.AppUtils.dialogGeneric
import pe.meria.repository.repository.exception.GenericException
import pe.meria.repository.repository.exception.NetworkException
import pe.meria.repository.repository.exception.UnAuthorizeException
import java.net.ConnectException
import java.net.SocketTimeoutException


abstract class BaseActivity : AppCompatActivity(){
    private var instanceState: Bundle? = null
    private var mProgressBar: DemoMovieProgressBar? = null

    abstract fun getLayout(): Int
    abstract fun setUpView()
    abstract fun getViewModel(): BaseViewModel?
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.instanceState = savedInstanceState
        setContentView(getLayout())
        mProgressBar = DemoMovieProgressBar(this, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen)
        this.setUpView()
        this.observeMainViewModel()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigth_in, R.anim.right_out)
    }

    private fun observeMainViewModel() {
        this.observeViewModel()
        getViewModel()?.let { viewModel ->
            viewModel.errorLiveData.observe(this, Observer { error -> this.showMessageException(error) })
            viewModel.loadingLiveData.observe(this, Observer { isLoading -> showLoading(isLoading) })
        }
    }

      private fun showMessageException(ex: Exception) {
         when(ex) {
             is UnAuthorizeException -> errorExpireSession(ex.message)
             is GenericException -> errorGeneric(ex.message)
             is NetworkException ->{errorMessage() }
             is SocketTimeoutException ->{ errorMessage() }
             is ConnectException->{errorMessage()}
             else -> this.errorGeneric(ex.message)
         }
    }

      private fun showLoading(isLoading: Boolean) {
        mProgressBar.apply {
            if (isLoading) this?.show() else this?.dismiss()
        }
    }


     private fun errorGeneric(message: String?) {
         dialogGeneric(message,this)
    }

     private fun errorMessage() {
            AlertDialog.Builder(ContextThemeWrapper(this,R.style.AppThemeBlue))
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setMessage(R.string.exception_no_internet_error_disponible)
                .setPositiveButton(R.string.dialog_accept) { dialogInterface, _ -> dialogInterface.dismiss() }
                .show()
    }

    private fun errorExpireSession(string: String?) {
        val message = string ?: resources.getString(R.string.error_go)
        AlertDialog.Builder(ContextThemeWrapper(this,R.style.AppThemeBlue))
            .setTitle(R.string.app_name)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.dialog_accept) { dialogInterface, _ ->
                dialogInterface.dismiss()
                finish()
                SplashActivity.newInstance(this)
            }
            .show()
    }

     fun toastGeneric(string: String?) {
        string?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }?:Toast.makeText(this, this.getString(R.string.error_generic), Toast.LENGTH_SHORT).show()
    }
}