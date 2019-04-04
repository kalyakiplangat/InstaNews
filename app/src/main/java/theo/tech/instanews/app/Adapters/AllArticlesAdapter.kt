package theo.tech.instanews.app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.technews_row.view.*
import theo.tech.instanews.Entinties.Article
import theo.tech.instanews.R

/**
 * created by theop
 * on 4/3/2019 at 11:37 PM
 */
class AllArticlesAdapter(var context: Context,private val articleList: ArrayList<Article>,private val listener:Listener):RecyclerView.Adapter<AllArticlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllArticlesAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.technews_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =articleList.count()

    override fun onBindViewHolder(holder: AllArticlesAdapter.ViewHolder, position: Int) {
        holder.bind(context,articleList[position],listener,position)
    }
    interface Listener{
        fun onArticleClick(article: Article)
        fun onCommentClick(article: Article)
        fun onFavoriteClick(article: Article)
        fun onLikeClick(article: Article)
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bind(context: Context,article: Article,listener: Listener,position: Int){
           itemView.tv_article_title.text=article.title
            itemView.tv_category.text=article.author
            itemView.tv_categ.text=article.content
            itemView.tv_source.text= "${article.source.name} By:-"

            Glide.with(context)
                .load(article.urlToImage)
                .into(itemView.img_thumbnail)

            itemView.setOnClickListener { listener.onArticleClick(article) }
            itemView.commentsWrapper.setOnClickListener { listener.onCommentClick(article) }
            itemView.shareWrapper.setOnClickListener { listener.onFavoriteClick(article) }
            itemView.likesWrapper.setOnClickListener { listener.onLikeClick(article) }
        }
    }
}