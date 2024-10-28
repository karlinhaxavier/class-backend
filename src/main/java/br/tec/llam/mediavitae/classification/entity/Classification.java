package br.tec.llam.mediavitae.classification.entity;

import br.tec.llam.mediavitae.classification.entity.embeddable.ClassId;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Class")
public class Classification {

    @Id
    @EmbeddedId
    private ClassId id;

    @NonNull
    private String constant;

    @NonNull
    private Integer uri;
}








