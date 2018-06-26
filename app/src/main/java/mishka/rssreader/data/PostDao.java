package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface PostDao {
    String DATABASE_NAME = "post";

    @Insert
    void insertAll(List<Post> posts);

    @Query("DELETE FROM " + DATABASE_NAME)
    void deleteAll();

    @Query("SELECT * FROM post")
    LiveData<List<Post>> getPosts();

    @Query("SELECT * FROM post WHERE id=:postId")
    LiveData<Post> getPostById(int postId);
}
