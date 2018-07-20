package mishka.rssreader.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import mishka.rssreader.R
import mishka.rssreader.data.model.Channel
import mishka.rssreader.data.model.RssItem

class FeedAdapter @Inject constructor(val itemClickListener: OnFeedItemClickListener) : RecyclerView.Adapter<RssItemViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.post_view, parent, false)

        return RssItemViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: RssItemViewHolder, position: Int) {
        holder.post = posts!![position]
    }

    var posts: List<RssItem>? = ArrayList()
    set(value){
        field = value?:ArrayList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int  = posts!!.size
}

