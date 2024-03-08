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


    @RequestMapping(method = RequestMethod.POST, value = "/commute")
    public ResponseEntity<Integer> commute(@RequestBody CommuteDto commuteDto) {
        Integer commuteId = commuteService.commute(commuteDto);
        return ResponseEntity.ok().body(commuteId);
    }
}
//    @Autowired
//    private CommuteService commuteService;
//
//    public CommuteController(CommuteService commuteService) {
//        this.commuteService=commuteService;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/commute")
//    public ResponseEntity commute(Integer id) {
//        return ResponseEntity.ok().body(commuteService.commute(id));
//    }
//
