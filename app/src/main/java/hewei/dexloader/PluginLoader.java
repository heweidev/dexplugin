package hewei.dexloader;

import android.content.Context;

import java.io.File;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * Created by hewei on 16-10-2.
 */

public class PluginLoader {
    private static HashMap<String, IPlugin> sPlugins = new HashMap<>();

    public static IPlugin loadDex(Context ctx, String clsName, File dexFile) {
        IPlugin plugin = sPlugins.get(clsName);
        if (plugin != null) {
            return plugin;
        }

        if (!dexFile.exists()) {
            return null;
        }

        DexClassLoader loader = new DexClassLoader(dexFile.getPath(), ctx.getCacheDir().getPath(),
                null, ctx.getClassLoader());

        try {
            HostImpl impl = new HostImpl();
            Class<?> cls = loader.loadClass(clsName);
            Object obj = cls.newInstance();
            if (obj instanceof IPlugin) {
                plugin = (IPlugin)obj;
                plugin.init(impl);
                return plugin;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
