package br.tec.llam.mediavitae.classification.exceptions;

import br.tec.llam.mediavitae.classification.entity.embeddable.ClassSchemeNameId;

public class ClassSchemeNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClassSchemeNameNotFoundException(ClassSchemeNameId id) {
		super("Could not find ClassSchemeName with ID " + id.toString());
	}
}
