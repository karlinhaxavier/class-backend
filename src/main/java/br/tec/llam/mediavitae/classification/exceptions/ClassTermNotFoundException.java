package br.tec.llam.mediavitae.classification.exceptions;

import br.tec.llam.mediavitae.classification.entity.embeddable.ClassTermId;

public class ClassTermNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClassTermNotFoundException(ClassTermId id) {
        super("Could not find ClassTerm " + id.toString());
    }
}
