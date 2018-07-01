package mishka.rssreader.data.model;

import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.Intent;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import io.realm.RealmList;
import io.realm.RealmObject;

@Root(name = "item", strict = false)
public class RealmRssItem extends RealmObject {
    @PrimaryKey
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
    private RealmList<RealmEnclosure> enclosure;
    @Element(name = "full-text", required = false)
    private String fullText;


    public String getEnclosureLink() {
        if (enclosure.size() > 0)
            return enclosure.get(0).getUrl();
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

    public RealmList<RealmEnclosure> getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(RealmList<RealmEnclosure> enclosure) {
        this.enclosure = enclosure;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
