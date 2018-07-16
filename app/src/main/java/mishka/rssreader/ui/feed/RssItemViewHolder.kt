package mishka.rssreader.ui.feed

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import mishka.rssreader.R
import mishka.rssreader.data.model.RssItem
import mishka.rssreader.ui.post.PostActivity

class RssItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var title: TextView? = null
    var pubDate: TextView? = null

    var post: RssItem? = null
        set(value) {
            field = value
            title?.text = value?.title
            pubDate?.text = value?.pubDate
        }

    init {
        title = itemView.findViewById(R.id.title)
        pubDate = itemView.findViewById(R.id.pub_date)
        itemView.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        val intent = Intent(v!!.context, PostActivity::class.java)
        intent.putExtra("post_id", post!!.id)
        v.context.startActivity(intent)
    }

}
