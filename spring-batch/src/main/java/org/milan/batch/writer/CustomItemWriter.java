package org.milan.batch.writer;

import org.milan.bean.User;
import org.milan.repository.UserRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Custom item writer
 *
 * @author Milan Rathod
 */
@Component
public class CustomItemWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(Chunk<? extends User> users) {
        userRepository.saveAll(users.getItems());
    }
}
