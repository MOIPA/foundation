package com.sf.IteratorMode;

public class client {
    public static void main(String[] args) {
        Aggreate project = new ConcreteAggreate();
        project.addItem("be");
        project.addItem("stupid");
        ConcreteIterator concreteIterator = project.creteIterator();
        while (concreteIterator.hasNext()) {
            ConcreteAggreate next = (ConcreteAggreate) concreteIterator.next();
            System.out.println(next.getInfo());

        }
    }
}
