package br.tec.llam.mediavitae.classification.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
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

import br.tec.llam.mediavitae.classification.Assembler.ClassClassAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassClass;
import br.tec.llam.mediavitae.classification.service.ClassClassService;

@RestController
@RequestMapping(path = "/classClass")
public class ClassClassController {

	@Autowired
	private ClassClassService service;

	@Autowired
	private ClassClassAssembler assembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClassClass save(@RequestBody ClassClass classClass) {
		return service.save(classClass);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<EntityModel<ClassClass>> findAll() {
		List<EntityModel<ClassClass>> entities = service.findAll()
				.stream().map(
						entity -> assembler.toModel(entity))
				.collect(Collectors.toList());
		return CollectionModel.of(entities, linkTo(methodOn(ClassClassController.class)).withSelfRel());
	}

	@GetMapping("/{classClassId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassClass>> getClassClassById(
			@PathVariable Integer classClassId) {

		var entityModel = service.getClassClassById(classClassId);
		var entity = Optional.of(entityModel);
		return ResponseEntity.of(entity);
	}

	@DeleteMapping(path = "/{classClassId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClassClassById(@PathVariable Integer classClassId) {
		service.deleteClassClassById(classClassId);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassClass>> replaceClassClass(@RequestBody ClassClass newClassClass) {
		EntityModel<ClassClass> updatedClassClass = service.updateClassClass(newClassClass);
		return ResponseEntity.ok(updatedClassClass);
	}
}
