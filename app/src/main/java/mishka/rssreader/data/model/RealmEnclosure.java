package mishka.rssreader.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import io.realm.RealmObject;


@Root(name = "enclosure", strict = false)
public class RealmEnclosure extends RealmObject {
    @Attribute(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }
}
