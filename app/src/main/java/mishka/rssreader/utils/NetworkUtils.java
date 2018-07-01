package mishka.rssreader.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public class NetworkUtils {
    private Context context;

    @Inject
    public NetworkUtils(Context context) {
        this.context = context;
    }

    public boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null)
                return true;
        }
        return false;
    }
}
