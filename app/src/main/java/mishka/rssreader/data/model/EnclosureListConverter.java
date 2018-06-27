package mishka.rssreader.data.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import mishka.rssreader.data.model.RssItem;

public class EnclosureListConverter {

    @TypeConverter
    public List<RssItem.Enclosure> jsonToEnclosure(String json) {
        Type type = new TypeToken<List<RssItem.Enclosure>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    @TypeConverter
    public String enclosuresToJson(List<RssItem.Enclosure> enclosures) {
        return new Gson().toJson(enclosures);
    }
}
