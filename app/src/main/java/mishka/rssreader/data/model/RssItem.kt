package mishka.rssreader.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.time.format.DateTimeFormatter
import java.util.*

@Entity
@TypeConverters(EnclosureListConverter::class)
@Root(name = "item", strict = true)
class RssItem {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    @field:Element(name = "title", required = false)
    var title: String? = null
    @field:Element(name = "link", required = false)
    var link: String? = null
    @field:Element(name = "description", required = false)
    var description: String? = null
    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null
    @field:ElementList(name = "enclosure", required = false, inline = true)
    var enclosure: List<Enclosure>? = null
    @field:Element(name = "full-text", required = false)
    var fullText: String? = null

    fun getEnclosureLink(): String {
        return if (enclosure!!.isNotEmpty())
            return enclosure!![0].url!!
        else return ""
    }

    fun getFormattedPubDate(): String {
        val dateTimeFormatter = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z").withLocale(Locale.ENGLISH)

        val dateTime = DateTime.parse(pubDate, dateTimeFormatter)

        var month = dateTime.monthOfYear().getAsText(Locale("ru"))
        month = month.substring(0, 1).toUpperCase() + month.substring(1)

        return dateTime.toString("H:mm  |  d " + month)

    }

    @Root(name = "enclosure", strict = false)
    class Enclosure {
        @field:Attribute(name = "url")
        var url: String? = null
    }
}


