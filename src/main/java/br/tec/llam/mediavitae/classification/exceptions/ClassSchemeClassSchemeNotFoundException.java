package br.tec.llam.mediavitae.classification.exceptions;

public class ClassSchemeClassSchemeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClassSchemeClassSchemeNotFoundException(int id) {
        super("Could not find ClassSchemeClassScheme " + id);
    }
}


