/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotmotionplanner;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Kyle
 */
public class Cell {
    
    public Point p0, p1, p2, p3;
    public boolean free;

    //sets the variable "free" to true if the cell has no part of any box inside it
    public void isFree(Rectangle[] boxes) {
        
        for(int i=0; i<3; ++i) {
            
            if(boxes[i].contains(p0) || boxes[i].contains(p1) || boxes[i].contains(p2) || boxes[i].contains(p3)) {
                free = false;
                return;
            }
            else 
                free = true;
        }
    }
}
