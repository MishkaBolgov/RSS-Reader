package mishka.rssreader.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(name = "channel", strict = false)
class Channel{
    @field:ElementList(name = "item", required = false, inline = true) var items: List<RssItem>? = null
}