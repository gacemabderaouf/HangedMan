package KernelHangAMan;

/**

 */
public class MultiChance extends Square implements Malus {
    private final static int NB_CHANCES = 3;
    private final static int MALUS = 2;
    private int remainingChances = NB_CHANCES;

    public MultiChance(String expectedChar) {
        super(expectedChar, 1);
    }

    @Override
    public State validate() {
        String answer = getEffectiveChar();
        State result;
        if (getExpectedChar().equals(answer)) result = State.SUCCESS;
        else {
            if (remainingChances > 0) remainingChances--;
            if (remainingChances > 0) result = State.RETRY;
            else result = State.FAILURE;

        }
        System.out.println(result);
        return result;
    }

    @Override
    public int calculateMalus() {
        return MALUS * (NB_CHANCES - remainingChances);
    }

    public int getRemainingChances() {
        return remainingChances;
    }
}
