package ru.clevertec.springboothw.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "createddate")
    private LocalDateTime createdDate;
    @Column(name = "shortdescription")
    private String shortDescription;
    private String language;
    private String category;
    @ManyToOne
    private Person channelOwner;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "channel_subscribers",
                joinColumns = @JoinColumn(name = "channel_id"),
                inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> subscribers;

}
