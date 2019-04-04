package theo.tech.instanews.app.Views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import theo.tech.instanews.Entinties.Article
import theo.tech.instanews.Entinties.Category
import theo.tech.instanews.Network.ApiClient
import theo.tech.instanews.R
import theo.tech.instanews.app.Adapters.AllArticlesAdapter
import theo.tech.instanews.app.AppControllers
import java.io.Serializable

/**
 * created by theop
 * on 4/4/2019 at 12:08 PM
 */
class ArticlesInCategory:AppCompatActivity(),AllArticlesAdapter.Listener,SwipeRefreshLayout.OnRefreshListener  {


    val client by lazy {
        ApiClient.create()
    }
    private lateinit var rv_content_list: RecyclerView
    lateinit var compositeDisposable: CompositeDisposable
    private lateinit var articleList:ArrayList<Article>
    private var articleAdapter:AllArticlesAdapter?=null
    private lateinit var category: Category
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        category = (intent?.extras?.get("extra_source")as? Category)!!
        compositeDisposable= CompositeDisposable()
        rv_content_list=findViewById(R.id.rv_content_list)
        progressBar = findViewById(R.id.progressBar)
        initViews()
        swipeToRefresh()
        fetchArticles()
    }

    override fun onRefresh() {
       fetchArticles()
    }

    private fun initViews() {
        rv_content_list.setHasFixedSize(true)
        val layoutManager:RecyclerView.LayoutManager= LinearLayoutManager(this)
        rv_content_list.layoutManager=layoutManager
    }

    private fun swipeToRefresh() {
        swipe_refresh_layout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    private fun fetchArticles() {
        progressBar?.setVisibility(View.VISIBLE)
        compositeDisposable.add(client.getArticlesInCategory(
            category.url.replace("http://","").replace("https://","").replace("https:// www.","").replace("http:// www.","").replace("www.",""),
            "technology"
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({articles->handleResponse(articles.articles)},
                this::handleError))
    }

    private fun handleResponse(articles: List<Article>) {
        swipe_refresh_layout.isRefreshing=false
        progressBar?.setVisibility(View.GONE)
        articleList= ArrayList(articles)
        articleAdapter=AllArticlesAdapter(this,articleList,this)
        rv_content_list.adapter=articleAdapter
    }

    private fun handleError(e:Throwable){
        swipe_refresh_layout.isRefreshing=false
        progressBar?.setVisibility(View.GONE)
        e.printStackTrace()
    }

    override fun onArticleClick(article: Article) {
        val intent= Intent(AppControllers.instance, NewsDetails::class.java)
        intent.putExtra("extra_article",article as Serializable)
        startActivity(intent)
    }

    override fun onCommentClick(article: Article) {
        Toast.makeText(this, "Not Implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onFavoriteClick(article: Article) {
        Toast.makeText(this, "Not Implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onLikeClick(article: Article) {
        Toast.makeText(this, "Not Implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?)=when(item?.itemId) {
        android.R.id.home->{
            onBackPressed()
            true
        }else->false
    }
}
