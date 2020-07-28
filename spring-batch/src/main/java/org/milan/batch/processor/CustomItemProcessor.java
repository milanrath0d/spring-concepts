package org.milan.batch.processor;

import org.milan.bean.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom item processor
 *
 * @author Milan Rathod
 */
@Component
public class CustomItemProcessor implements ItemProcessor<User, User> {

    private static final Map<String, String> DEPT_NAMES = new HashMap<>();

    public CustomItemProcessor() {
        DEPT_NAMES.put("001", "TECH");
        DEPT_NAMES.put("002", "OPS");
        DEPT_NAMES.put("003", "HR");
    }

    @Override
    public User process(User user) throws Exception {
        String deptCode = user.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        user.setDept(dept);
        return user;
    }
}
