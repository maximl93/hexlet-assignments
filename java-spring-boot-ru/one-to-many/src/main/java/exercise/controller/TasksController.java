package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping(path = "")
    public List<TaskDTO> showAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::map)
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO showOne(@PathVariable long id) {
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO dto) {
        var userId = dto.getAssigneeId(); //достаем id разработчика из дто
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found")); // находим разработчика по id
        var task = taskMapper.map(dto); // мапим задачу из dto в сущность
        user.addTask(task); // добавляем задачу в список задач разработчика
        userRepository.save(user); // обновляем разработчика и тем самым в таблице задач появляется новая созданная задача
        return taskMapper.map(taskRepository.findByTitle(dto.getTitle()).get()); // возвращаем дто созданной задачи
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@Valid @RequestBody TaskUpdateDTO dto,
                          @PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")); // находим обновляемую задачу
        var assigneeId = dto.getAssigneeId();
        var user = userRepository.findById(assigneeId)
                        .orElseThrow(() -> new ResourceNotFoundException("User with id " + assigneeId + " not found")); // находим разработчика
        taskMapper.update(dto, task); // обновляем данные в задаче
        task.setAssignee(user); // обновляем разработчика
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")); // находим сущность задачи по id
        var assigneeId = task.getAssignee().getId(); // достаем из сущности id разработчика
        var user = userRepository.findById(assigneeId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + assigneeId + " not found")); // находим сущность разработчика по id
        user.removeTask(task); // удаляем из списка задач нужную задачу
        userRepository.save(user); // обновляем разработчика в бд и тем самым удаляется нужная задача
    }
    // END
}
