package hewei.dexloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by hewei on 16-10-3.
 */

public class TestReceiver extends BroadcastReceiver {
    private static final String ACTION_TEST = "hewei.test.ACTION_CMD";

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (!ACTION_TEST.equals(action)) {
            return;
        }

        final String cmd = intent.getStringExtra("cmd");
        String data  = intent.getStringExtra("data");
        try {
            data = new String(Base64.decode(data, Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }

        final String DEX_FILE_NAME = "libPlugin_dex.jar";
        File dexFile = new File(Environment.getExternalStorageDirectory(), DEX_FILE_NAME);
        IPlugin plugin = PluginLoader.loadDex(context, "hewei.cmdhandler.TestPlugin", dexFile);
        if (plugin != null) {
            plugin.onCmd(cmd, data);
        }
    }
}
