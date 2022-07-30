package pl.kurs.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.model.Shape;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.command.UpdateShapeCommand;
import pl.kurs.model.dto.ResultDto;
import pl.kurs.service.ShapeService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shapes")
@RequiredArgsConstructor
public class ShapeController {

    private final ShapeService shapeService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResultDto> addShape(@RequestBody @Valid CreateShapeCommand command) {
        Shape saved = shapeService.addShape(command);
        return new ResponseEntity(modelMapper.map(saved, ResultDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ResultDto>> searchShape(@PageableDefault Pageable pageable, @RequestBody @Valid SearchShapeCommand search) {
        return ResponseEntity.ok(shapeService.findAll(pageable, search)
                .map(s -> modelMapper.map(s, ResultDto.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDto> getSingleShape(@PathVariable int id) {
        Shape object = shapeService.findByShapeId(id);
        return new ResponseEntity(modelMapper.map(object, ResultDto.class), HttpStatus.OK);
    }

    @GetMapping("/user_id={id}")
    public ResponseEntity<List<ResultDto>> findShapeByUserId(@PathVariable int id) {
        return ResponseEntity.ok(shapeService.findByUser(id)
                .stream()
                .map(s -> modelMapper.map(s, ResultDto.class))
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDto> editShape(@PathVariable int id, @RequestBody @Valid UpdateShapeCommand command) {
        Shape toEdit = shapeService.findByShapeId(id);
        Shape edited = shapeService.editShape(toEdit, command);
        return new ResponseEntity(modelMapper.map(edited, ResultDto.class), HttpStatus.OK);
    }


}
