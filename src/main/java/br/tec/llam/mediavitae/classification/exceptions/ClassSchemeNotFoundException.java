package br.tec.llam.mediavitae.classification.exceptions;

public class ClassSchemeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClassSchemeNotFoundException(int id) {
        super("Could not find ClassScheme " + id);
    }
}
