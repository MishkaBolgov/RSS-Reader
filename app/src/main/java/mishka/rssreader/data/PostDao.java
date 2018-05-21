package mishka.rssreader.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface PostDao {
    String DATABASE_NAME = "post";

    @Insert
    void insertAll(List<Post> posts);

    @Query("SELECT * FROM " + DATABASE_NAME)
    List<Post> getAll();

    @Query("DELETE FROM " + DATABASE_NAME)
    void eraseDatabase();

}
