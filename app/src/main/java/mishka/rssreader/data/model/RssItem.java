package mishka.rssreader.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;
import java.util.Locale;

@Entity
@TypeConverters(EnclosureListConverter.class)
@Root(name = "item", strict = false)
public class RssItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Element(name = "title", required = false)
    private String title;
    @Element(name = "link", required = false)
    private String link;
    @Element(name = "description", required = false)
    private String description;
    @Element(name = "pubDate", required = false)
    private String pubDate;
    @ElementList(name = "enclosure", required = false, inline = true)
    private List<Enclosure> enclosure;
    @Element(name = "full-text", required = false)
    private String fullText;

    @Root(name = "enclosure", strict = false)
    public static class Enclosure {
        @Attribute(name = "url")
        private String url;
    }

    public String getEnclosureLink() {
        if (enclosure.size() > 0)
            return enclosure.get(0).url;
        else return "";
    }


    public boolean hasImage() {
        if ((enclosure == null) || (enclosure.size() == 0))
            return false;

        return true;
    }


    public String getFormattedPubDate() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z").withLocale(Locale.ENGLISH);

        DateTime dateTime = DateTime.parse(pubDate, dateTimeFormatter);

        String month = dateTime.monthOfYear().getAsText(new Locale("ru"));
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        String formattedPubDate = dateTime.toString("H:mm  |  d " + month);

        return formattedPubDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<Enclosure> getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(List<Enclosure> enclosure) {
        this.enclosure = enclosure;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }
}

