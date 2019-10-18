package com.nsz.android.home.contact;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SortModel> {

    public int compare(SortModel o1, SortModel o2) {
        boolean b1 = o1.sortLetters.equals("@") || o2.sortLetters.equals("#");
        boolean b2 = o1.sortLetters.equals("#") || o2.sortLetters.equals("@");
        boolean b3 = o1.sortLetters.equals("↑") || o2.sortLetters.equals("↑");
        if (b1) {
            return -1;
        } else if (b2) {
            return 1;
        } else if (b3) {
            return -1;
        } else {
            return o1.sortLetters.compareTo(o2.sortLetters);
        }
    }


}
