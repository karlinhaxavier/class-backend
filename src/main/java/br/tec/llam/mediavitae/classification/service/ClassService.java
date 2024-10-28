package br.tec.llam.mediavitae.classification.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import br.tec.llam.mediavitae.classification.Assembler.ClassAssembler;
import br.tec.llam.mediavitae.classification.entity.Classification;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassId;
import br.tec.llam.mediavitae.classification.repository.ClassRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository repository;

    @Autowired
    private ClassAssembler assembler;

    public EntityModel<Classification> save(Classification entity) {
        var createdEntity = repository.save(entity);
        return assembler.toModelForCreate(createdEntity);
    }

    public List<Classification> findAll() {
        return repository.findAll();
    }

    public Optional<EntityModel<Classification>> getById(ClassId id) {
        Optional<Classification> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(assembler.toModelForGetById(entity.get()));
        } else {
            return Optional.empty();
        }
    }

    public void delete(ClassId id) {
        repository.deleteById(id);
    }

    public EntityModel<Classification> update(Classification newClassification) {
        Classification updatedClassification = repository.findById(newClassification.getId())
                .map(foundClassification -> {

                    if (newClassification.getId().getClassSchemeId() != null) {
                        foundClassification.getId().setClassSchemeId(newClassification.getId().getClassSchemeId());
                    }

                    if (newClassification.getConstant() != null) {
                        foundClassification.setConstant(newClassification.getConstant());
                    }

                    if (newClassification.getUri() != null) {
                        foundClassification.setUri(newClassification.getUri());
                    }

                    return repository.save(foundClassification);

                }).orElseGet(() -> repository.save(newClassification));

        return assembler.toModelForCreate(updatedClassification);

    }
}
