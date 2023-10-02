package no.hvl.dat250.rest.todos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {

    private final Map<Long, Todo> todos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Todo create(Todo todo) {
        long id = counter.incrementAndGet();
        todo.setId(id);
        todos.put(id, todo);
        return todo;
    }

    public Todo getOne(Long id) {
        return todos.get(id);
    }

    public List<Todo> getAll() {
        return new ArrayList<>(todos.values());
    }

    public Todo update(Todo todo) {
        if(todo.getId() != null && todos.containsKey(todo.getId())) {
            todos.put(todo.getId(), todo);
            return todo;
        }
        return null; // or throw an appropriate exception
    }

    public boolean delete(Long id) {
        if(todos.containsKey(id)) {
            todos.remove(id);
            return true;
        }
        return false;
    }
}
