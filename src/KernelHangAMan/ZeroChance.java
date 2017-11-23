package KernelHangAMan;

/**
 * Created by Mekhalfa Taki Eddine on 20/04/2017.
 */
public class ZeroChance extends Square {

    public ZeroChance(String expectedChar, int SCORE) {
        super(expectedChar, SCORE);
    }

    public ZeroChance(String expectedChar) {
        super(expectedChar, 3);
    }

    @Override
    public State validate() {
        String answer = getEffectiveChar();
       if (getExpectedChar().equals(answer)) return State.SUCCESS;
       else return State.FAILURE;
    }
}
