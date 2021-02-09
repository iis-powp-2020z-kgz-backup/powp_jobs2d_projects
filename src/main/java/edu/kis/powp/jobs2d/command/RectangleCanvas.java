package edu.kis.powp.jobs2d.command;

public class RectangleCanvas implements ShapeChecker{
    private int bonduaryPositiveX, bonduaryPositiveY, bonduaryNegativeX, bonduaryNegativeY;

    public RectangleCanvas(int height, int width){
        this.bonduaryPositiveX=width/2;
        this.bonduaryPositiveY=height/2;
        this.bonduaryNegativeX=(-1)*(width/2);
        this.bonduaryNegativeY=(-1)*(height/2);
    }

    @Override
    public boolean checkBonduary(int x, int y) {
        if((x>bonduaryPositiveX)||(x<bonduaryNegativeX)){
            return true;
        }
        if((y>bonduaryPositiveY)||(y<bonduaryNegativeY)){
            return true;
        }
        return false;
    }
}
