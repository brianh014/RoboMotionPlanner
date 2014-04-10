/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotmotionplanner;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class MotionPlanner {
    /**
     * 
     * @param box1 - x and y location of box1
     * @param box1_size - size of box 1
     * @param box2 - x and y location of box2
     * @param box2_size - size of box 2
     * @param box3 - x and y location of box3
     * @param box3_size - size of box 3
     * @param start - starting location of robot
     * @param goal - end goal of robot
     * @return - a list containing points for the path from start to goal
     */
    public ArrayList PlanMotion(Point box1, int box1_size, Point box2, int box2_size, Point box3, int box3_size, Point start, Point goal){
        ArrayList path = new ArrayList();
        return path;
    }
    
}
