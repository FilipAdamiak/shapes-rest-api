package pl.kurs.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.model.User;
import pl.kurs.model.command.CreateUserCommand;
import pl.kurs.model.command.UpdateUserCommand;
import pl.kurs.model.dto.ResultDto;
import pl.kurs.model.dto.UserDto;
import pl.kurs.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping("/{id}/shapes")
    public ResponseEntity<List<ResultDto>> getUserShapes(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id)
                .getShapes()
                .stream()
                .map(s -> modelMapper.map(s, ResultDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, UserDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable int id) {
        User user = userService.findById(id);
        return new ResponseEntity(modelMapper.map(user, UserDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid CreateUserCommand command) {
        User toAdd = userService.addUser(command);
        return new ResponseEntity(modelMapper.map(toAdd, UserDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.softDelete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable int id, @RequestBody @Valid UpdateUserCommand command) {
        User user = userService.findById(id);
        User edited = userService.editUser(user, command);
        return new ResponseEntity(modelMapper.map(edited, UserDto.class), HttpStatus.OK);
    }

}
