package com.HelloRolha.HR.feature.commute.service;


import com.HelloRolha.HR.feature.commute.model.Commute;
import com.HelloRolha.HR.feature.commute.model.dto.CommuteDto;
import com.HelloRolha.HR.feature.commute.repository.CommuteRepository;
import org.springframework.stereotype.Service;

@Service
public class CommuteService {

    CommuteRepository commuteRepository;

    public CommuteService(CommuteRepository commuteRepository) {
        this.commuteRepository = commuteRepository;
    }
    public CommuteDto commute(Integer id) {
        Commute commute = commuteRepository.findById(id).orElseThrow(()->new CommuteNotFoundException(""));

        return CommuteDto.builder()
                .id(commute.getId())
                .start_time(commute.getStart_time())
                .end_time(commute.getEnd_time())
                .build();
    }

}