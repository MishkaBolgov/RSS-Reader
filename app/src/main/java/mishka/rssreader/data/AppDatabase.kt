package mishka.rssreader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mishka.rssreader.data.model.RssItemDao;
import mishka.rssreader.data.model.RssItem

@Database(entities = [ RssItem::class ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rssItemDao(): RssItemDao
}
