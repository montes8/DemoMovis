package pe.meria.demovideos.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movie.view.*
import pe.meria.demovideos.R
import pe.meria.demovideos.extensions.delayClickState
import pe.meria.entity.Movie

class AdapterMovie(var onClickItem: ((Movie) -> Unit)? = null) : RecyclerView.Adapter<AdapterMovie.EstablishmentViewHolder>() {

    var list: List<Movie> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstablishmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.row_movie, parent, false)
        return EstablishmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EstablishmentViewHolder, position: Int) {
        holder.bind(list[position])
        Log.d("urlImage",list[position].posterPath)
        holder.itemView.setOnClickListener{
            holder.itemView.delayClickState()
            onClickItem?.invoke(list[position])
        }
    }

    class EstablishmentViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(data: Movie) {
            dataBinding.setVariable(BR.movie, data)
            dataBinding.executePendingBindings()
        }
    }
}