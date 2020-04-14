package com.sf.IteratorMode;


import java.util.ArrayList;

/**
 * @author tr
 * @content ConcreteAggreate 作为一个实体类维护自己的信息，比如project，维护自己的时间，名字等。为了实现迭代器模式需要加入
 * 一个collection，提供添加方法，将构造方法私有化，每次用户添加的时候，利用私有构造方法创建自己作为实体类的对象，再添加到维护的队列中，
 * 这样导致：ConcreteAggreate不仅有自己的实体类信息，还有一个集合维护所有ConcreteAggreate,这样迭代时只需要把包含自身集合的集合传入
 * 迭代器，迭代器负责迭代逻辑，只提供用户next，hasnext等方法
 */
public class ConcreteAggreate implements Aggreate {

    private ArrayList collection = new ArrayList();

    private String info = "";

    private ConcreteAggreate(String info) {
        this.info = info;
    }

    public ConcreteAggreate() {

    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void addItem(String name) {
        this.collection.add(new ConcreteAggreate(name));
    }

    @Override
    public ConcreteIterator creteIterator() {
        return new ConcreteIterator(this.collection);
    }
}
