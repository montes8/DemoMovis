package pe.meria.demovideos.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.meria.demovideos.R
import pe.meria.demovideos.databinding.ActivityDetailBinding
import pe.meria.demovideos.extensions.loadImageUrlPicasso
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.utils.DATA_MOVIE
import pe.meria.demovideos.utils.EMPTY
import pe.meria.entity.Movie

class DetailActivity : BaseActivity() {

    private val          detailViewModel       : DetailViewModel by viewModel(clazz = DetailViewModel::class)
    private lateinit var activityDetailBinding : ActivityDetailBinding
    private var          dataMovie             : Movie? = null

    companion object {
        fun newInstance(context: Context,data : Movie):Intent{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DATA_MOVIE,data)
            return  intent
        }

        var VIEW_NAME_HEADER_IMAGE = "detail:header:image"
        var VIEW_NAME_HEADER_TITLE = "detail:header:title"
    }

    override fun getLayout() = R.layout.activity_detail

    override fun setUpView() {
        intent.let { dataMovie = it.getSerializableExtra(DATA_MOVIE) as Movie?}
        bindLayout()

        ViewCompat.setTransitionName(activityDetailBinding.imgBannerFood, VIEW_NAME_HEADER_IMAGE)
        ViewCompat.setTransitionName(activityDetailBinding.txtTitleDetail, VIEW_NAME_HEADER_TITLE)
    }

    private fun bindLayout() {
        activityDetailBinding = DataBindingUtil.setContentView(this, getLayout())
        activityDetailBinding.let {
            it.detailViewModel = detailViewModel
            it.movieDetail = dataMovie
            it.lifecycleOwner = this
        }
        activityDetailBinding.imgBannerFood.loadImageUrlPicasso(dataMovie?.backdropPath?: EMPTY,activityDetailBinding.progressBarImg,
        activityDetailBinding.txtErrorConnexionDetail)
    }

    override fun getViewModel() = detailViewModel

    override fun observeViewModel() {
         detailViewModel.successClickBack.observe(this, Observer { onBackPressed() })
    }

}