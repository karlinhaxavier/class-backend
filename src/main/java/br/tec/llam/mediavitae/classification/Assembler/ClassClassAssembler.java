package br.tec.llam.mediavitae.classification.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.tec.llam.mediavitae.classification.Controller.ClassClassController;
import br.tec.llam.mediavitae.classification.entity.ClassClass;

@Component
public class ClassClassAssembler implements RepresentationModelAssembler<ClassClass, EntityModel<ClassClass>> {

    @Override // para usar no GetAll()
    public EntityModel<ClassClass> toModel(ClassClass classClass) {
        Link linkToFindById = linkTo(
                methodOn(ClassClassController.class).getClassClassById(classClass.getClassClassId()))
                .withRel("Get ClassClass by id");

        return EntityModel.of(classClass, linkToFindById);
    }

    public EntityModel<ClassClass> toModelForGetById(ClassClass entity) {
        Link linkToFindAll = linkTo(
                methodOn(ClassClassController.class).findAll()).withRel("Get all ClassClass");

        return EntityModel.of(entity, linkToFindAll);
    }

}
