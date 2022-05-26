package pl.kurs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.error.EntityNotFoundException;
import pl.kurs.model.command.CreateUserCommand;
import pl.kurs.model.command.UpdateUserCommand;
import pl.kurs.model.dto.UserDto;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("no-liquibase")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc postman;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user")
    void shouldAddDoctor() throws Exception {
        String saveJson = objectMapper.writeValueAsString(
                CreateUserCommand.builder()
                        .name("Pawel")
                        .surname("Nowak")
                        .email("pawlo123@gmail.com")
                        .build());

        String content = postman.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto userDto = objectMapper.readValue(content, UserDto.class);

        postman.perform(MockMvcRequestBuilders.get("/users/" + userDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Pawel"))
                .andExpect(jsonPath("surname").value("Nowak"))
                .andExpect(jsonPath("shapesCount").value(0))
                .andExpect(jsonPath("version").value(0))
                .andExpect(jsonPath("email").value("pawlo123@gmail.com"))
                .andExpect(jsonPath("deleted").value("false"));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldNotAddStudentBecauseNotUniqueMailAndEmptyParmeters() throws Exception {
        String saveJson = objectMapper.writeValueAsString(
                CreateUserCommand.builder()
                        .name("Pawel")
                        .surname("Nowak")
                        .email("pawlo123@gmail.com")
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated());

        String sameEmailUser = objectMapper.writeValueAsString(
                CreateUserCommand.builder()
                        .name("")
                        .surname("")
                        .email("pawlo123@gmail.com")
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sameEmailUser))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].error", containsInAnyOrder("NAME_NOT_EMPTY", "SURNAME_NOT_EMPTY", "EMAIL_NOT_UNIQUE")))
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("email", "name", "surname")));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldDeleteUser() throws Exception {
        String saveJson = objectMapper.writeValueAsString(
                CreateUserCommand.builder()
                        .name("Pawel")
                        .surname("Nowak")
                        .email("pawlo123@gmail.com")
                        .build());

        String saveContent = postman.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto user = objectMapper.readValue(saveContent, UserDto.class);

        String content = postman.perform(MockMvcRequestBuilders.get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto userDto = objectMapper.readValue(content, UserDto.class);

        //safe delete user
        postman.perform(MockMvcRequestBuilders.delete("/users/" + userDto.getId()))
                .andExpect(status().isNoContent());

        postman.perform(MockMvcRequestBuilders.get("/users/" + user.getId()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof EntityNotFoundException))
                .andExpect(jsonPath("entityName").value("User"))
                .andExpect(jsonPath("entityKey").value(user.getId()));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldEditUser() throws Exception {
        String saveJson = objectMapper.writeValueAsString(
                CreateUserCommand.builder()
                        .name("Pawel")
                        .surname("Nowak")
                        .email("pawlo123@gmail.com")
                        .build());

        String saved = postman.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto userDto = objectMapper.readValue(saved, UserDto.class);

        String editJson = objectMapper.writeValueAsString(
                UpdateUserCommand.builder()
                        .name("Michal")
                        .surname("Monko")
                        .version(0)
                        .build());

        postman.perform(MockMvcRequestBuilders.put("/users/" + userDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(editJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Michal"))
                .andExpect(jsonPath("surname").value("Monko"))
                .andExpect(jsonPath("version").value(1));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldNotEditUser() throws Exception {
        String saveJson = objectMapper.writeValueAsString(
                CreateUserCommand.builder()
                        .name("Pawel")
                        .surname("Nowak")
                        .email("pawlo123@gmail.com")
                        .build());

        String saved = postman.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto userDto = objectMapper.readValue(saved, UserDto.class);

        String editJson = objectMapper.writeValueAsString(
                UpdateUserCommand.builder()
                        .name("")
                        .surname("")
                        .version(null)
                        .build());

        postman.perform(MockMvcRequestBuilders.put("/users/" + userDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(editJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].error", containsInAnyOrder("VERSION_NOT_EMPTY", "NAME_NOT_EMPTY", "SURNAME_NOT_EMPTY")))
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("version", "name", "surname")));
    }

}