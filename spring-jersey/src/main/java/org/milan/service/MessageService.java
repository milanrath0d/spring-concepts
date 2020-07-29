package org.milan.service;

import org.milan.database.Database;
import org.milan.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author Milan Rathod
 */
@Service
public class MessageService {

    private Map<Long, Message> messages = Database.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1L, "Hello 1", "Milan"));
        messages.put(2L, new Message(2L, "Hello 2", "Milan"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessagesByYear(int year) {
        List<Message> messagesByYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messagesByYear.add(message);
            }
        }
        return messagesByYear;
    }

    public List<Message> getMessagesByPage(int start, int size) {
        ArrayList<Message> messagesByPage = new ArrayList<>(messages.values());
        if (start + size > messagesByPage.size()) return new ArrayList<>();
        return messagesByPage.subList(start, start + size);
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message deleteMessage(long id) {
        return messages.remove(id);
    }
}
