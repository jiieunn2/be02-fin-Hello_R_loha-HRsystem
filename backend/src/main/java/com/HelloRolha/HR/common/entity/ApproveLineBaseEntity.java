package com.HelloRolha.HR.common.entity;

import com.HelloRolha.HR.feature.employee.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApproveLineBaseEntity extends BaseEntity {
//    private Employee confirmer1Id;
//    private Employee confirmer2Id;
    private String comment;
    private LocalDateTime approveTime;
    private LocalDateTime applyTime;
    private String status;
}
