package pe.meria.demovideos.ui.home

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.databinding.ActivityHomeBinding
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.home.adapter.AdapterMovie

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
            it.apply { if (this.isNotEmpty()){ adapterMovie?.list = this} }
        })
    }
}