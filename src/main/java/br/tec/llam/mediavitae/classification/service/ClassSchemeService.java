package br.tec.llam.mediavitae.classification.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import br.tec.llam.mediavitae.classification.Assembler.ClassSchemeAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassScheme;
import br.tec.llam.mediavitae.classification.exceptions.ClassSchemeNotFoundException;
import br.tec.llam.mediavitae.classification.repository.ClassSchemeRepository;

@Service
public class ClassSchemeService {

	@Autowired
	private ClassSchemeRepository repository;

	@Autowired
	private ClassSchemeAssembler assembler;

	public ClassScheme save(ClassScheme entity) {
		return repository.save(entity);
	}

	public List<ClassScheme> findAll() {
		return repository.findAll();
	}

	public EntityModel<ClassScheme> getClassSchemeById(Integer id) {
		Optional<ClassScheme> classScheme = repository.findById(id);
		if (classScheme.isPresent()) {
			return assembler.toModel(classScheme.get());
		} else {
			throw new ClassSchemeNotFoundException(id);
		}
	}

	public void deleteClassSchemeById(Integer id) {
		Optional<ClassScheme> classScheme = repository.findById(id);
		if (classScheme.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new ClassSchemeNotFoundException(id);
		}
	}

	public EntityModel<ClassScheme> updateClassScheme(ClassScheme newClassScheme) {
		ClassScheme updatedClassScheme = repository.findById(newClassScheme.getClassSchemeId()).map(
				classScheme -> {
					classScheme.setConstant(newClassScheme.getConstant());
					classScheme.setUri(newClassScheme.getUri());
					return repository.save(classScheme);

				}).orElseGet(() -> repository.save(newClassScheme));

		return assembler.toModel(updatedClassScheme);
	}
}
