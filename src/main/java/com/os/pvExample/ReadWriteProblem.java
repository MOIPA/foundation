package com.os.pvExample;

import com.os.pv.MySemaphore;
import com.os.pv.PVOperation;

public class ReadWriteProblem {
    public static void main(String[] args) {
        RSC RSC = new RSC();
    }

    public static class RSC {
        static MySemaphore rmutex = new MySemaphore(1);
        static MySemaphore wmutex = new MySemaphore(1);
        static int readCount = 0;
        static PVOperation pvOperation = new PVOperation();
    }

class Reader implements Runnable {

    @Override
    public void run() {
        RSC.pvOperation.doP(RSC.rmutex);
        if (RSC.readCount==0)
            //第一个读者负责加锁写，防止被写
            RSC.pvOperation.doP(RSC.wmutex);
        RSC.readCount++;
        //释放readcount使用权
        RSC.pvOperation.doV(RSC.rmutex);
    }
}
}
