package br.tec.llam.mediavitae.classification.exceptions;

public class ClassClassNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClassClassNotFoundException(int id) {
        super("Could not find ClassClass " + id);
    }
}
