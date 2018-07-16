package mishka.rssreader.data.rss

import mishka.rssreader.data.model.RssItem


interface RssHelper {
    fun getRss(): List<RssItem>?
}
