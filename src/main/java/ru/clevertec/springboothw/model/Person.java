package ru.clevertec.springboothw.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "person")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    @Column(name = "nickname")
    @EqualsAndHashCode.Include
    private String nickName;
    private String name;
    @Column(name = "email")
    private String email;
    @ManyToMany(mappedBy = "subscribers")
    private List<Channel> channelsSubscribed;
    @OneToMany(mappedBy = "channelOwner", fetch = FetchType.LAZY)
    private List<Channel> ownChannels;

}
