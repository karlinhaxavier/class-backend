package br.tec.llam.mediavitae.classification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import br.tec.llam.mediavitae.classification.Assembler.ClassSchemeNameAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeName;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassSchemeNameId;
import br.tec.llam.mediavitae.classification.exceptions.ClassSchemeNameNotFoundException;
import br.tec.llam.mediavitae.classification.repository.ClassSchemeNameRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassSchemeNameService {

    @Autowired
    private ClassSchemeNameAssembler assembler;

    @Autowired
    private ClassSchemeNameRepository repository;

    public ClassSchemeName save(ClassSchemeName entity) {
        return repository.save(entity);
    }

    public List<ClassSchemeName> findAll() {
        return repository.findAll();
    }

    public EntityModel<ClassSchemeName> getClassSchemeById(ClassSchemeNameId classSchemeNameId) {
        Optional<ClassSchemeName> classSchemeName = repository.findById(classSchemeNameId);
        if (classSchemeName.isPresent()) {
            return assembler.toModel(classSchemeName.get());
        } else {
            throw new ClassSchemeNameNotFoundException(classSchemeNameId);
        }
    }

    public void deleteClassSchemeNameById(ClassSchemeNameId classSchemeNameId) {
        Optional<ClassSchemeName> classSchemeName = repository.findById(classSchemeNameId);
        if (classSchemeName.isPresent()) {
            repository.deleteById(classSchemeNameId);
        } else {
            throw new ClassSchemeNameNotFoundException(classSchemeNameId);
        }
    }

    public EntityModel<ClassSchemeName> updateClassSchemeName(ClassSchemeName newClassSchemeName) {
        ClassSchemeName updatedClassSchemeName = repository.findById(newClassSchemeName.getId())
                .map(classSchemeName -> {
                    if (newClassSchemeName.getId().getLanguageId() != null) {
                        classSchemeName.getId().setLanguageId(newClassSchemeName.getId().getLanguageId());
                    }

                    if (newClassSchemeName.getName() != null) {
                        classSchemeName.setName(newClassSchemeName.getName());
                    }

                    return repository.save(classSchemeName);
                })
                .orElseGet(() -> repository.save(newClassSchemeName));

        return assembler.toModel(updatedClassSchemeName);

    }
}
