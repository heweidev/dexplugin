package hewei.dexloader;

import android.util.Log;

/**
 * Created by hewei on 16-10-2.
 */

public class HostImpl implements IHost {
    private static final String TAG = "Host";

    @Override
    public void log(String s) {
        Log.d(TAG, s);
    }
}
