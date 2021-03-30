package com.company.proxyCglib;

import com.company.proxyJDK.UserServiceImpl;

public class DoCGLib {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        UserServiceImpl proxyImp = (UserServiceImpl)proxy.getProxy(UserServiceImpl.class);
        proxyImp.add();
    }
}
