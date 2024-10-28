package br.tec.llam.mediavitae.classification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Class_Class")
public class ClassClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classClassId;

    @NonNull
    private Integer classId1;
    @NonNull
    private Integer classId2;
    @NonNull
    private Integer classId;
}
