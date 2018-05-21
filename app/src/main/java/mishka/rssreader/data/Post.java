package mishka.rssreader.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.Locale;

@Entity
public class Post implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String imageLink;
    private String fullText;

    public Post(String title, String link, String description, String pubDate, String imageLink, String fullText) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.fullText = fullText;
        if (isLinkOnImage(imageLink))
            this.imageLink = imageLink;
        else this.imageLink = null;
    }

    private boolean isLinkOnImage(String imageLink) {
        if (imageLink == null)
            return false;
        imageLink = imageLink.trim();
        return imageLink.endsWith(".jpg");
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", fullText='" + fullText + '\'' +
                '}';
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getFormattedPubDate() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z").withLocale(Locale.ENGLISH);

        DateTime dateTime = DateTime.parse(pubDate, dateTimeFormatter);

        String month = dateTime.monthOfYear().getAsText(new Locale("ru"));
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        String formattedPubDate = dateTime.toString("H:mm  |  d " + month);

        return formattedPubDate;
    }

    public boolean hasImage() {
        return imageLink != null;
    }
}
