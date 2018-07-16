package mishka.rssreader.data.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

class EnclosureListConverter {

    @TypeConverter
    fun jsonToEnclosure(json: String): List<RssItem.Enclosure> {
        val type = object : TypeToken<List<RssItem.Enclosure>>() {}.getType()
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun enclosuresToJson(enclosures: List<RssItem.Enclosure>): String {
        return Gson().toJson(enclosures)
    }


}
