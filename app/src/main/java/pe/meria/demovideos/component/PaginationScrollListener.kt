package pe.meria.demovideos.component

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(var layoutManager: LinearLayoutManager) :

    RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (!isLoading && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition < totalItemCount || firstVisibleItemPosition < 0 || totalItemCount < PAGE_SIZE
                ) {
                    return
                }
                loadMoreItems()
            }
        }

        protected abstract fun loadMoreItems()
        abstract val isLastPage: Boolean
        abstract val isLoading: Boolean
        companion object {
            private const val PAGE_SIZE = 20
        }
}