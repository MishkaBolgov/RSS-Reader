package mishka.rssreader.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mishka.rssreader.RssApplication;
import mishka.rssreader.di.component.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {
    protected ApplicationComponent getApplicationComponent(){
        return ((RssApplication)getApplication()).getApplicationComponent();
    }
}
