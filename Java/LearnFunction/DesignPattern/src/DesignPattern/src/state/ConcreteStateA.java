package state;

/**
 * 具体状态类 A
 *
 * @author lee.shenzhou
 */
public class ConcreteStateA implements State {

    @Override
    public void handle(String parameter) {
        System.out.println("A handle:" + parameter);
    }

}