package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    public Task getTask() {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(6))
                .create();
        return task;
    }

    @Test
    public void testShow() throws Exception {
        Task testTask = taskRepository.save(getTask());

        var result = mockMvc.perform(get("/tasks/" + testTask.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains(testTask.getDescription());
    }

    @Test
    public void testDelete() throws Exception {
        Task testTask = taskRepository.save(getTask());

        mockMvc.perform(delete("/tasks/" + testTask.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        Task testTask = getTask();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(testTask));

        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        Task testTask = taskRepository.save(getTask());

        var newData = new HashMap<>();
        newData.put("description", "something new");

        var request = put("/tasks/" + testTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(newData));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        testTask = taskRepository.findById(testTask.getId()).get();
        assertThat(testTask.getDescription()).isEqualTo(newData.get("description"));
    }
    // END
}
