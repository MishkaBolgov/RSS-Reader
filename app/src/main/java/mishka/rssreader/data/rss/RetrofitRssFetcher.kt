package mishka.rssreader.data.rss

import javax.inject.Inject

import mishka.rssreader.data.model.Rss
import mishka.rssreader.data.model.RssItem
import mishka.rssreader.ui.channelsettings.ChannelConfigUtils
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.io.IOException

class RetrofitRssFetcher @Inject constructor(private val channelConfigUtils: ChannelConfigUtils) : RssHelper {

    override fun getRss(): List<RssItem>? {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://stub/")
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                Persister(AnnotationStrategy())
                        )
                )
                .build()

        val vestiRssApi: VestiRssApi = retrofit.create(VestiRssApi::class.java)
        val call = vestiRssApi.getRss(channelConfigUtils.getCurrentChannelUrl())

        var items: List<RssItem>? = null
        try {
            items = call.execute().body()?.channel?.items
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return items
    }

    interface VestiRssApi {
        @GET
        fun getRss(@Url url: String): Call<Rss>
    }


}
