package GuiHangAMan;

import KernelHangAMan.MultiChance;
import KernelHangAMan.Propositions;
import KernelHangAMan.Square;
import KernelHangAMan.ZeroChance;

/**
 * Created by Mekhalfa Taki Eddine on 20/04/2017.
 */
public class SquareHandlersFactory {

    public static SquarePane getSquareHandeler(Square square) {

        /* String squareType = square.getClass().getSimpleName();
        switch (squareType) {
            case "ZeroChance":
                return new ZeroSquarePane(square);
            case "MultiChance":
                return new MultiChancesPane(square);
            case "Propositions":
                return new PopositionsSquarePane(square);
        }
        return null;*/
        if (square instanceof Propositions) return new PopositionsSquarePane(square);
        if (square instanceof ZeroChance) return new ZeroSquarePane(square);
        if (square instanceof MultiChance) return new MultiChancesPane(square);
        return null;
    }

}
