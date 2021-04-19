package com.nsz.kotlin.open.source.realm;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Dog extends RealmObject {

    @Index
    @PrimaryKey
    public String id = UUID.randomUUID().toString();
    public String name;
    public int age;

}