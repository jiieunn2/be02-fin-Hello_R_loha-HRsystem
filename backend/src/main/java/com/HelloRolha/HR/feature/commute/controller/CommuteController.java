package com.HelloRolha.HR.feature.commute.controller;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.service.CommuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class CommuteController {

    private final CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @PostMapping("/commute")
    public ResponseEntity<CommuteDto> commute() {
        CommuteDto commuteDto = commuteService.commute();
        return ResponseEntity.ok().body(commuteDto);
    }

    @PatchMapping("/leave/{id}")
    public ResponseEntity<CommuteDto> leave(@PathVariable Integer id) {
        CommuteDto commuteDto = commuteService.leave(id);
        return ResponseEntity.ok().body(commuteDto);
    }
}

