package observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched {

    // 存放观察者
    private List<Watcher> mList = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        mList.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        mList.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {
        // 自动调用实际上是主题进行调用的
        for (Watcher watcher : mList) {
            watcher.update(str);
        }
    }

}