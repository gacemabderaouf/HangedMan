package KernelHangAMan;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private final int MAX_ZEROEtP_SQUARES = 3;
    private final int MIN_SQUARES = 6;

    private static final int DEFINITION = 3;
    private static final int SYNONYME = 2;
    private static final int ANTONYME = 1;

    private HintType hintType;
    private String hint;


    private boolean malus;

    private String expectedWord;

    private List<Square> squares;


    public Word(String expectedWord, HintType hintType, String hint) {
        this.expectedWord = expectedWord.toUpperCase();
        this.hintType = hintType;
        this.hint = hint;
    }

    public void generateSquares() {
        int nbrZeroEtPSquares = 0;
        int nbrMultiPropoSquares = 0;

        squares = new ArrayList<>(expectedWord.length());
        String[] letters = expectedWord.split("");

        for (int i = 0; i < expectedWord.length(); i++) {
            int rand;
            if (nbrZeroEtPSquares < MAX_ZEROEtP_SQUARES) rand = (int) (Math.random() * 10) % 3;
            else rand = 0;
            if (rand == 0) {
                squares.add(new MultiChance(letters[i]));
                nbrMultiPropoSquares++;
            } else if (rand == 1) {
                squares.add(new Propositions(letters[i]));
                nbrZeroEtPSquares++;
                nbrMultiPropoSquares++;
            } else {
                squares.add(new ZeroChance(letters[i]));
                nbrZeroEtPSquares++;
            }
        }

        if (nbrMultiPropoSquares > 5) malus = true;

    }

    public int getScore() {
        int finalResult = 0;
        finalResult = squares.stream()
                .mapToInt(Square::getScore)
                .sum();
        switch (hintType) {
            case DEFINITION:
                finalResult = DEFINITION * finalResult;
                break;
            case SYNONYME:
                finalResult = SYNONYME * finalResult;
                break;
            case ANTONYME:
                finalResult = ANTONYME * finalResult;
        }

        if (malus) {
            finalResult -= squares.stream()
                    .filter(Square::isMalus)
                    .map(e -> (Malus) e)
                    .mapToInt(Malus::calculateMalus)
                    .sum();
        }

        return finalResult;
    }

    public HintType getHintType() {
        return hintType;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public boolean isMalus() {
        return malus;
    }

    public String getExpectedWord() {
        return expectedWord;
    }

    public String getHint() {
        return hint;
    }
}
