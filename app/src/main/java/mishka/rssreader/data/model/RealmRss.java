package mishka.rssreader.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class RealmRss {
    @Element
    private RealmChannel channel;

    public RealmChannel getChannel() {
        return channel;
    }

}
