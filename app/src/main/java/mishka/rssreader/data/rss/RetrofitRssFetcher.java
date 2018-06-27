package mishka.rssreader.data.rss;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.model.Rss;
import mishka.rssreader.data.model.RssItem;
import mishka.rssreader.data.rss.RssHelper;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public class RetrofitRssFetcher implements RssHelper {

    @Inject
    public RetrofitRssFetcher() {
    }

    public List<RssItem> getRss() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.vesti.ru/")
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                new Persister(new AnnotationStrategy())
                        )
                )
                .build();

        VestiRssApi vestiRssApi = retrofit.create(VestiRssApi.class);
        Call<Rss> call = vestiRssApi.getRss();

        List<RssItem> items = new ArrayList<>();
        try {
            items = call.execute().body().getChannel().getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    interface VestiRssApi {
        @GET("vesti.rss")
        Call<Rss> getRss();
    }
}
