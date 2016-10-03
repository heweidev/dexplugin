package hewei.cmdhandler;

import hewei.dexloader.IHost;
import hewei.dexloader.IPlugin;

/**
 * Created by hewei on 16-10-2.
 */

public class TestPlugin implements IPlugin {
    private IHost mHost;

    @Override
    public void init(IHost host) {
        mHost = host;
        mHost.log("test plugin init.");
    }

    @Override
    public void onCmd(String cmd, String data) {
        mHost.log("cmd: " + cmd + ", data: " + data);
    }

    @Override
    public void uninit() {
        mHost.log("test plugin uninit.");
    }
}
