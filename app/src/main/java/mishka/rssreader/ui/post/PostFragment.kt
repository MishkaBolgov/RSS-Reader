package mishka.rssreader.ui.post


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.rssreader.R
import mishka.rssreader.data.model.RssItem
import mishka.rssreader.di.component.DaggerPostComponent
import mishka.rssreader.di.module.PostModule
import mishka.rssreader.ui.MainActivity
import javax.inject.Inject

class PostFragment : Fragment() {

    @Inject
    lateinit var viewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)

        val postId = arguments!!.getInt("postId")

        val postComponent = DaggerPostComponent.builder()
                .postModule(PostModule(activity!!, postId))
                .applicationComponent((activity as MainActivity).getApplicationComponent())
                .build()
        postComponent.inject(this)

        viewModel.post.observe(this, object : Observer<RssItem> {
            override fun onChanged(post: RssItem?) {
                if (post != null) {
                    showPost(post.title!!, post.fullText!!, post.getEnclosureLink())
                }
            }
        })

        (activity)?.invalidateOptionsMenu()

        return view
    }


    private fun showPost(title: String, fullText: String, imageUrl: String?) {
        if (imageUrl != null)
            Picasso.get().load(imageUrl).into(ivPostImage)
        tvTitle.text = title
        tvFullText.text = fullText
    }


}
