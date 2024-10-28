package br.tec.llam.mediavitae.classification.entity.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClassId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classId;

    @NonNull
    private Integer classSchemeId;

    public void convertFields(Integer classId, Integer classSchemeId) {
        this.classId = classId;
        this.classSchemeId = classSchemeId;
    }

}
