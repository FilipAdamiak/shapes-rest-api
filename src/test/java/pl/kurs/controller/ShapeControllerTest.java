package pl.kurs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import pl.kurs.model.User;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.command.UpdateShapeCommand;
import pl.kurs.model.dto.ResultDto;
import pl.kurs.model.searchcriteria.Parameters;
import pl.kurs.model.searchcriteria.SearchParameters;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("no-liquibase")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ShapeControllerTest {

    @Autowired
    private MockMvc postman;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user")
    void shouldAddShape() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Parameters parameters = Parameters.builder()
                .radius(10.0)
                .build();

        String saveJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().plusHours(2))
                        .type("circle")
                        .parameter(parameters)
                        .build());

        String content = postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ResultDto resultDto = objectMapper.readValue(content, ResultDto.class);

        postman.perform(MockMvcRequestBuilders.get("/shapes/" + resultDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("type").value("circle"))
                .andExpect(jsonPath("createdBy").value("Adam Nowak"))
                .andExpect(jsonPath("parameters").value("radius: 10.0"))
                .andExpect(jsonPath("version").value(0));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldNotAddShape() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();

        String incorrectJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().minusHours(1))
                        .type("DUPA")
                        .parameter(null)
                        .build());


        postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(incorrectJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].error", containsInAnyOrder("PARAMETERS_NOT_NULL", "INCORRECT_SHAPE_TYPE", "DATE_FROM_THE_PAST")))
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("type", "date", "parameter")));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldSearchShape() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Parameters parameters = Parameters.builder()
                .width(10.0)
                .height(5.0)
                .build();

        String saveJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().plusHours(2))
                        .type("rectangle")
                        .parameter(parameters)
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SearchParameters searchParameters = SearchParameters.builder()
                .heightFrom(2.0)
                .heightTo(7.0)
                .widthFrom(8.0)
                .widthTo(12.0)
                .createdFrom(LocalDateTime.now().plusHours(1))
                .createdTo(LocalDateTime.now().plusHours(3))
                .userEmail(user1.getEmail())
                .build();

        String searchJson = objectMapper.writeValueAsString(
                SearchShapeCommand.builder()
                        .type("rectangle")
                        .parameter(searchParameters)
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/shapes/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchJson))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    void shouldNotSearchShapeBecauseOfIncorrectSearchParameters() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Parameters parameters = Parameters.builder()
                .width(10.0)
                .height(5.0)
                .build();

        String saveJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().plusHours(2))
                        .type("rectangle")
                        .parameter(parameters)
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //bas search type
        String searchJson = objectMapper.writeValueAsString(
                SearchShapeCommand.builder()
                        .type("DUPA")
                        .parameter(null)
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/shapes/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].error", containsInAnyOrder("INCORRECT_SHAPE_TYPE", "PARAMETERS_NOT_NULL")))
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("type", "parameter")));
    }



    @Test
    void shouldReturnUnauthorizedRequestStatus() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Parameters parameters = Parameters.builder()
                .width(10.0)
                .height(5.0)
                .build();

        String saveJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().plusHours(2))
                        .type("rectangle")
                        .parameter(parameters)
                        .build());

        postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user")
    void shouldEditShape() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Parameters parameters = Parameters.builder()
                .width(10.0)
                .height(5.0)
                .build();

        String saveJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().plusHours(2))
                        .type("rectangle")
                        .parameter(parameters)
                        .build());

        String posted = postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ResultDto resultDto = objectMapper.readValue(posted, ResultDto.class);

        Parameters newParams = Parameters.builder()
                .width(10.0)
                .height(5.0)
                .build();
        User newUser = User.builder()
                .name("Pawel")
                .surname("Polak")
                .email("polo123@gmail.com")
                .build();

        String editJson = objectMapper.writeValueAsString(
                UpdateShapeCommand.builder()
                        .parameter(newParams)
                        .user(newUser)
                        .date(LocalDateTime.now())
                        .version(0)
                        .build());

        //result after edit - version 1
        postman.perform(MockMvcRequestBuilders.put("/shapes/" + resultDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(editJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("type").value("rectangle"))
                .andExpect(jsonPath("version").value(1))
                .andExpect(jsonPath("createdBy").value("Pawel Polak"));
    }

    @Test
    @WithMockUser(username = "user")
    void shouldNotEditShapeBecauseOfIncorrectUpdateArguments() throws Exception {
        User user1 = User.builder()
                .name("Adam")
                .surname("Nowak")
                .email("adas123@gmail.com")
                .build();
        Parameters parameters = Parameters.builder()
                .width(10.0)
                .height(5.0)
                .build();

        String saveJson = objectMapper.writeValueAsString(
                CreateShapeCommand.builder()
                        .user(user1)
                        .date(LocalDateTime.now().plusHours(2))
                        .type("rectangle")
                        .parameter(parameters)
                        .build());

        String posted = postman.perform(MockMvcRequestBuilders.post("/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ResultDto resultDto = objectMapper.readValue(posted, ResultDto.class);

        //try edit with old user, null version and null params
        String editJson = objectMapper.writeValueAsString(
                UpdateShapeCommand.builder()
                        .parameter(null)
                        .user(user1)
                        .date(LocalDateTime.now())
                        .version(null)
                        .build());

        postman.perform(MockMvcRequestBuilders.put("/shapes/" + resultDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(editJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].error", containsInAnyOrder("PARAMETERS_NOT_NULL", "VERSION_NOT_EMPTY", "USER_NOT_UNIQUE")))
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("user", "parameter", "version")));
    }

}