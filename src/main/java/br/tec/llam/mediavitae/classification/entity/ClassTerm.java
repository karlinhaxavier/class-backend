package br.tec.llam.mediavitae.classification.entity;

import br.tec.llam.mediavitae.classification.entity.embeddable.ClassTermId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ClassTerm")
public class ClassTerm {
    @Id
    @EmbeddedId
    private ClassTermId id;

    @NonNull
    private String term;
    @NonNull
    private String termSrc;
}




