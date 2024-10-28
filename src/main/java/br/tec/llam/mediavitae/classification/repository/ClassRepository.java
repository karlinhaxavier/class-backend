package br.tec.llam.mediavitae.classification.repository;

import br.tec.llam.mediavitae.classification.entity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import br.tec.llam.mediavitae.classification.entity.embeddable.ClassId;

public interface ClassRepository extends JpaRepository<Classification, ClassId> {

}
