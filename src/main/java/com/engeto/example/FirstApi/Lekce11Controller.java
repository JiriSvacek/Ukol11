package com.engeto.example.FirstApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

@CrossOrigin
@RestController
public class Lekce11Controller {

    TodoItemService todoItemService;

    public Lekce11Controller() throws SQLException {
        todoItemService = new TodoItemService();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleError(Exception e){
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    @GetMapping("/error")
    public TodoItem testError(@RequestParam(value = "hodnota", required = false) String hodnota) throws Exception {
        throw new Exception("Chyba "+hodnota);
    }

    @GetMapping("/todo")
    public Collection<TodoItem> getAllItems() throws SQLException {
        return todoItemService.getAllTodoItems();
    }

    @GetMapping("/todo/{id}")
    public TodoItem getItemById(@PathVariable("id") Integer id) throws Exception {
        TodoItem item = todoItemService.getById(id);

        if (item == null)
            throw new Exception("Some Exception");

        return item;
    }

    @PostMapping("/todo")
    public TodoItem postItem(@RequestBody TodoItem todoItem) throws SQLException {
        Integer generatedId = todoItemService.insertNewItem(todoItem);
        todoItem.setId(generatedId);
        return todoItem;
    }

    @PutMapping("/todo/{id}")
    public void putItem(@PathVariable("id") Integer id) throws SQLException {
        todoItemService.setItemToDone(id);
    }

    @PutMapping("todoDetail/")
    public void editItem(@RequestBody TodoItem todoItem) throws SQLException {
        todoItemService.editItem(todoItem);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteItem(@PathVariable("id") Integer id) throws SQLException {
        todoItemService.deleteItemById(id);
    }
}
