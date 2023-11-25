package ru.clevertec.springboothw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nickname")
    private String nickName;
    private String name;
    @Column(name = "email")
    private String email;
    @ManyToMany(mappedBy = "subscribers")
    private List<Channel> channelsSubscribed;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_channels",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private List<Channel> ownChannels;

}
