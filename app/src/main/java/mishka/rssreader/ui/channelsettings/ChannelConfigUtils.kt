package mishka.rssreader.ui.channelsettings

import android.content.Context
import javax.inject.Inject

const val CHANNEL_URLS_PREF = "channel_urls"
const val CURRENT_CHANNEL_URL_PREF = "current_channel"
const val CURRENT_CHANNEL_KEY = "current_channel_key"

val DEFAULT_CHANNEL_URLS = arrayListOf("https://www.vesti.ru/vesti.rss",
        "http://static.feed.rbc.ru/rbc/logical/footer/news.rss",
        "https://www.vedomosti.ru/rss/news")

class ChannelConfigUtils @Inject constructor(val context: Context) {
    init {
        if (!isSharedPrefsContainsDefaultUrl()) {
            for (url in DEFAULT_CHANNEL_URLS)
                addRssChannel(url)
            setCurrentChannelUrl(DEFAULT_CHANNEL_URLS[0])
        }
    }

    private fun isSharedPrefsContainsDefaultUrl(): Boolean {
        val prefs = context.getSharedPreferences(CURRENT_CHANNEL_URL_PREF, Context.MODE_PRIVATE)
        return prefs.contains(CURRENT_CHANNEL_KEY)
    }

    fun getRssChannelUrlsArray(): List<String> {
        val prefs = context.getSharedPreferences(CHANNEL_URLS_PREF, Context.MODE_PRIVATE)
        return prefs.all.keys.toList()
    }

    fun addRssChannel(url: String) {
        val prefs = context.getSharedPreferences(CHANNEL_URLS_PREF, Context.MODE_PRIVATE)
        val prefEditor = prefs.edit()

        prefEditor.putString(url, "")

        prefEditor.apply()
    }

    fun getCurrentChannelUrl(): String {
        val prefs = context.getSharedPreferences(CURRENT_CHANNEL_URL_PREF, Context.MODE_PRIVATE)
        return prefs.getString(CURRENT_CHANNEL_KEY, "")
    }

    fun setCurrentChannelUrl(url: String) {
        val prefs = context.getSharedPreferences(CURRENT_CHANNEL_URL_PREF, Context.MODE_PRIVATE)
        val prefEditor = prefs.edit()

        prefEditor.putString(CURRENT_CHANNEL_KEY, url)

        prefEditor.apply()
    }

    fun deleteChannel(urlToDelete: String) {
        val prefs = context.getSharedPreferences(CHANNEL_URLS_PREF, Context.MODE_PRIVATE)
        val prefEditor = prefs.edit()

        prefEditor.remove(urlToDelete)

        prefEditor.apply()
    }

    fun resetCurrentChannelUrl() {
        var prefs = context.getSharedPreferences(CURRENT_CHANNEL_URL_PREF, Context.MODE_PRIVATE)
        var prefEditor = prefs.edit()
        prefEditor.clear()
        prefEditor.apply()
    }

    fun deleteAllChannels() {
        var prefs = context.getSharedPreferences(CHANNEL_URLS_PREF, Context.MODE_PRIVATE)
        var prefEditor = prefs.edit()
        prefEditor.clear()
        prefEditor.apply()
    }
}