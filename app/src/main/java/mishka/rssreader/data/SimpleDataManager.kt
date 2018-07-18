package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import javax.inject.Inject;

import mishka.rssreader.data.model.RssItemDao;
import mishka.rssreader.data.model.RssItem
import mishka.rssreader.data.rss.RssHelper;

class SimpleDataManager @Inject constructor(val rssHelper: RssHelper, val rssItemDao: RssItemDao) : DataManager {

    override fun getPosts(): LiveData<List<RssItem>> {
        update()
        return rssItemDao.getAll()
    }

    override fun update() {
        AsyncPostFetcher(rssHelper, rssItemDao).execute()
    }

    override fun getPostById(postId: Int): LiveData<RssItem> {
        return rssItemDao.getItemById(postId)
    }

}

class AsyncPostFetcher(val rssHelper: RssHelper, val rssItemDao: RssItemDao) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg p0: Void?): Void? {
        val items = rssHelper.getRss()?:ArrayList()

        if (items.isNotEmpty()){
            rssItemDao.deleteAll()
            rssItemDao.insertAll(items)
        }
        return null
    }

}
