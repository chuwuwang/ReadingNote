package state;

/**
 * 具体状态类 B
 *
 * @author lee.shenzhou
 */
public class ConcreteStateB implements State {

    @Override
    public void handle(String parameter) {
        System.out.println("B handle:" + parameter);
    }

}