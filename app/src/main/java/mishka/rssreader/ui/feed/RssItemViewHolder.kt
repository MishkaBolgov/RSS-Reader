package mishka.rssreader.ui.feed

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import mishka.rssreader.R
import mishka.rssreader.data.model.RssItem

class RssItemViewHolder(itemView: View, private val itemClickListener: OnFeedItemClickListener) : RecyclerView.ViewHolder(itemView) {

    var title: TextView? = null
    var pubDate: TextView? = null

    var post: RssItem? = null
        set(value) {
            field = value
            title?.text = value?.title
            pubDate?.text = value?.pubDate
            itemView.setOnClickListener { itemClickListener.onItemClick(post!!.id!!) }
        }

    init {
        title = itemView.findViewById(R.id.title)
        pubDate = itemView.findViewById(R.id.pub_date)
    }

}
