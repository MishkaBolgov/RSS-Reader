package mishka.rssreader.ui.post;

import android.arch.lifecycle.Observer;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.rssreader.R
import mishka.rssreader.data.model.RssItem
import mishka.rssreader.di.component.DaggerPostComponent
import mishka.rssreader.di.module.PostModule
import mishka.rssreader.ui.BaseActivity

class PostActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        val intent = intent
        val postId = intent.getIntExtra("post_id", -1)

        setSupportActionBar(toolbar);

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        val postComponent = DaggerPostComponent.builder()
                .postModule(PostModule(this, postId))
                .applicationComponent(getApplicationComponent())
                .build()
        postComponent.inject(this)


        viewModel.post.observe(this, object : Observer<RssItem> {
            override fun onChanged(post: RssItem?) {
                if (post != null)
                    showPost(post.title!!, post.fullText!!, post.getEnclosureLink())
            }
        })

    }

    private fun showPost(title: String, fullText: String, imageUrl: String?) {
        if (imageUrl != null)
            Picasso.get().load(imageUrl).into(ivPostImage)
        tvTitle.text = title
        tvFullText.text = fullText
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
