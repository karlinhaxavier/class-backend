package br.tec.llam.mediavitae.classification.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClassTermId {

    @NonNull
    private Integer classId;

    @NonNull
    private Integer languageId;

    public void convertFields(Integer classId, Integer languageId) {
    	this.classId = classId;
    	this.languageId = languageId;
    }
}
