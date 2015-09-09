package com.zheng.mygame;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 2015/8/6.
 */
public class Contanier {

    private List<Contanier> mChildren = null;
    private float x = 0, y =0;

    public Contanier(){
        mChildren = new ArrayList<Contanier>();
    }

    public void draw(Canvas canvas){

        canvas.save();

        canvas.translate(getX(),getY());

        childrenView(canvas);
        for (Contanier contanier : mChildren){
            contanier.draw(canvas);
        }

        canvas.restore();
    }

    public void childrenView(Canvas canvas){

    }

    public void addChildrenView(Contanier child){
        mChildren.add(child);
    }

    public void removeChildrenView(Contanier child){
        mChildren.remove(child);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
