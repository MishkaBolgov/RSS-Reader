package mishka.rssreader.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.AppDatabase;
import mishka.rssreader.data.Post;
import mishka.rssreader.data.PostDao;


public class SimpleDbHelper implements DbHelper {
    private AppDatabase database;
    @Inject
    public SimpleDbHelper(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, PostDao.DATABASE_NAME).build();
    }

    @Override
    public void insertAllPosts(List<Post> posts) {
        database.postDao().insertAll(posts);
    }

    @Override
    public List<Post> getAllPosts() {
        return database.postDao().getAll();
    }

    @Override
    public void erase() {
        database.postDao().eraseDatabase();
    }
}
