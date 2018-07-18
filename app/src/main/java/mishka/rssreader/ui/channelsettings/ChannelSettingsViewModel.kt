package mishka.rssreader.ui.channelsettings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class ChannelSettingsViewModel @Inject constructor(val channelConfigUtils: ChannelConfigUtils) : ViewModel() {

    fun addChannel(url: String) {
        channelConfigUtils.addRssChannel(url)
    }

    fun deleteChannel(urlToDelete: String) {
        channelConfigUtils.deleteChannel(urlToDelete)
    }

    fun setCurrentChannelUrl(selectedUrl: String) {
        channelConfigUtils.setCurrentChannelUrl(selectedUrl)
    }

    var channelUrls: List<String> = channelConfigUtils.getRssChannelUrlsArray()
        get() = channelConfigUtils.getRssChannelUrlsArray()
}