package com.sf.IteratorMode;

public interface Aggreate {

    String getInfo();

    void addItem(String name);

    ConcreteIterator creteIterator();
}
