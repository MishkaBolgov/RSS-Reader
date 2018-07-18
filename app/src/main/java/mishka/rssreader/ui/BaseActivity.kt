package mishka.rssreader.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mishka.rssreader.RssApplication;
import mishka.rssreader.di.component.ApplicationComponent;

abstract class BaseActivity : AppCompatActivity() {
    public fun getApplicationComponent() = (application as RssApplication).applicationComponent
}
