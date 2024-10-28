package br.tec.llam.mediavitae.classification.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.tec.llam.mediavitae.classification.Controller.ClassSchemeController;
import br.tec.llam.mediavitae.classification.entity.ClassScheme;

@Component
public class ClassSchemeAssembler implements RepresentationModelAssembler<ClassScheme, EntityModel<ClassScheme>> {

    @Override
    public EntityModel<ClassScheme> toModel(ClassScheme entity) {
        Link linkToFindById = linkTo(
                methodOn(ClassSchemeController.class).getClassSchemeById(entity.getClassSchemeId())).withSelfRel();

        return EntityModel.of(entity, linkToFindById);
    }

    public EntityModel<ClassScheme> toModelForGetById(ClassScheme entity) {
        Link linkToFindAll = linkTo(methodOn(ClassSchemeController.class).findAll()).withRel("Get all ClassScheme");

        return EntityModel.of(entity, linkToFindAll);
    }
}
