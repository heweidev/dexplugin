package hewei.dexloader;

/**
 * Created by hewei on 16-10-2.
 */

public interface IPlugin {
    void init(IHost host);
    void uninit();
}
