/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.data;

import java.io.Serializable;

/**
 *
 * @author khamidou
 */
public class Position implements Serializable {
    static final long serialVersionUID = 1L;
    
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWX(){
        return x;
    }
    
    public int getWY(){
        return 7 - y;
    }
    
    public int getBX(){
        return 7 - x;
    }
    
    public int getBY(){
        return y;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object P) {
        if (((Position) P).getX() == x && ((Position) P).getY() == y) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        String temp = String.valueOf(x) + String.valueOf(y);
        return Integer.valueOf(temp);
    }
}
