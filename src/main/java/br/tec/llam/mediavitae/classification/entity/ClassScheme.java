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
@Table(name = "ClassScheme")
public class ClassScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classSchemeId;

    @NonNull
    private String constant;

    @NonNull
    private Integer uri;

}
