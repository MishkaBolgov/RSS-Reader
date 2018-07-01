package mishka.rssreader.data.rss;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.model.RealmRss;
import mishka.rssreader.data.model.RealmRssItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public class RetrofitRssFetcher implements RssHelper {

    @Inject
    public RetrofitRssFetcher() {
    }

    @Override
    public List<RealmRssItem> getRealmRss() {
        Call<RealmRss> realmCall = getVestiRssApi().getRealmRss();
        List<RealmRssItem> realmItems = new ArrayList<>();

        try {
            realmItems = realmCall.execute().body().getChannel().getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return realmItems;
    }

    private VestiRssApi getVestiRssApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.vesti.ru/")
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                new Persister(new AnnotationStrategy())
                        )
                )
                .build();

        return retrofit.create(VestiRssApi.class);
    }

    interface VestiRssApi {
        @GET("vesti.rss")
        Call<RealmRss> getRealmRss();
    }
}
