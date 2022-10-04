package com.moxuanran.learning.mediator;

/**
 * @author wutao
 * @date 2022/9/29 10:52
 */
public class User {
    private String name;

    protected ChatRoom chatRoom;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void login(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        this.chatRoom.register(this);
    }

    protected void logout() {
        chatRoom.deregister(this);
        this.chatRoom = null;
    }

    protected void talk(User to,String msg) {
        if(chatRoom == null) {
            System.out.println("未登录");
            return;
        }
        chatRoom.sendMsg(this,to, msg);
    }

    public void listen(User from , User to,String msg) {
        System.out.print("【" + name + "的对话框】");
        System.out.println(chatRoom.processMsg(from, to, msg));
    }
}
