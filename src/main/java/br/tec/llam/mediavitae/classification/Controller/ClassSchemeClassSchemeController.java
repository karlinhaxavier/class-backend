package br.tec.llam.mediavitae.classification.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.tec.llam.mediavitae.classification.Assembler.ClassSchemeClassSchemeAssembler;
import br.tec.llam.mediavitae.classification.entity.ClassSchemeClassScheme;
import br.tec.llam.mediavitae.classification.service.ClassSchemeClassSchemeService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/classSchemeClassScheme")
public class ClassSchemeClassSchemeController {
	
	@Autowired
	private ClassSchemeClassSchemeService service;
	
	@Autowired
	private ClassSchemeClassSchemeAssembler assembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public ClassSchemeClassScheme save(@RequestBody ClassSchemeClassScheme classSchemeClassScheme) {
        return service.save(classSchemeClassScheme);
    }

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EntityModel<ClassSchemeClassScheme> getClassSchemeClassSchemeId(@PathVariable Integer id) {
		return service.getClassSchemeClassSchemeById(id);
	}

	@DeleteMapping(path = "/{classSchemeClassSchemeId}")  
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void deleteClassSchemeClassSchemeById(@PathVariable Integer classSchemeClassSchemeId) {
		service.deleteClassSchemeClassSchemeById(classSchemeClassSchemeId);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get All ClassSchemeClassScheme")
	public CollectionModel<EntityModel<ClassSchemeClassScheme>> getAll() {
	  List<EntityModel<ClassSchemeClassScheme>> classSchemeClassScheme = service.findAll().stream() //
	      .map(assembler::toModel) //
	      .collect(Collectors.toList());
	  return CollectionModel.of(classSchemeClassScheme, linkTo(methodOn(ClassSchemeClassSchemeController.class).getAll()).withSelfRel());
	}
}




