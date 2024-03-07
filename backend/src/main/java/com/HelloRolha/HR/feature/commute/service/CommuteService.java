package com.HelloRolha.HR.feature.commute.service;


import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.repository.CommuteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CommuteService {

    private final CommuteRepository commuteRepository;

    @Autowired
    public CommuteService(CommuteRepository commuteRepository) {
        this.commuteRepository = commuteRepository;
    }

    public Integer commute(CommuteDto commuteDto) {
        Commute commute = Commute.builder()
                .startTime(commuteDto.getStartTime())
                .endTime(commuteDto.getEndTime())
                .sumTime(commuteDto.getSumTime())
                .build();

        Commute savedCommute = commuteRepository.save(commute);
        return savedCommute.getId();
    }



//    public CommuteService(CommuteRepository commuteRepository) {
//        this.commuteRepository = commuteRepository;
//    }
//    public CommuteDto commute(Integer id) {
//        Commute commute = commuteRepository.findById(id).orElseThrow(()->new CommuteNotFoundException(""));
//
//        return CommuteDto.builder()
//                .id(commute.getId())
//                .sumTime(commute.getSumTime())
//                .startTime(commute.getStartTime())
//                .endTime(commute.getEndTime())
//                .build();
//    }


}