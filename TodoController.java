package com.example.demo.Controller;

import com.example.demo.model.ToDo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TodoController {
    private List<ToDo> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<ToDo> getAllTasks(){
        return tasks;
    }

    @GetMapping("/{id}")
    public ToDo getTaskById(@PathVariable Long id)
    {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @PostMapping
    public ToDo createTask(@RequestBody ToDo task){
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id)
    {
        tasks.removeIf(t -> t.getId().equals(id));
    }


}
