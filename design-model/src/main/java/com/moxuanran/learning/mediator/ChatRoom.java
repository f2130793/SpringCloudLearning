package com.moxuanran.learning.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @date 2022/9/29 10:54
 */
public abstract class ChatRoom {
    protected String name;
    protected List<User> users = new ArrayList<>();

    public ChatRoom(String name) {
        this.name = name;
    }

    protected void register(User user) {
        users.add(user);
    }

    protected void deregister(User user) {
        users.remove(user);
    }

    protected abstract void sendMsg(User from, User to, String msg);
    protected abstract String processMsg(User from, User to, String msg);


}
