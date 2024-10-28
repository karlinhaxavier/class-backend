package br.tec.llam.mediavitae.classification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import br.tec.llam.mediavitae.classification.Assembler.ClassSchemeClassSchemeAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeClassScheme;
import br.tec.llam.mediavitae.classification.exceptions.ClassSchemeClassSchemeNotFoundException;
import br.tec.llam.mediavitae.classification.repository.ClassSchemeClassSchemeRepository;
import lombok.AllArgsConstructor;

@Service
public class ClassSchemeClassSchemeService {

	@Autowired
    private  ClassSchemeClassSchemeAssembler assembler;
	@Autowired
    private  ClassSchemeClassSchemeRepository repository;

    public ClassSchemeClassScheme save(ClassSchemeClassScheme entity) {
        return repository.save(entity);
    }

    public List<ClassSchemeClassScheme> findAll() {
        return repository.findAll();
    }

    public EntityModel<ClassSchemeClassScheme> getClassSchemeClassSchemeById(Integer classSchemeClassSchemeId) {
        Optional<ClassSchemeClassScheme> classSchemeClassScheme = repository.findById(classSchemeClassSchemeId);
        if(classSchemeClassScheme.isPresent()){
            return assembler.toModelForGetById(classSchemeClassScheme.get());
        } else {
            throw new ClassSchemeClassSchemeNotFoundException(classSchemeClassSchemeId);
        }
    }


    public void deleteClassSchemeClassSchemeById(Integer classSchemeClassSchemeId) {
        Optional<ClassSchemeClassScheme> classSchemeClassScheme = repository.findById(classSchemeClassSchemeId);
        if(classSchemeClassScheme.isPresent()){
        	repository.deleteById(classSchemeClassSchemeId);
        } else {
            throw new ClassSchemeClassSchemeNotFoundException(classSchemeClassSchemeId);
        }
    }

    public EntityModel<ClassSchemeClassScheme> updateClassSchemeClassScheme(ClassSchemeClassScheme newClassSchemeClassScheme) {
    	ClassSchemeClassScheme updatedClassSchemeClassScheme = repository.findById(newClassSchemeClassScheme.getClassSchemeClassSchemeId())
                .map(classSchemeClassScheme -> {
                	classSchemeClassScheme.setClassId(newClassSchemeClassScheme.getClassId());
                	classSchemeClassScheme.setClassSchemeId1(newClassSchemeClassScheme.getClassSchemeId1());
                	classSchemeClassScheme.setClassSchemeId2(newClassSchemeClassScheme.getClassSchemeId2());
                    return repository.save(classSchemeClassScheme);
                })
                .orElseGet(() -> {
                    return repository.save(newClassSchemeClassScheme);
                });
                
        return assembler.toModel(updatedClassSchemeClassScheme);

    }
}



