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
@Table(name = "ClassScheme_ClassScheme")
public class ClassSchemeClassScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classSchemeClassSchemeId;
    @NonNull
    private Integer classSchemeId1;
    @NonNull
    private Integer classSchemeId2;
    @NonNull
    private Integer  classId;
}






