package mishka.rssreader.data.rss

import javax.inject.Inject

import mishka.rssreader.data.model.Rss
import mishka.rssreader.data.model.RssItem
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import java.io.IOException

class RetrofitRssFetcher @Inject constructor() : RssHelper {

    override fun getRss(): List<RssItem>? {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.vesti.ru/")
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                Persister(AnnotationStrategy())
                        )
                )
                .build()

        val vestiRssApi: VestiRssApi = retrofit.create(VestiRssApi::class.java)
        val call = vestiRssApi.getRss()

        var items : List<RssItem>? = null
        try {
            items = call.execute().body()?.channel?.items
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return items
    }

    interface VestiRssApi {
        @GET("vesti.rss")
        fun getRss(): Call<Rss>
    }
}
