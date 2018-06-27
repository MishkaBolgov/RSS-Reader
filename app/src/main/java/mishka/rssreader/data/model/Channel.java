package mishka.rssreader.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {
    @ElementList(name = "item", required = false, inline = true)
    private List<RssItem> items;

    public List<RssItem> getItems() {
        return items;
    }
}
