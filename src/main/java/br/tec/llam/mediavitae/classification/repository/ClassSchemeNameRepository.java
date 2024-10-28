package br.tec.llam.mediavitae.classification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.tec.llam.mediavitae.classification.entity.ClassSchemeName;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassSchemeNameId;

public interface ClassSchemeNameRepository extends JpaRepository<ClassSchemeName, ClassSchemeNameId> {

}
