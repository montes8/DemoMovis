package pe.meria.demovideos.ui.detail

import android.content.Context
import android.content.Intent
import pe.meria.demovideos.R
import pe.meria.demovideos.ui.BaseActivity
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.demovideos.utils.DATA_MOVIE
import pe.meria.entity.Movie

class DetailActivity : BaseActivity() {

    private var dataMovie : Movie? = null

    companion object {
        fun newInstance(context: Context,data : Movie){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DATA_MOVIE,data)
            return  context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.activity_detail

    override fun setUpView() {
        intent.let {
            dataMovie = it.getSerializableExtra(DATA_MOVIE) as Movie?
        }

    }

    override fun getViewModel(): BaseViewModel? {
       return null
    }

    override fun observeViewModel() {

    }

}