package com.nsz.android.home;

public class Drawer {

    public int state;   // 当前交易所是否被选择
    public String name;

    public Drawer() {

    }

    public Drawer(String name, int state) {
        this.name = name;
        this.state = state;
    }

}
