package KernelHangAMan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mekhalfa Taki Eddine on 20/04/2017.
 */
public class Propositions extends ZeroChance implements Malus {
    private final static int SCORE = 1;
    private final static int NB_PROPOSITIONS = 4;
    private int malus = 0;
    private List<String> propositions;

    public Propositions(String expectedChar) {
        super(expectedChar, 2);
        generatePropsitions();
    }


    private void generatePropsitions() {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        propositions = new ArrayList<>(NB_PROPOSITIONS);
        int i = 0;


        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);

        for (String letter : letters) {
            propositions.add(letter);
            i++;
            if (i == NB_PROPOSITIONS) break;
        }
        if (!propositions.contains(getExpectedChar())) {
            propositions.remove(0);
            propositions.add(0,getExpectedChar());
        }
    }

    @Override
    public int calculateMalus() {
        return malus;
    }


    @Override
    public State validate() {
        State result = super.validate();
        if (result.equals(State.FAILURE)) {
            if (malus == 0) malus = 1;
        }
        return result;
    }

    public List<String> getPropositions() {
        return propositions;
    }
}
