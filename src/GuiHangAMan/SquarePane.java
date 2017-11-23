package GuiHangAMan;

import KernelHangAMan.Square;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class SquarePane extends TextField implements EventHandler<KeyEvent> {
    protected Square square;
    WordPane wordHnadler;

    public SquarePane(Square square) {
        this.square = square;
        setOnKeyTyped(this);
        setAlignment(Pos.CENTER);
    }

    public void setWordHandler(WordPane wordPane) {
        this.wordHnadler = wordPane;
    }

    @Override
    public void handle(KeyEvent event) {
        event.consume();
        String answer = event.getCharacter().toUpperCase();
        if (Character.isAlphabetic(answer.charAt(0))) {
            if (isEditable()) validate(answer);
        }
    }

    public Square getSquare() {
        return square;
    }

    public abstract void validate(String answer);

    public void focusNext() {
        KeyEvent tab = new KeyEvent(this, this, KeyEvent.KEY_PRESSED, "", "", KeyCode.TAB, false, false, false, false);
        fireEvent(tab);
    }
}
