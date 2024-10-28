package br.tec.llam.mediavitae.classification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.tec.llam.mediavitae.classification.entity.ClassScheme;

@Repository
public interface ClassSchemeRepository extends JpaRepository<ClassScheme, Integer> {
}
