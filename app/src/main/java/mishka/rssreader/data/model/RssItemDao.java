package mishka.rssreader.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mishka.rssreader.data.model.RssItem;

@Dao
public interface RssItemDao {
    @Insert
    void insertAll(List<RssItem> items);

    @Query("DELETE FROM rssitem")
    void deleteAll();

    @Query("SELECT * FROM rssitem")
    LiveData<List<RssItem>> getAll();

    @Query("SELECT * FROM rssitem WHERE id=:id")
    LiveData<RssItem> getItemById(int id);
}
