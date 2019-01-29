package observer;

/**
 * 具体的观察者
 *
 * @author lee.shenzhou
 */
public class ConcreteWatcher implements Watcher {

    @Override
    public void update(String str) {
        System.out.println(str);
    }

}
