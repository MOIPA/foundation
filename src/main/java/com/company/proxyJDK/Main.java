package com.company.proxyJDK;

public class Main {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserServiceImpl proxy =  myInvocationHandler.getProxy();
        proxy.add();
    }
}
