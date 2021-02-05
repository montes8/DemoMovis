package pe.meria.demovideos.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_movie.view.*
import pe.meria.demovideos.R
import pe.meria.demovideos.extensions.delayClickState
import pe.meria.demovideos.extensions.loadImageUrlPicasso
import pe.meria.entity.LoadingStatus
import pe.meria.entity.Movie

class AdapterMovie(var onClickItem: ((Movie) -> Unit)? = null) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoading = false
    private var flag = true
    private var list: ArrayList<Movie> = ArrayList()

    fun addList(list: ArrayList<Movie>) {
        if (list.isEmpty()) return
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun showLoading(isShow: Boolean) {
        this.isLoading = isShow
        notifyDataSetChanged()
    }

    fun clearListMovie(){
        list.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            return LoadingStatus.ACTIVE.value
        }
        return LoadingStatus.INACTIVE.value
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val view: ViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.row_progress,
                    parent,
                    false
                )
                TypeTwoHolder(view)
            }
            else -> {
                val binding: ViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.row_movie,
                    parent,
                    false
                )
                MovieViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (flag) {
            list.size + 1
        } else {
            list.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 2) {
            if (list.isEmpty())
                return
            (holder as MovieViewHolder).bind(list[position])
            holder.itemView.roundImageView.loadImageUrlPicasso(list[position].posterPath,holder.itemView.progressBarItem)
            holder.itemView.setOnClickListener{
                holder.itemView.delayClickState()
                onClickItem?.invoke(list[position])
            }
        } else {
            (holder as TypeTwoHolder).showLoading(isLoading)
        }
    }

    class MovieViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(data: Movie) {
            dataBinding.setVariable(BR.movie, data)
            dataBinding.executePendingBindings()
        }
    }

    inner class TypeTwoHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun showLoading(isShow: Boolean) {
            dataBinding.setVariable(BR.visible, isShow)
            dataBinding.executePendingBindings()
        }
    }
}