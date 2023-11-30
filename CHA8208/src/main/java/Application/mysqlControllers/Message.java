package Application.mysqlControllers;

import org.springframework.data.annotation.PersistenceConstructor;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "message")
// @ XmlRootElement idica que Spring tiene que tratar de convertir un objeto del tipo anotado a XML si se requiere.
@XmlRootElement
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    @ManyToOne
    @JoinColumn(name = "chat")
    Chat chat;

    @ManyToOne
    @JoinColumn(name = "sendBy")
    User sendBy;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public User getSendBy() {
        return sendBy;
    }

    public Message(){}

    public Message(Long id, String message, Chat chat, User sendBy){
        this.id = id;
        this.message = message;
        this.chat = chat;
        this.sendBy = sendBy;
    }

    @PersistenceConstructor
    public Message(String message, Chat chat, User sendBy){
        this.message = message;
        this.chat = chat;
        this.sendBy = sendBy;
    }

}
