package theo.tech.instanews.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

/**
 * created by theop
 * on 4/3/2019 at 10:22 PM
 */
class HomeFragment:Fragment(),AllArticlesAdapter.Listener {

    val client by lazy {
        ApiClient.create()
    }
    private lateinit var rv_content_list: RecyclerView
    lateinit var compositeDisposable: CompositeDisposable
    private lateinit var articleList:ArrayList<Article>
    private var articleAdapter:AllArticlesAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView:View=inflater.inflate(R.layout.content_main,container,false)
        compositeDisposable= CompositeDisposable()
        rv_content_list=rootView.findViewById(R.id.rv_content_list)
        initViews()
        fetchArticles()
        return rootView
    }

    private fun fetchArticles() {
        compositeDisposable.add(client.getAllArticles(
            "technology"
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({articles->handleResponse(articles.articles)},
                this::handleError))
    }

    private fun handleResponse(articles: List<Article>) {
        articleList= ArrayList(articles)
        articleAdapter=AllArticlesAdapter(this.context!!,articleList,this)
        rv_content_list.adapter=articleAdapter
    }

    private fun handleError(e:Throwable){
        e.printStackTrace()
    }

    private fun initViews() {
        rv_content_list.setHasFixedSize(true)
        val layoutManager:RecyclerView.LayoutManager= LinearLayoutManager(context)
        rv_content_list.layoutManager=layoutManager
    }

    override fun onArticleClick(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCommentClick(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFavoriteClick(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLikeClick(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}