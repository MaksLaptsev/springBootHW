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
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_channels",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private List<Channel> ownChannels;

}
