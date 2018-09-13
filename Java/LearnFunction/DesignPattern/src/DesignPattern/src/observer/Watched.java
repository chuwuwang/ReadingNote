package observer;

/**
 * 抽象主题角色，watched：被观察
 *
 * @author lee.shenzhou
 */
public interface Watched {

    void addWatcher(Watcher watcher);

    void removeWatcher(Watcher watcher);

    void notifyWatchers(String str);

}
