package org.milan.batch.writer;

import org.milan.bean.User;
import org.milan.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void write(List<? extends User> users) {
        userRepository.saveAll(users);
    }
}
