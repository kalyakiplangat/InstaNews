package theo.tech.instanews.app.Fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import theo.tech.instanews.Entinties.Category
import theo.tech.instanews.Network.ApiClient
import theo.tech.instanews.R
import theo.tech.instanews.app.Adapters.SourcesAdapter
import theo.tech.instanews.app.AppControllers
import theo.tech.instanews.app.Views.ArticlesInCategory
import java.io.Serializable

/**
 * created by theop
 * on 4/3/2019 at 10:22 PM
 */
class TechFragment:Fragment(),SourcesAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    val client by lazy {
        ApiClient.create()
    }
    private var progressBar: ProgressBar? = null
    private lateinit var rv_content_list: RecyclerView
    lateinit var compositeDisposable: CompositeDisposable
    private lateinit var sourcesLists:ArrayList<Category>
    private var sourcesAdapter: SourcesAdapter?=null
    private lateinit var swipe_refresh_layout:SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val rootView:View=inflater.inflate(R.layout.tech_main_layout,container,false)
       compositeDisposable= CompositeDisposable()
        rv_content_list=rootView.findViewById(R.id.rv_content_list)
        progressBar = rootView.findViewById(R.id.progressBar)
        swipe_refresh_layout=rootView.findViewById(R.id.swipe_refresh_layout)
        swipeToRefresh()
        initViews()
        fetchSources()

        return rootView
    }

    override fun onRefresh() {
      fetchSources()
    }
    private fun swipeToRefresh() {
        swipe_refresh_layout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    private fun initViews() {
        rv_content_list.setHasFixedSize(true)
        val layoutManager:RecyclerView.LayoutManager= LinearLayoutManager(context)
        rv_content_list.layoutManager=layoutManager

    }
/*fetch all sources for category tech related*/
    private fun fetchSources() {
    progressBar?.setVisibility(View.VISIBLE)
    compositeDisposable.add(client.getSourceTechCategory(
        "technology"
    )
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({category->handleResponse(category.sources)},
            this::handleError))
    }

    private fun handleResponse(sources: List<Category>) {
        progressBar?.setVisibility(View.GONE)
        swipe_refresh_layout.isRefreshing=false
        sourcesLists= ArrayList(sources)
        sourcesAdapter= SourcesAdapter(this.context!!,sourcesLists,this)
        rv_content_list.adapter=sourcesAdapter
    }

    private fun handleError(e:Throwable){
        swipe_refresh_layout.isRefreshing=false
        progressBar?.setVisibility(View.GONE)
        e.printStackTrace()
    }

    override fun onSourceClick(category: Category) {
       val intent=Intent(AppControllers.instance, ArticlesInCategory::class.java)
        intent.putExtra("extra_source",category as Serializable)
        startActivity(intent)
    }

}