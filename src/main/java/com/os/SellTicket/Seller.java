package com.os.SellTicket;

/**
 * @author tr
 * @date 2020/12/9 17:31
 */
public class Seller implements Runnable {
    private Ticket ticket;

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Seller(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ticket) {
                if (ticket.getTicketNo() > 0) {
                    System.out.printf("%s线程正在运行,第%d张票正在出售\n", Thread.currentThread().getName(), ticket.getTicketNo());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket.decreateTicketNo();
                }
            }
        }
    }
}
