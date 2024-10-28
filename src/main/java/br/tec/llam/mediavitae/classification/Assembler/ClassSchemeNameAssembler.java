package br.tec.llam.mediavitae.classification.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.tec.llam.mediavitae.classification.Controller.ClassSchemeNameController;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeName;

@Component
public class ClassSchemeNameAssembler
        implements RepresentationModelAssembler<ClassSchemeName, EntityModel<ClassSchemeName>> {

    @Override // para usar no GetAll();
    public EntityModel<ClassSchemeName> toModel(ClassSchemeName entity) {
        Integer classSchemeId = entity.getId().getClassSchemeId();
        Integer languageId = entity.getId().getLanguageId();

        Link linkToGetById = linkTo(
                methodOn(ClassSchemeNameController.class).getClassSchemeNameById(classSchemeId, languageId))
                .withRel("Get ClassSchemeName By ID");

        return EntityModel.of(entity, linkToGetById);
    }

    public EntityModel<ClassSchemeName> toModelForGetById(ClassSchemeName entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClassSchemeNameController.class).getAll()).withRel("Get all ClassSchemeName"));
    }

}
