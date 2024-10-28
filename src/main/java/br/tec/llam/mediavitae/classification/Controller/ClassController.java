package br.tec.llam.mediavitae.classification.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import br.tec.llam.mediavitae.classification.Assembler.ClassAssembler;
import br.tec.llam.mediavitae.classification.entity.Classification;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassId;
import br.tec.llam.mediavitae.classification.service.ClassService;

@RestController
@RequestMapping(path = "/class")
public class ClassController {

	@Autowired
	private ClassService service;

	@Autowired
	private ClassAssembler assembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody Classification entity) {
		try {
			EntityModel<Classification> saved = service.save(entity);
			return ResponseEntity.ok(saved);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Causa do erro: " + e.getMessage());
		}
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<EntityModel<Classification>> getAll() {
		List<EntityModel<Classification>> entities = service.findAll()
				.stream().map(entity -> assembler.toModel(entity))
				.collect(Collectors.toList());
		return CollectionModel.of(entities, linkTo(methodOn(ClassController.class).getAll()).withSelfRel());
	}

	@GetMapping("/{classId}/{classSchemeId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<Classification>> getClassById(@PathVariable Integer classId, @PathVariable Integer classSchemeId) {
		ClassId embeddedId = new ClassId(classId, classSchemeId);
		Optional<EntityModel<Classification>> foundClass = service.getById(embeddedId);

		if (foundClass.isPresent()) {
			return ResponseEntity.ok(foundClass.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/{classId}/{classSchemeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> delete(@PathVariable Integer classId, @PathVariable Integer classSchemeId) {
		ClassId id = new ClassId(classId, classSchemeId);
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody Classification newClassification) {
		try {
			EntityModel<Classification> updatedClass = service.update(newClassification);
			return ResponseEntity.ok(updatedClass);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Causa do erro: " + e.getMessage());
		}
	}
}
