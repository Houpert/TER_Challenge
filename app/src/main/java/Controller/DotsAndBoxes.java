package Controller;

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
}
