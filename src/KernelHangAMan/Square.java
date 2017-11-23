package KernelHangAMan;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mekhalfa Taki Eddine on 17/04/2017.
 */

public abstract class Square {
    private String expectedChar;
    private final int SCORE;
    private String effectiveChar;

    public Square(String expectedChar, int SCORE) {
        this.expectedChar = expectedChar;
        this.SCORE = SCORE;
        this.effectiveChar = null;
    }

    public String getExpectedChar() {
        return expectedChar;
    }

    public boolean isMalus() {
        return this instanceof Malus;
    }

    public abstract State validate();

    public int getScore() {
        if (effectiveChar == null || !effectiveChar.equals(expectedChar)) return 0;
        return SCORE;
    }

    public void setEffectiveChar(String effectiveChar) {
        this.effectiveChar = effectiveChar;
    }

    public String getEffectiveChar() {
        return effectiveChar;
    }
}

