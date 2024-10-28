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

import br.tec.llam.mediavitae.classification.Assembler.ClassSchemeAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassScheme;
import br.tec.llam.mediavitae.classification.service.ClassSchemeService;

@RestController
@RequestMapping(path = "/classScheme")
public class ClassSchemeController {

	@Autowired
	private ClassSchemeService classSchemeService;

	@Autowired
	private ClassSchemeAssembler assembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClassScheme addNewClassScheme(@RequestBody ClassScheme classScheme) {
		return classSchemeService.save(classScheme);
	}

	@GetMapping("/{classSchemeId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassScheme>> getClassSchemeById(@PathVariable Integer classSchemeId) {
		return ResponseEntity.of(Optional.ofNullable(classSchemeService.getClassSchemeById(classSchemeId)));
	}

	@DeleteMapping(path = "/{classSchemeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClassSchemeById(@PathVariable Integer classSchemeId) {
		classSchemeService.deleteClassSchemeById(classSchemeId);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EntityModel<ClassScheme>> replaceClassScheme(@RequestBody ClassScheme newClassScheme) {
		EntityModel<ClassScheme> updatedClassScheme = classSchemeService.updateClassScheme(newClassScheme);
		return ResponseEntity.ok(updatedClassScheme);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<EntityModel<ClassScheme>> findAll() {
		List<EntityModel<ClassScheme>> entities = classSchemeService.findAll().stream().map(
				entity -> assembler.toModel(entity)).collect(Collectors.toList());

		return CollectionModel.of(entities, linkTo(methodOn(ClassSchemeController.class).findAll()).withSelfRel());
	}

}
