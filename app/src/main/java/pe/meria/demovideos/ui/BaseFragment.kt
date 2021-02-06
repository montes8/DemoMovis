package pe.meria.demovideos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment() {

    abstract fun getViewModel(): BaseViewModel?
    abstract fun getLayout(): Int
    abstract fun setUpView()
    abstract fun setBundle()
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.observeViewModel()
        this.observeMainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutID = getLayout()
        this.setBundle()
        return inflater.inflate(layoutID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setUpView()
    }

    private fun observeMainViewModel() {
        this.getViewModel()?.let { viewModel ->
            viewModel.errorLiveData.observe(this, Observer { error ->
                this.activity?.let {
                    if (it is BaseActivity) {
                        it.showMessageException(error)
                    }
                }
            })

            viewModel.loadingLiveData.observe(this, Observer { error ->
                this.activity?.let {
                    if (it is BaseActivity) {
                        it.showLoading(error)
                    }
                }
            })
        }
    }

}