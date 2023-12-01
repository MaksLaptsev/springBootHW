package ru.clevertec.springboothw.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.clevertec.springboothw.model.Channel;
import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends PagingAndSortingRepository<Channel,Long> {
    List<Channel> findAllByNameContainingIgnoreCaseAndLanguageContainingIgnoreCaseAndAndCategoryContainingIgnoreCase(String name, String language, String category, Pageable pageable);
    Optional<Channel> findById(Long id);
    Channel saveAndFlush(Channel channel);
    boolean deleteById(Long id);
}
