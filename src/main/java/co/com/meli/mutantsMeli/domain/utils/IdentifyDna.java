package co.com.meli.mutantsMeli.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class IdentifyDna {
    private static final String DNA_SEQUENCES_VALID = "(A{4})|(C{4})|(G{4})|(T{4})";

    private static final List<Character> POSSIBLE_LETTERS = Arrays.asList('A', 'T', 'C', 'G');
    private static final long DNA_SEQUENCES_MIN = 2;

    private static final long DNA_SEQUENCES_MIN_ARRAY = 4;

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    ArrayList<String> sequences = new ArrayList();

    public Boolean checkDna(String[] dna) throws MutantException {

        sequences.clear();

        if (dna.length < DNA_SEQUENCES_MIN_ARRAY)
            throw new MutantException("VMUT03", "La longitud mínima es 4 para la matriz");

        getRows(dna);
        getColumns(dna);
        getDiagonals(dna);

        LOGGER.info("_____________");
        long countDnaMutant = 0;
        countDnaMutant = sequences.stream().filter(s -> {
                            LOGGER.info("s = " + s);
                    try {
                        checkCharacters(s);
                    } catch (MutantException e) {
                        throw new RuntimeException(e);
                    }
                    Matcher matcherDNA = Pattern.compile(DNA_SEQUENCES_VALID).matcher(s);
                            return matcherDNA.find();
                        })
                        .count();

        LOGGER.info("countDnaMutant = " + countDnaMutant);

        return countDnaMutant >= DNA_SEQUENCES_MIN;
    }

    private void getRows(String[] dna) {
        int sizeArray = dna.length;
        Arrays.stream(dna)
                .forEach(s -> {
                    try {
                        checkSizeRow(s,sizeArray);
                    } catch (MutantException e) {
                        throw new RuntimeException(e);
                    }
                    sequences.add(s);
                });
    }

    private void getColumns(String[] dna) {
        for (int i = 0; i < dna.length; i++) {
            StringBuilder builderColumns = new StringBuilder(dna.length);
            for (int j = 0; j < dna.length; j++) {
                builderColumns.append(dna[j].charAt(i));
            }
            sequences.add(builderColumns.toString());
        }
    }

    private void getDiagonals(String[] dna) {
        for (int i = 0; i < dna.length / 2; i++) {
            StringBuilder diagonalsHigher = new StringBuilder(dna.length);
            StringBuilder diagonalsLower = new StringBuilder(dna.length);

            for (int j = 0; j < dna.length - i; j++) {
                diagonalsHigher.append(dna[j].charAt(j + i));
                if (i != 0)
                    diagonalsLower.append(dna[i + j].charAt(j));
            }
            if (diagonalsHigher.length() > 0)
                sequences.add(diagonalsHigher.toString());

            if (diagonalsLower.length() > 0)
                sequences.add(diagonalsLower.toString());
        }
    }

    private void checkCharacters(String subDna) throws MutantException {

        for (int i = 0; i < subDna.length(); i++) {
            if (!POSSIBLE_LETTERS.contains(subDna.charAt(i)))
                throw new MutantException("VMUT01", "Caracter no valido " + subDna.charAt(i));
        }
    }

    private void checkSizeRow(String subDna, int sizeArray) throws MutantException {
        if (sizeArray != subDna.length())
            throw new MutantException("VMUT02", "La matriz de dna no es cuadrada ");
    }
}
