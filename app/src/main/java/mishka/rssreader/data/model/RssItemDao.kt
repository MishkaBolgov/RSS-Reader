package mishka.rssreader.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
interface RssItemDao {
    @Insert
    fun insertAll(items: List<RssItem>)

    @Query("DELETE FROM rssitem")
    fun deleteAll()

    @Query("SELECT * FROM rssitem")
    fun getAll(): LiveData<List<RssItem>>

    @Query("SELECT * FROM rssitem WHERE id=:id")
    fun getItemById(id: Int): LiveData<RssItem>
}
