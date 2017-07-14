package jaydahstudios.com.chatstories.model;

/**
 * Created by Jaystiqs on 7/6/2017.
 */

public class ChatModel {
    public int id;
    public String chatActor;
    public String chatMessage;
    public int isSend;

    public ChatModel(int id, String chatActor, String chatMessage, int isSend) {
        this.id = id;
        this.chatActor = chatActor;
        this.chatMessage = chatMessage;
        this.isSend = isSend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChatActor() {
        return chatActor;
    }

    public void setChatActor(String chatActor) {
        this.chatActor = chatActor;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public int isSend() {
        return isSend;
    }

    public void setSend(int send) {
        isSend = send;
    }
}
