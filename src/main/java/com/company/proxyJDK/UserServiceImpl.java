package com.company.proxyJDK;

public class UserServiceImpl implements IUserService{
    @Override
    public void add() {
        System.out.println("user add");
    }
}
