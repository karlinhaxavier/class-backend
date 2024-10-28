package br.tec.llam.mediavitae.classification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import br.tec.llam.mediavitae.classification.exceptions.ClassTermNotFoundException;
import br.tec.llam.mediavitae.classification.Assembler.ClassTermAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassTerm;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassTermId;
import br.tec.llam.mediavitae.classification.repository.ClassTermRepository;

@Service
public class ClassTermService {
	
    @Autowired
	private ClassTermAssembler assembler;

	@Autowired
	private ClassTermRepository repository;
    
    public ClassTerm save(ClassTerm entity) {
    	return repository.save(entity);
    }

    public List<ClassTerm> findAll() {
        return repository.findAll();
    }

    public EntityModel<ClassTerm> getClassTermById(ClassTermId classTermId) {
        Optional<ClassTerm> classTerm = repository.findById(classTermId);
        if(classTerm.isPresent()){
            return assembler.toModelForGetById(classTerm.get());
        } else {
            throw new ClassTermNotFoundException(classTermId);
        }
    }

    public void deleteClassTermById(ClassTermId classTermId) {
        Optional<ClassTerm> classTerm = repository.findById(classTermId);
        if(classTerm.isPresent()){
        	repository.deleteById(classTermId);
        } else {
            throw new ClassTermNotFoundException(classTermId);
        }
    }
    
	    public EntityModel<ClassTerm> updateClassTerm(ClassTerm newClassTerm) {
	        ClassTerm updatedClassTerm = repository.findById(newClassTerm.getId())
	                .map(classTerm -> {
                        
                        if(newClassTerm.getTerm() != null){
    	             	   classTerm.setTerm(newClassTerm.getTerm());
                        }
                        if(newClassTerm.getTermSrc() != null){
                            classTerm.setTermSrc(newClassTerm.getTermSrc());
                        }

                        if(newClassTerm.getId().getLanguageId() != null){
                            classTerm.getId().setLanguageId(newClassTerm.getId().getLanguageId());
                        }

	                    return repository.save(classTerm);
	                }) 
	                .orElseGet(() -> {
	                    return repository.save(newClassTerm);
	                });

	        return assembler.toModel(updatedClassTerm);
	     }
    }
