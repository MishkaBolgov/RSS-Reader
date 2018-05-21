package mishka.rssreader.data.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mishka.rssreader.data.Post;

public class PostFetcher {

    private static final String RSS_URL = "http://www.vesti.ru/vesti.rss";

    static List<Post> fetch() {

        List<Post> posts = new ArrayList<>();

        try

        {
            URL url = new URL(RSS_URL);
            InputStream inputStream = url.openConnection().getInputStream();
            posts = RssFeedParser.parse(inputStream);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return posts;
    }

}
