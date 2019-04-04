package theo.tech.instanews.app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sources_row.view.*
import theo.tech.instanews.Entinties.Category
import theo.tech.instanews.Entinties.Source
import theo.tech.instanews.R

/**
 * created by theop
 * on 4/4/2019 at 10:56 AM
 */
class SourcesAdapter(var context: Context,private val sourceList: ArrayList<Category>,private val listener:Listener):RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sources_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourcesAdapter.ViewHolder, position: Int) {
        holder.bind(context,sourceList[position],listener,position)
    }


    override fun getItemCount(): Int =sourceList.count()

    interface Listener{
        fun onSourceClick(category: Category)
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bind(context: Context,category: Category,listener: Listener,position: Int){
            itemView.tv_name.text= "Source: ${category.name}"
            itemView.tv_category.text="Category: ${category.category}"
            itemView.tv_desc.text=category.description
            itemView.setOnClickListener { listener.onSourceClick(category) }
        }
    }
}