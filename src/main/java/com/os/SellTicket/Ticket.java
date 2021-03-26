package com.os.SellTicket;

import java.util.List;

/**
 * @author tr
 * @date 2020/12/9 17:21
 */
public class Ticket  {
    private  int ticketNo = 1000;

    public int getTicketNo() {
        return ticketNo;
    }

    public void decreateTicketNo() {
        ticketNo--;
    }
}
