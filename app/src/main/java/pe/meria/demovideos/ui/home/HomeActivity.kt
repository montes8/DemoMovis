package pe.meria.demovideos.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_logout.*
import kotlinx.android.synthetic.main.mold_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.databinding.ActivityHomeBinding
import pe.meria.demovideos.extensions.showDialogCustom
import pe.meria.demovideos.extensions.visible
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.detail.DetailActivity
import pe.meria.demovideos.ui.home.adapter.AdapterMovie
import pe.meria.demovideos.ui.signing.SigningActivity
import pe.meria.entity.Movie

class HomeActivity : BaseActivity() {

    private val homeViewModel                : HomeViewModel by viewModel(clazz = HomeViewModel::class)
    private lateinit var activityHomeBinding : ActivityHomeBinding
    private var adapterMovie                 : AdapterMovie?       = null


    companion object {
        fun newInstance(context: Context){
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return  context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.activity_home

    override fun setUpView() {
        bindLayout()
        homeViewModel.getListMovie()
        imgGeneric.setOnClickListener { dialogLogout() }
    }

    private fun bindLayout() {
        activityHomeBinding = DataBindingUtil.setContentView(this, getLayout())
        activityHomeBinding.let { it.lifecycleOwner = this }
        initList()
    }

    private fun initList(){
        adapterMovie = AdapterMovie()
        activityHomeBinding.rvMovie.adapter = adapterMovie
    }

    override fun getViewModel()  = homeViewModel

    override fun observeViewModel() {
        homeViewModel.movieListLiveData.observe(this, Observer {
            it.apply { if (this.isNotEmpty()){ updateListAdapter(this) } }
        })
    }

    private fun updateListAdapter(list : List<Movie>){
        adapterMovie?.list = list
        adapterMovie?.onClickItem={
            it.apply { DetailActivity.newInstance(this@HomeActivity,it) }
        }
    }

    private fun dialogLogout() {
        this@HomeActivity.showDialogCustom(R.layout.dialog_logout) {
            val backgroundColor = ColorDrawable(
                ContextCompat.getColor(this@HomeActivity, android.R.color.transparent))
            this.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            this.window?.setBackgroundDrawable(backgroundColor)
            this.window?.attributes?.windowAnimations = R.style.DialogTheme
            this.btnCancelDialog.visible()
            this.btnAcceptDialog.setOnClickListener {
                homeViewModel.logout()
                SigningActivity.newInstance(this@HomeActivity)
                this.dismiss()
            }
            this.contentLogout.setOnClickListener { this.dismiss() }
            this.imgCloseLogout.setOnClickListener { this.dismiss() }
            this.btnCancelDialog.setOnClickListener { this.dismiss() }
        }
    }
}