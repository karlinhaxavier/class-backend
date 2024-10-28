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

import br.tec.llam.mediavitae.classification.Assembler.ClassSchemeNameAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeName;
import br.tec.llam.mediavitae.classification.entity.embeddable.ClassSchemeNameId;
import br.tec.llam.mediavitae.classification.service.ClassSchemeNameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/classSchemeName")
public class ClassSchemeNameController {

	@Autowired
	private ClassSchemeNameService service;

	@Autowired
	private ClassSchemeNameAssembler assembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClassSchemeName addNewClassSchemeName(@RequestBody ClassSchemeName classSchemeName) {
		return service.save(classSchemeName);
	}

	@GetMapping("/{classSchemeNameId}/{languageId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassSchemeName>> getClassSchemeNameById(@PathVariable Integer classSchemeNameId,
			@PathVariable Integer languageId) {
		ClassSchemeNameId id = new ClassSchemeNameId(classSchemeNameId, languageId);
		return ResponseEntity.of(Optional.ofNullable(service.getClassSchemeById(id)));
	}

	@DeleteMapping(path = "/{classSchemeNameId}/{languageId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClassSchemeNameById(@PathVariable Integer classSchemeNameId, @PathVariable Integer languageId) {
		ClassSchemeNameId id = new ClassSchemeNameId(classSchemeNameId, languageId);
		service.deleteClassSchemeNameById(id);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassSchemeName>> replaceClassSchemeName(
			@RequestBody ClassSchemeName newClassSchemeName) {
		EntityModel<ClassSchemeName> updatedClassSchemeName = service.updateClassSchemeName(newClassSchemeName);
		return ResponseEntity.ok(updatedClassSchemeName);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get All ClassSchemeName")
	public CollectionModel<EntityModel<ClassSchemeName>> getAll() {
		List<EntityModel<ClassSchemeName>> languages = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());
		return CollectionModel.of(languages, linkTo(methodOn(ClassSchemeNameController.class).getAll()).withSelfRel());
	}
}
