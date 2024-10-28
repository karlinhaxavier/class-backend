package br.tec.llam.mediavitae.classification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeClassScheme;

@Repository
public interface ClassSchemeClassSchemeRepository extends JpaRepository<ClassSchemeClassScheme, Integer> {
}


