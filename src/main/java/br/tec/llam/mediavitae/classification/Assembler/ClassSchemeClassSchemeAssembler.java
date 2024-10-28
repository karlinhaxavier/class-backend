package br.tec.llam.mediavitae.classification.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.tec.llam.mediavitae.classification.Controller.ClassSchemeClassSchemeController;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeClassScheme;

@Component
public class ClassSchemeClassSchemeAssembler implements RepresentationModelAssembler<ClassSchemeClassScheme, EntityModel<ClassSchemeClassScheme>> {


    @Override // para usar no GetAll()
    public EntityModel<ClassSchemeClassScheme> toModel(ClassSchemeClassScheme entity) {
    	Link linkToFindById = linkTo(
    	 methodOn(ClassSchemeClassSchemeController.class).getClassSchemeClassSchemeId(entity.getClassId())
    	 ).withSelfRel();

       return EntityModel.of(entity,linkToFindById);
    }

    public EntityModel<ClassSchemeClassScheme> toModelForGetById(ClassSchemeClassScheme entity){
        Link linkToFindAll = linkTo(
            methodOn(ClassSchemeClassSchemeController.class).getAll()
        ).withRel("Get all ClassSchemeClassScheme");

        return EntityModel.of(entity, linkToFindAll);
    }
}

