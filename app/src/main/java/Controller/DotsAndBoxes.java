package Controller;

import android.app.ActivityOptions;

import Domain.Action;
import Domain.Trait;

public class DotsAndBoxes {

    private final int MS_ONE_SEC = 1000;
    private long gameTime = 10 * MS_ONE_SEC;
    private int soundVolume= 4;

    private final int nbOddsSquare = 4;
    private final int nbSquare = 4;

    Trait[][] plateau = new Trait[nbSquare][nbOddsSquare];

    private boolean player = false;

    private void drawTrait(Action action, int nbPlateau){
        plateau[nbPlateau][action.ordinal()].setDraw(true);
    }

    public void changeTurn(){
        gameTime = 10 * MS_ONE_SEC;
        soundVolume = 4;
    }

    public void changePlayer(){
        if(player){
            player = false;
        }else{
            player = true;
        }
    }

    public void initLogic() {
        plateau[0][0]=new Trait(false);
        plateau[0][1]=new Trait(false);
        plateau[1][0]=new Trait(false);
        plateau[1][2]=new Trait(false);
        plateau[2][1]=new Trait(false);
        plateau[2][3]=new Trait(false);
        plateau[3][2]=new Trait(false);
        plateau[3][3]=new Trait(false);

        Trait  tH = new Trait(false);
        Trait  tB = new Trait(false);
        Trait  tD = new Trait(false);
        Trait  tG = new Trait(false);

        plateau[0][2]= tH;
        plateau[1][1]= tH;
        plateau[0][3]= tG;
        plateau[2][0]= tG;
        plateau[1][3]= tD;
        plateau[3][0]= tD;
        plateau[2][2]= tB;
        plateau[3][1]= tB;

    }

    public void detectCompleteSquare(int Square, Action action){
        for(int i=0; i<3; i++){

        }
    }

    public boolean addTrait(Action action, int point){
        if(Action.EMPTY == action)
            return false;

        switch (point) {
            case 1:
                if (action == Action.UP || action == Action.LEFT)
                    return false;
                if (action == Action.DOWN) {
                    if (plateau[0][1].isDraw())
                        return false;
                    else {
                        plateau[0][1].setDraw(true);
                        return true;
                    }
                }
                if (action == Action.RIGHT){
                    if (plateau[0][0].isDraw())
                        return false;
                    else {
                        plateau[0][0].setDraw(true);
                        return true;
                    }
                }
                break;
            case 2:
                if(action == Action.UP)
                    return false;
                if(action == Action.LEFT){
                    if (plateau[0][0].isDraw())
                        return false;
                    else {
                        plateau[0][0].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[1][0].isDraw())
                        return false;
                    else {
                        plateau[1][0].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[0][2].isDraw())
                        return false;
                    else {
                        plateau[0][2].setDraw(true);
                        return true;
                    }
                }
                break;
            case 3:
                if (action == Action.UP || action == Action.RIGHT)
                    return false;
                if (action == Action.DOWN) {
                    if (plateau[1][2].isDraw())
                        return false;
                    else {
                        plateau[1][2].setDraw(true);
                        return true;
                    }
                }
                if (action == Action.LEFT){
                    if (plateau[1][0].isDraw())
                        return false;
                    else {
                        plateau[1][0].setDraw(true);
                        return true;
                    }
                }
                break;
            case 4:
                if(action == Action.LEFT)
                    return false;
                if(action == Action.UP){
                    if (plateau[0][1].isDraw())
                        return false;
                    else {
                        plateau[0][1].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[1][3].isDraw())
                        return false;
                    else {
                        plateau[1][3].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[2][1].isDraw())
                        return false;
                    else {
                        plateau[2][1].setDraw(true);
                        return true;
                    }
                }
                break;
            case 5:
                if(action == Action.LEFT)
                    if (plateau[0][3].isDraw())
                        return false;
                    else {
                        plateau[0][3].setDraw(true);
                        return true;
                    }
                if(action == Action.UP){
                    if (plateau[0][2].isDraw())
                        return false;
                    else {
                        plateau[0][2].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[1][3].isDraw())
                        return false;
                    else {
                        plateau[1][3].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[2][2].isDraw())
                        return false;
                    else {
                        plateau[2][2].setDraw(true);
                        return true;
                    }
                }
                break;
            case 6:
                if(action == Action.RIGHT)
                    return false;
                if(action == Action.UP){
                    if (plateau[1][2].isDraw())
                        return false;
                    else {
                        plateau[1][2].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.LEFT){
                    if (plateau[1][3].isDraw())
                        return false;
                    else {
                        plateau[1][3].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[3][2].isDraw())
                        return false;
                    else {
                        plateau[3][2].setDraw(true);
                        return true;
                    }
                }
                break;
            case 7:
                if (action == Action.DOWN || action == Action.LEFT)
                    return false;
                if (action == Action.UP) {
                    if (plateau[2][1].isDraw())
                        return false;
                    else {
                        plateau[2][1].setDraw(true);
                        return true;
                    }
                }
                if (action == Action.RIGHT){
                    if (plateau[2][3].isDraw())
                        return false;
                    else {
                        plateau[2][3].setDraw(true);
                        return true;
                    }
                }
                break;
            case 8:
                if(action == Action.DOWN)
                    return false;
                if(action == Action.UP){
                    if (plateau[2][2].isDraw())
                        return false;
                    else {
                        plateau[2][2].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.LEFT){
                    if (plateau[2][3].isDraw())
                        return false;
                    else {
                        plateau[2][3].setDraw(true);
                        return true;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[3][3].isDraw())
                        return false;
                    else {
                        plateau[3][3].setDraw(true);
                        return true;
                    }
                }
                break;
            case 9:
                if (action == Action.DOWN || action == Action.RIGHT)
                    return false;
                if (action == Action.UP) {
                    if (plateau[3][2].isDraw())
                        return false;
                    else {
                        plateau[2][2].setDraw(true);
                        return true;
                    }
                }
                if (action == Action.LEFT){
                    if (plateau[3][3].isDraw())
                        return false;
                    else {
                        plateau[3][3].setDraw(true);
                        return true;
                    }
                }
                break;
            default:
                return false;
        }

        return false;
    }
}
