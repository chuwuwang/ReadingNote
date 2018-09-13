package jdk8.other;

import java.time.ZoneId;

public class String_Join {

    public static void main(String[] args) {
        String join = String.join("/", "user", "local", "bin");
        System.out.println(join);

        join = String.join(",", ZoneId.getAvailableZoneIds());
        System.out.println(join);
    }

}
