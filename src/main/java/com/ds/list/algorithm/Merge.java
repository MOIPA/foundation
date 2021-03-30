package com.ds.list.algorithm;

import com.ds.Element;
import com.ds.list.IList;
import com.ds.list.linked.LinkedList;

/**
 * @author TR
 * @content merge algorithm
 * merge two sorted list into one list
 */
public class Merge {

    public static void main(String[] args) {
        IList first = new LinkedList();
        IList second = new LinkedList();
        first.addValue(new Element(1, ""));
        first.addValue(new Element(2, ""));
        first.addValue(new Element(5, ""));
        first.addValue(new Element(7, ""));
        second.addValue(new Element(3, ""));
        second.addValue(new Element(4, ""));
        second.addValue(new Element(6, ""));
        second.addValue(new Element(7, ""));
        second.addValue(new Element(8, ""));
        second.addValue(new Element(9, ""));
        MergeFunc merge = new MergeFunc();
        IList iList = merge.doMerge(first, second);
        iList.printElements();
    }

}

class MergeFunc {
    public IList doMerge(IList first, IList second) {
        IList newList = new LinkedList();
//        for (int i = 0; i < (first.getSize() > second.getSize() ? second.getSize() : first.getSize()); i++) {
        int pointFirst = 1;
        int pointSecond = 1;
        while (pointFirst <= first.getSize() && pointSecond <= second.getSize()) {
            Element valueFirst = (Element) first.getValueByIndex(pointFirst);
            Element valueSecond = (Element) second.getValueByIndex(pointSecond);

            if (valueFirst.getCode() <= valueSecond.getCode()) {
                newList.addValue(valueFirst);
                pointFirst++;
            } else if (valueFirst.getCode() > valueSecond.getCode()) {
                newList.addValue(valueSecond);
                pointSecond++;
            }

            if (pointFirst > first.getSize()) {
                for (; pointSecond <= second.getSize(); pointSecond++)
                    newList.addValue(second.getValueByIndex(pointSecond));
                break;
            }
            if (pointSecond > second.getSize()) {
                for (; pointFirst <= first.getSize(); pointFirst++)
                    newList.addValue(first.getValueByIndex(pointFirst));
                break;
            }

        }

        return newList;
    }
}
