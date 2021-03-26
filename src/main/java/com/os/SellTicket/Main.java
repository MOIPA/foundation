package com.os.SellTicket;

/**
 * @author tr
 * @date 2020/12/9 17:26
 */
public class Main {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Seller seller1 = new Seller(ticket);
        Seller seller2 = new Seller(ticket);
        Seller seller3 = new Seller(ticket);
        new Thread(seller1).start();
        new Thread(seller2).start();
        new Thread(seller3).start();
    }
}
