package com.os.Test;

/**
 * @author tr
 * @date 2020/12/10 16:57
 */
public class Item {
    private String f = "";
    private int fi;

    public Item(String f, int fi) {
        this.f = f;
        this.fi = fi;
    }

    @Override
    public String toString() {
        return "Item{" +
                "f='" + f + '\'' +
                ", fi=" + fi +
                '}';
    }

    public Item(String f) {
        this.f = f;
    }

    public Item() {
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public int getFi() {
        return fi;
    }

    public void setFi(int fi) {
        this.fi = fi;
    }
}
