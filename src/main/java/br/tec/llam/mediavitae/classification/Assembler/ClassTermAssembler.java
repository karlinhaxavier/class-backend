package br.tec.llam.mediavitae.classification.Assembler;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.tec.llam.mediavitae.classification.Controller.ClassTermController;
import br.tec.llam.mediavitae.classification.entity.ClassTerm;

@Component
public class ClassTermAssembler implements RepresentationModelAssembler<ClassTerm, EntityModel<ClassTerm>> {

    @Override //para usar no GetAll();
    public EntityModel<ClassTerm> toModel(ClassTerm entity) {

        Integer classId = entity.getId().getClassId();
        Integer languageId = entity.getId().getLanguageId();

        Link linkToFindById = linkTo(
            methodOn(ClassTermController.class).getClassTermById(classId, languageId)
        ).withRel("Get ClassTerm By Id");

       return EntityModel.of(entity, linkToFindById);
    }
    
    public EntityModel<ClassTerm> toModelForGetById(ClassTerm entity){  
        Link linkToFindAll = linkTo(
            methodOn(ClassTermController.class).getAll()
        ).withRel("Get all ClassTerm");

    	   return EntityModel.of(entity, linkToFindAll);
    }
    
}

