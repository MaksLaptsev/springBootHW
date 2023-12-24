package ru.clevertec.springboothw.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.springboothw.model.Channel;

public final class ChannelSpecification {

    static public Specification<Channel> hasName(String name) {
        return (channel, cq, cb) -> cb.like(cb.lower(channel.get("name")), "%" + name + "%");
    }

    static public Specification<Channel> hasLanguage(String language) {
        return (channel, cq, cb) -> cb.like(cb.lower(channel.get("language")), "%" + language + "%");
    }

    static public Specification<Channel> hasCategory(String category) {
        return (channel, cq, cb) -> cb.like(cb.lower(channel.get("category")), "%" + category + "%");
    }
}
