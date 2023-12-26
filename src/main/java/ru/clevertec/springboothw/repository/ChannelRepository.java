package ru.clevertec.springboothw.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.clevertec.springboothw.model.Channel;

import java.util.Optional;

public interface ChannelRepository extends PagingAndSortingRepository<Channel, Long>, JpaSpecificationExecutor<Channel> {
    Optional<Channel> findById(Long id);

    Channel save(Channel channel);

    boolean deleteById(Long id);

}
