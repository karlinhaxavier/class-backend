package br.tec.llam.mediavitae.classification.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.tec.llam.mediavitae.classification.Assembler.ClassTermAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassTerm;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassTermId;
import br.tec.llam.mediavitae.classification.service.ClassTermService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/classTerm")
public class ClassTermController {

	@Autowired
	private ClassTermService service;

	@Autowired
	private ClassTermAssembler assembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public ClassTerm save(@RequestBody ClassTerm classTerm) {
        return service.save(classTerm);
    }

	@GetMapping ("/{classId}/{languageId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassTerm>> getClassTermById(@PathVariable Integer classId, @PathVariable Integer languageId) {
		ClassTermId id = new ClassTermId(classId, languageId);
	 	EntityModel<ClassTerm> entityModel = service.getClassTermById(id);
		return ResponseEntity.ok(entityModel);
	}

	@DeleteMapping(path = "/{classId}/{languageId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClassTermById(@PathVariable Integer classId, @PathVariable Integer languageId) {
		ClassTermId id = new ClassTermId(classId, languageId);
		service.deleteClassTermById(id);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel <ClassTerm>> updateClassTerm(@RequestBody ClassTerm newClassTerm ) {
		EntityModel<ClassTerm> updatedClassTerm = service.updateClassTerm(newClassTerm);
		return ResponseEntity.ok(updatedClassTerm);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get All ClassTerm")
	public CollectionModel<EntityModel<ClassTerm>> getAll() {
	  List<EntityModel<ClassTerm>> classTerms = service.findAll().stream() //
	      .map(assembler::toModel) //
	      .collect(Collectors.toList());
	  return CollectionModel.of(classTerms, linkTo(methodOn(ClassTermController.class).getAll()).withSelfRel());
	}
}
