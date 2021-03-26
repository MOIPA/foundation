package com.ds.list;

/**
 * @author TR
 * @content CRUD of list class
 */
public interface IList {

    // print elements
    void printElements();

    // get list size
    Integer getSize();

    // get value
    Object getValueByIndex(Integer index);

    // insert value
    Integer insertValueByIndex(Integer index, Object value);

    // delete value
    Integer deleteValueByIndex(Integer index);

    // clear list
    void clearList();

    // add value
    Integer addValue(Object obj);

}
