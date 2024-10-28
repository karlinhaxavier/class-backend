package br.tec.llam.mediavitae.classification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.tec.llam.mediavitae.classification.entity.ClassTerm;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassTermId;

public interface ClassTermRepository extends JpaRepository <ClassTerm, ClassTermId>{

}
