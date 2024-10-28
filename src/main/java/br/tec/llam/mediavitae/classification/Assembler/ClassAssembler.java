package br.tec.llam.mediavitae.classification.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.CollectionModel;

import br.tec.llam.mediavitae.classification.Controller.ClassController;
import br.tec.llam.mediavitae.classification.entity.Classification;

@Component
public class ClassAssembler implements RepresentationModelAssembler<Classification, EntityModel<Classification>> {

        public EntityModel<Classification> toModelForGetById(Classification entity) {
                Link linkToGetAll = linkTo(methodOn(ClassController.class).getAll()).withRel("Get all Class");
                return EntityModel.of(entity, linkToGetAll);
        }

        public EntityModel<Classification> toModelForCreate(Classification entity) {
                Integer classId = entity.getId().getClassId();
                Integer classSchemeId = entity.getId().getClassSchemeId();

                Link linkToGetById = linkTo(methodOn(ClassController.class).getClassById(classId, classSchemeId))
                                .withRel("Get Class by ID");
                return EntityModel.of(entity, linkToGetById);
        }

        @Override // para usar no GetAll()
        public EntityModel<Classification> toModel(Classification entity) {
                Integer classId = entity.getId().getClassId();
                Integer classSchemeId = entity.getId().getClassSchemeId();

                Link linkToFindById = linkTo(methodOn(ClassController.class).getClassById(classId, classSchemeId))
                        .withRel("Get Class by ID");
                return EntityModel.of(entity, linkToFindById);
        }

        @Override
        public CollectionModel<EntityModel<Classification>> toCollectionModel(
                        Iterable<? extends Classification> entities) {
                return RepresentationModelAssembler.super.toCollectionModel(entities);
        }
}
