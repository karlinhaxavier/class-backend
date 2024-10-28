package br.tec.llam.mediavitae.classification.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClassSchemeNameId {

    @NonNull
    private Integer classSchemeId;

    @NonNull
    private Integer languageId;

    public void convertFields(Integer classSchemeId, Integer languageId) {
        this.classSchemeId = classSchemeId;
        this.languageId = languageId;
    }
}
