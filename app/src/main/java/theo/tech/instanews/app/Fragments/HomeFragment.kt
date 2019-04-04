package theo.tech.instanews.app.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import theo.tech.instanews.Entinties.Article
import theo.tech.instanews.Network.ApiClient
import theo.tech.instanews.R
import theo.tech.instanews.app.Adapters.AllArticlesAdapter
import theo.tech.instanews.app.AppControllers
import theo.tech.instanews.app.MainActivity
import theo.tech.instanews.app.Views.NewsDetails
import java.io.Serializable

/**
 * created by theop
 * on 4/3/2019 at 10:22 PM
 */
class HomeFragment:Fragment(),AllArticlesAdapter.Listener {

    val client by lazy {
        ApiClient.create()
    }
    private var progressBar: ProgressBar? = null
    private lateinit var rv_content_list: RecyclerView
    lateinit var compositeDisposable: CompositeDisposable
    private lateinit var articleList:ArrayList<Article>
    private var articleAdapter:AllArticlesAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView:View=inflater.inflate(R.layout.content_main,container,false)
        compositeDisposable= CompositeDisposable()
        rv_content_list=rootView.findViewById(R.id.rv_content_list)
        progressBar = rootView.findViewById(R.id.progressBar)
        initViews()
        setHasOptionsMenu(true)
        fetchArticles()
        return rootView
    }

    private fun fetchArticles() {
        progressBar?.setVisibility(View.VISIBLE)
        compositeDisposable.add(client.getAllArticles(
            "technology"
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({articles->handleResponse(articles.articles)},
                this::handleError))
    }

    private fun handleResponse(articles: List<Article>) {
        progressBar?.setVisibility(View.GONE)
        articleList= ArrayList(articles)
        articleAdapter=AllArticlesAdapter(this.context!!,articleList,this)
        rv_content_list.adapter=articleAdapter
    }

    private fun handleError(e:Throwable){
        progressBar?.setVisibility(View.GONE)
        e.printStackTrace()
    }

    private fun initViews() {
        rv_content_list.setHasFixedSize(true)
        val layoutManager:RecyclerView.LayoutManager= LinearLayoutManager(context)
        rv_content_list.layoutManager=layoutManager
    }

    override fun onArticleClick(article: Article) {
 val intent=Intent(AppControllers.instance,NewsDetails::class.java)
        intent.putExtra("extra_article",article as Serializable)
        startActivity(intent)
    }

    override fun onCommentClick(article: Article) {
        Toast.makeText(context, "not Implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onFavoriteClick(article: Article) {
        Toast.makeText(context, "not Implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onLikeClick(article: Article) {
        Toast.makeText(context, "not Implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.main,menu)
        val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //TODO: not implemented
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
              //TODO: not implemented
                return false
            }
        })
    }
}