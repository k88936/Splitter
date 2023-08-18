package org.kvto;

import java.util.ArrayList;

public interface Splittable {

    ArrayList<Splittable> dependency = new ArrayList<>();
    int cost();
    ArrayList<Splittable> dependency();
    String GenBody();

    mode mode();
}
enum mode{
    green,
    effective,
    sync
    ;
}
