package com.sf.IteratorMode;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcreteIterator implements Iterator {

    private ArrayList collection = null;
    private int currentPosition = 0;

    ConcreteIterator(ArrayList list) {
        this.collection = list;
    }

    @Override
    public boolean hasNext() {
        if(this.currentPosition >=collection.size())
            return false;
        return true;
    }

    @Override
    public Object next() {
        return this.collection.get(this.currentPosition++);
    }

}
