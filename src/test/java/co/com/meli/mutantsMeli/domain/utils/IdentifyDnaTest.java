package co.com.meli.mutantsMeli.domain.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IdentifyDnaTest {

    private final IdentifyDna identifyDna = new IdentifyDna();

    @Test
    void checkDnaNotSizeMinimum(){
        Assertions.assertThrows(MutantException.class, () -> identifyDna.checkDna(new String[]{"ATG", "CTG", "TTA"}));
    }

    @Test
    void checkDnaInvalidCharacters(){
        Assertions.assertThrows(MutantException.class, () -> identifyDna.checkDna(new String[]{"ATGH", "CTGJ", "TTAN"}));
    }

    @Test
    void checkDnaInvalidSizeRowV1(){
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> identifyDna.checkDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTGT"}));
        MutantException mutantException = (MutantException) thrown.getCause();
        Assertions.assertEquals("La matriz de dna no es cuadrada ", mutantException.getDescription());
    }

    @Test
    void checkDnaInvalidSizeRowV2(){
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> identifyDna.checkDna(new String[]{"ATGCGAT","CAGTGCC","TTATGTT","AGAAGGC","CCCCTAG","TCACTGT"}));
        MutantException mutantException = (MutantException) thrown.getCause();
        Assertions.assertEquals("La matriz de dna no es cuadrada ", mutantException.getDescription());
    }

    @Test
    void checkDnaInvalidSizeRowV3(){
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> identifyDna.checkDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTGT","TCACTGT"}));
        MutantException mutantException = (MutantException) thrown.getCause();
        Assertions.assertEquals("La matriz de dna no es cuadrada ", mutantException.getDescription());
    }

    @Test
    void checkDnaIsMutant() throws MutantException {
        Assertions.assertTrue(identifyDna.checkDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}));
    }

    @Test
    void checkDnaIsNotMutant() throws MutantException {
        Assertions.assertFalse(identifyDna.checkDna(new String[]{"ATGCGA","CTGTGC","TTATGT","AGAAGG","CCTCTA","TCACTG"}));
    }
}