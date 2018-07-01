package mishka.rssreader.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class RealmChannel {
    @ElementList(name = "item", required = false, inline = true)
    private List<RealmRssItem> items;

    public List<RealmRssItem> getItems() {
        return items;
    }
}
