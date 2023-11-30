package Application.mysqlControllers;

import org.springframework.data.annotation.PersistenceConstructor;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "chats")
// @ XmlRootElement indica que Spring tiene que tratar de convertir un objeto del tipo anotado a XML si se requiere.
@XmlRootElement
public class Chat {

    @Id
    @Column(name = "idChat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
        @JoinColumn(name = "user1_id")
    User user1;

    @ManyToOne
        @JoinColumn(name = "user2_id")
    User user2;

    public User getUser1() {
        return user1;
    }

    public Long getId() {
        return id;
    }

    public User getUser2() {
        return user2;
    }

    public Chat(){}

    public Chat(Long id, User user1, User user2){
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    @PersistenceConstructor
    public Chat(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

}
