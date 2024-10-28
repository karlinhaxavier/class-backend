package br.tec.llam.mediavitae.classification.entity;

import br.tec.llam.mediavitae.classification.entity.embeddable.ClassSchemeNameId;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ClassSchemeName")
public class ClassSchemeName {

	@Id
	@EmbeddedId
	private ClassSchemeNameId id;

	@NonNull
	private String name;
}
