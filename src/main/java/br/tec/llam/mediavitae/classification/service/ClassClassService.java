package br.tec.llam.mediavitae.classification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import br.tec.llam.mediavitae.classification.Assembler.ClassClassAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassClass;
import br.tec.llam.mediavitae.classification.exceptions.ClassClassNotFoundException;
import br.tec.llam.mediavitae.classification.repository.ClassClassRepository;

@Service
public class ClassClassService {

    @Autowired
    private ClassClassAssembler assembler;
    @Autowired
    private ClassClassRepository repository;

    public ClassClass save(ClassClass entity) {
        return repository.save(entity);
    }

    public List<ClassClass> findAll() {
        return repository.findAll();
    }

    public EntityModel<ClassClass> getClassClassById(Integer classClassId) {
        Optional<ClassClass> classClass = repository.findById(classClassId);
        if (classClass.isPresent()) {
            return assembler.toModelForGetById(classClass.get());
        } else {
            throw new ClassClassNotFoundException(classClassId);
        }
    }

    public void deleteClassClassById(Integer classClassId) {
        Optional<ClassClass> classClass = repository.findById(classClassId);
        if (classClass.isPresent()) {
            repository.deleteById(classClassId);
        } else {
            throw new ClassClassNotFoundException(classClassId);
        }
    }

    public EntityModel<ClassClass> updateClassClass(ClassClass newClassClass) {
        ClassClass updatedClassClass = repository.findById(newClassClass.getClassClassId())
                .map(classClass -> {
                    classClass.setClassId(newClassClass.getClassId());
                    classClass.setClassId1(newClassClass.getClassId1());
                    classClass.setClassId2(newClassClass.getClassId2());
                    return repository.save(classClass);
                })
                .orElseGet(() -> repository.save(newClassClass));

        return assembler.toModel(updatedClassClass);
    }
}
