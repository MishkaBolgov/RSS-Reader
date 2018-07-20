package mishka.rssreader.di.component

import dagger.Component
import mishka.rssreader.di.ActivityScope
import mishka.rssreader.di.module.PostModule
import mishka.rssreader.ui.post.PostFragment

@Component(modules = [PostModule::class], dependencies = [ApplicationComponent::class])
@ActivityScope
public interface PostComponent {
    fun inject(postFragment: PostFragment)
}
