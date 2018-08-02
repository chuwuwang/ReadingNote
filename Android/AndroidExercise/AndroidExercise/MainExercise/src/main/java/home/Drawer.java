package home;

/**
 * @author Created by Lee64 on 2017/10/9.
 */

public class Drawer {

    public String name;
    // 当前交易所是否被选择
    public int state;

    public Drawer() {

    }

    public Drawer(String name, int state) {
        this.name = name;
        this.state = state;
    }

}
