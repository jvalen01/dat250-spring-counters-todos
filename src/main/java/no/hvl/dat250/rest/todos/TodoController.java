package no.hvl.dat250.rest.todos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  private Map<Long, Todo> todos = new HashMap<>();
  private long currentId = 0;

  @PostMapping
  public ResponseEntity<Todo> create(@RequestBody Todo todo) {
    currentId++;
    todo.setId(currentId);
    todos.put(currentId, todo);
    return new ResponseEntity<>(todo, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable Long id) {
    Todo todo = todos.get(id);
    if (todo == null) {
      Map<String, String> response = new HashMap<>();
      response.put("message", String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Todo>> getAll() {
    List<Todo> todosList = todos.values().stream().collect(Collectors.toList());
    return new ResponseEntity<>(todosList, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
    if (!todos.containsKey(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    todo.setId(id);
    todos.put(id, todo);
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
    if (!todos.containsKey(id)) {
      Map<String, String> response = new HashMap<>();
      response.put("message", String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    todos.remove(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

