package mishka.rssreader.data.rss;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mishka.rssreader.data.Post;

public class RssFeedParser {
    public static List<Post> parse(InputStream inputStream) throws IOException {
        String title = null;
        String description = null;
        String link = null;
        String pubDate = null;
        String fullText = null;
        String imageLink = null;
        String content = null;
        boolean isFeedItemParsing = false;
        List<Post> items = new ArrayList<>();


        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);
            xmlPullParser.next();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();
                String currentParsingTagName = xmlPullParser.getName();

                if (currentParsingTagName == null)
                    continue;

                if (eventType == XmlPullParser.START_TAG && currentParsingTagName.equals("item")) {
                    isFeedItemParsing = true;
                    continue;
                }

                if (eventType == XmlPullParser.END_TAG && currentParsingTagName.equals("item")) {
                    isFeedItemParsing = false;
                    continue;
                }

                if (!isFeedItemParsing || eventType == XmlPullParser.END_TAG)
                    continue;

                if (currentParsingTagName.equals("enclosure"))
                    imageLink = xmlPullParser.getAttributeValue(null, "url");
                else if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    content = xmlPullParser.getText();

                    if (currentParsingTagName.equals("title"))
                        title = content;

                    if (currentParsingTagName.equals("description"))
                        description = content;

                    if (currentParsingTagName.equals("link"))
                        link = content;

                    if (currentParsingTagName.contains("full-text"))
                        fullText = content;

                    if (currentParsingTagName.equals("pubDate"))
                        pubDate = content;
                }

                if (title != null && description != null && link != null & pubDate != null && fullText != null) {


                    items.add(new Post(title, link, description, pubDate, imageLink, fullText));

                    title = null;
                    description = null;
                    link = null;
                    pubDate = null;
                    fullText = null;
                    imageLink = null;

                    content = null;
                }

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return items;
    }
}
