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

    //player -> j1 / !player -> j2
    private boolean player = true;

    private int nbSquareComplete = 0;
    private int nbSquareCompleteJ1 = 0;
    private int nbSquareCompleteJ2 = 0;


    private void restGame(){
        player = true;
        nbSquareComplete = 0;
        nbSquareCompleteJ1 = 0;
        nbSquareCompleteJ2 = 0;
        initLogic();
    }

    public boolean isPlayer() {
        return player;
    }

    private void drawTrait(Action action, int nbPlateau){
        plateau[nbPlateau][action.ordinal()].setDraw(true);
    }

    public void changeTurn(){
        gameTime = 10 * MS_ONE_SEC;
        soundVolume = 4;
    }

    public void changePlayer(){
        player = !player;
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

    public boolean gameEnd(){
        if(nbSquareComplete == nbSquare)
            return true;
        else
            return false;
    }

    public boolean checkSquareComplete(){
        int nbLineFind, nbTotalSquare = 0;
        for(int i=0; i<nbSquare; i++){
            nbLineFind = 0;
            for(int j=0; j<nbOddsSquare; j++){
                if(plateau[i][j].isDraw())
                    nbLineFind++;

            }
            if(nbLineFind == 4){
                nbTotalSquare++;
            }
        }

        if(nbSquareComplete != nbTotalSquare) {
            nbSquareComplete++;
            if (player)
                nbSquareCompleteJ1++;
            else
                nbSquareCompleteJ2++;

            return true;
        }

        return false;
    }

    public int[] getSquaresWon(int[] res) {

        if (res[0] == 0 && plateau[0][0].isDraw() && plateau[0][1].isDraw() && plateau[0][2].isDraw() && plateau[0][3].isDraw()) {
            res[0] = isPlayer() ? 1 : 2;
        }
        if (res[1] == 0 && plateau[1][0].isDraw() && plateau[2][1].isDraw() && plateau[1][2].isDraw() && plateau[1][3].isDraw()) {
            res[1] = isPlayer() ? 1 : 2;
        }
        if (res[2] == 0 && plateau[2][0].isDraw() && plateau[2][1].isDraw() && plateau[2][2].isDraw() && plateau[2][3].isDraw()) {
            res[2] = isPlayer() ? 1 : 2;
        }
        if (res[3] == 0 && plateau[3][0].isDraw() && plateau[3][1].isDraw() && plateau[3][2].isDraw() && plateau[3][3].isDraw()) {
            res[3] = isPlayer() ? 1 : 2;
        }
        return res;
    }



    public int addTrait(Action action, int point){
        if(Action.EMPTY == action)
            return -1;

        switch (point) {
            case 1:
                if (action == Action.UP || action == Action.LEFT)
                    return -1;
                if (action == Action.DOWN) {
                    if (plateau[0][1].isDraw())
                        return -1;
                    else {
                        plateau[0][1].setDraw(true);
                        return 3;
                    }
                }
                if (action == Action.RIGHT){
                    if (plateau[0][0].isDraw())
                        return -1;
                    else {
                        plateau[0][0].setDraw(true);
                        return 1;
                    }
                }
                break;
            case 2:
                if(action == Action.UP)
                    return -1;
                if(action == Action.LEFT){
                    if (plateau[0][0].isDraw())
                        return -1;
                    else {
                        plateau[0][0].setDraw(true);
                        return 1;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[1][0].isDraw())
                        return -1;
                    else {
                        plateau[1][0].setDraw(true);
                        return 2;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[0][2].isDraw())
                        return -1;
                    else {
                        plateau[0][2].setDraw(true);
                        return 4;
                    }
                }
                break;
            case 3:
                if (action == Action.UP || action == Action.RIGHT)
                    return -1;
                if (action == Action.DOWN) {
                    if (plateau[1][2].isDraw())
                        return -1;
                    else {
                        plateau[1][2].setDraw(true);
                        return 5;
                    }
                }
                if (action == Action.LEFT){
                    if (plateau[1][0].isDraw())
                        return -1;
                    else {
                        plateau[1][0].setDraw(true);
                        return 2;
                    }
                }
                break;
            case 4:
                if(action == Action.LEFT)
                    return -1;
                if(action == Action.UP){
                    if (plateau[0][1].isDraw())
                        return -1;
                    else {
                        plateau[0][1].setDraw(true);
                        return 3;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[0][3].isDraw())
                        return -1;
                    else {
                        plateau[0][3].setDraw(true);
                        return 6;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[2][1].isDraw())
                        return -1;
                    else {
                        plateau[2][1].setDraw(true);
                        return 8;
                    }
                }
                break;
            case 5:
                if(action == Action.LEFT)
                    if (plateau[0][3].isDraw())
                        return -1;
                    else {
                        plateau[0][3].setDraw(true);
                        return 6;
                    }
                if(action == Action.UP){
                    if (plateau[0][2].isDraw())
                        return -1;
                    else {
                        plateau[0][2].setDraw(true);
                        return 4;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[1][3].isDraw())
                        return -1;
                    else {
                        plateau[1][3].setDraw(true);
                        return 7;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[2][2].isDraw())
                        return -1;
                    else {
                        plateau[2][2].setDraw(true);
                        return 9;
                    }
                }
                break;
            case 6:
                if(action == Action.RIGHT)
                    return -1;
                if(action == Action.UP){
                    if (plateau[1][2].isDraw())
                        return -1;
                    else {
                        plateau[1][2].setDraw(true);
                        return 5;
                    }
                }
                if(action == Action.LEFT){
                    if (plateau[1][3].isDraw())
                        return -1;
                    else {
                        plateau[1][3].setDraw(true);
                        return 7;
                    }
                }
                if(action == Action.DOWN){
                    if (plateau[3][2].isDraw())
                        return -1;
                    else {
                        plateau[3][2].setDraw(true);
                        return 10;
                    }
                }
                break;
            case 7:
                if (action == Action.DOWN || action == Action.LEFT)
                    return -1;
                if (action == Action.UP) {
                    if (plateau[2][1].isDraw())
                        return -1;
                    else {
                        plateau[2][1].setDraw(true);
                        return 8;
                    }
                }
                if (action == Action.RIGHT){
                    if (plateau[2][3].isDraw())
                        return -1;
                    else {
                        plateau[2][3].setDraw(true);
                        return 11;
                    }
                }
                break;
            case 8:
                if(action == Action.DOWN)
                    return -1;
                if(action == Action.UP){
                    if (plateau[2][2].isDraw())
                        return -1;
                    else {
                        plateau[2][2].setDraw(true);
                        return 9;
                    }
                }
                if(action == Action.LEFT){
                    if (plateau[2][3].isDraw())
                        return -1;
                    else {
                        plateau[2][3].setDraw(true);
                        return 11;
                    }
                }
                if(action == Action.RIGHT){
                    if (plateau[3][3].isDraw())
                        return -1;
                    else {
                        plateau[3][3].setDraw(true);
                        return 12;
                    }
                }
                break;
            case 9:
                if (action == Action.DOWN || action == Action.RIGHT)
                    return -1;
                if (action == Action.UP) {
                    if (plateau[3][2].isDraw())
                        return -1;
                    else {
                        plateau[3][2].setDraw(true);
                        return 10;
                    }
                }
                if (action == Action.LEFT){
                    if (plateau[3][3].isDraw())
                        return -1;
                    else {
                        plateau[3][3].setDraw(true);
                        return 12;
                    }
                }
                break;
            default:
                return -1;
        }

        return -1;
    }

}
