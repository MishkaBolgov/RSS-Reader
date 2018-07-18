package mishka.rssreader.ui.channelsettings


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.OnClick
import butterknife.OnItemLongClick
import kotlinx.android.synthetic.main.fragment_channel_settings.*
import kotlinx.android.synthetic.main.fragment_channel_settings.view.*

import mishka.rssreader.R
import mishka.rssreader.di.component.ChannelSettingsComponent
import mishka.rssreader.di.component.DaggerChannelSettingsComponent
import mishka.rssreader.ui.BaseActivity
import mishka.rssreader.ui.feed.FeedActivity
import javax.inject.Inject

class ChannelSettingsFragment : Fragment() {
    var isNewChannelLayoutVisible = false
    @Inject
    lateinit var viewModel: ChannelSettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_channel_settings, container, false)

        val channelSettingsComponent = DaggerChannelSettingsComponent.builder().applicationComponent((activity as BaseActivity).getApplicationComponent()).build()
        channelSettingsComponent.inject(this)

        var adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, viewModel.channelUrls)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        view.channelSelectSpinner.adapter = adapter



        view.channelSelectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                val selectedUrl = view?.findViewById<TextView>(android.R.id.text1)?.text as String
                viewModel.setCurrentChannelUrl(selectedUrl)
                (activity as FeedActivity).notifyCurrentChannelChanged()
            }
        }

        view.btnAddChannel.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                when(isNewChannelLayoutVisible){
                    true -> hideNewChannelLayout()
                    false -> showNewChannelLayout()
                }
            }

        })

        view.btnSaveChannel.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                viewModel.addChannel(etNewChannelName.text.toString())
                etNewChannelName.setText("")
                hideNewChannelLayout()

                adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, viewModel.channelUrls)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                view.channelSelectSpinner.adapter = adapter
            }
        })

        return view
    }

    fun showNewChannelLayout(){
        ltNewChannel.visibility = View.VISIBLE
        isNewChannelLayoutVisible = true
    }

    fun hideNewChannelLayout(){
        ltNewChannel.visibility = View.GONE
        isNewChannelLayoutVisible = false
    }


}
