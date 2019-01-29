package state;

/**
 * 抽象状态类
 */
public interface State {

    /**
     * 状态对应的处理
     */
    void handle(String parameter);

}