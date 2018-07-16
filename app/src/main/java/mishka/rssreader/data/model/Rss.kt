package mishka.rssreader.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
class Rss {
    @field:Element var channel: Channel? = null
}

