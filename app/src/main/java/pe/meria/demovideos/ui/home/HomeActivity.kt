package pe.meria.demovideos.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_logout.*
import kotlinx.android.synthetic.main.mold_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.component.PaginationScrollListener
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

    private var isLoadingOn = false
    private var isPage      = false
    private var  page       = 1
    private var pageNext    = true

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
        getList()
        imgGeneric.setOnClickListener { dialogLogout() }
    }

    private fun bindLayout() {
        activityHomeBinding = DataBindingUtil.setContentView(this, getLayout())
        activityHomeBinding.let { it.lifecycleOwner = this }
        initList()
    }

    private fun initList(){
        adapterMovie = AdapterMovie()
        val linearLayoutManager = LinearLayoutManager(this)
        activityHomeBinding.rvMovie.layoutManager = linearLayoutManager
        activityHomeBinding.rvMovie.adapter = adapterMovie
        activityHomeBinding.rvMovie.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                if (pageNext){
                    isLoadingOn = true
                    getList()
                }
            }
            override val isLastPage: Boolean
                get() = isPage
            override val isLoading: Boolean
                get() =isLoadingOn


        })
    }

    private fun getList(){
        showSwipe()
        homeViewModel.getListMovie(false,page)
    }

    override fun getViewModel()  = homeViewModel

    override fun observeViewModel() {
        homeViewModel.movieListLiveData.observe(this, Observer {
            it.apply {updateListAdapter(this)}
        })

        homeViewModel.errorLiveData.observe(this, Observer {
            adapterMovie?.showLoading(false)

        })
    }

    private fun updateListAdapter(list : List<Movie>){
        hideSwipe()
        if (list.isNotEmpty()){
            page++
            adapterMovie?.addList(list as ArrayList<Movie>)
            adapterMovie?.onClickItem={
                it.apply {
                    overridePendingTransition(R.anim.left_in, R.anim.left_out)
                    DetailActivity.newInstance(this@HomeActivity,it)
                }
            }
        }else{
            pageNext = false

            Log.d("pageNext","$pageNext")
        }
    }

    private fun showSwipe() {
        adapterMovie?.showLoading(true)
        isLoadingOn = true
    }

    private fun hideSwipe() {
        adapterMovie?.showLoading(false)
        isLoadingOn = false
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