package Domain;

/**
 * Created by yhoupert on 22/01/15.
 */
public class Trait {

    boolean isDraw;


    public Trait(boolean isDraw) {
        this.isDraw = isDraw;
    }

    @Override
    public String toString() {
        return "Trait{" +
                "isDraw=" + isDraw +
                '}';
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }
}
