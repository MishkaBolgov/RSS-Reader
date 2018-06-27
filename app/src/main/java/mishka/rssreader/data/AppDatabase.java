package mishka.rssreader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mishka.rssreader.data.model.RssItem;
import mishka.rssreader.data.model.RssItemDao;

@Database(entities = {RssItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RssItemDao rssItemDao();
}
