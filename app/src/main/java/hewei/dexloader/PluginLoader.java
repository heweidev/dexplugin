package hewei.dexloader;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * Created by hewei on 16-10-2.
 */

public class PluginLoader {
    public static void loadDex(Context ctx) {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }

        final String DEX_FILE_NAME = "librunner_dex.jar";
        File dexFile = new File(Environment.getExternalStorageDirectory(), DEX_FILE_NAME);

        DexClassLoader loader = new DexClassLoader(dexFile.getPath(), ctx.getCacheDir().getPath(),
                null, ctx.getClassLoader());

        try {
            HostImpl impl = new HostImpl();
            Class<?> cls = loader.loadClass("hewei.cmdhandler.TestPlugin");
            Object obj = cls.newInstance();
            if (obj instanceof IPlugin) {
                ((IPlugin)obj).init(impl);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
