/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotmotionplanner;

import java.awt.Point;
import java.awt.Rectangle;
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
     * @param start_size
     * @param goal - end goal of robot
     * @param goal_size
     * @return - a list containing points for the path from start to goal
     */
    
    public ArrayList PlanMotion(Point box1, int box1_size, Point box2, int box2_size, Point box3, int box3_size, Point start, int start_size, Point goal, int goal_size){
        ArrayList<Point> path = new ArrayList();
        path.add(new Point(start.x+start_size/2,start.y+start_size/2));
        
        //create rectangle objects for each obstacle so we can test for containment of points
        Rectangle[] boxes = new Rectangle[3];
        
        //we augment each box by the robots size to create point SMPP (TODO)
        boxes[0] = new Rectangle(box1.x, box1.y, box1_size, box1_size);
        boxes[1] = new Rectangle(box2.x, box2.y, box2_size, box2_size);
        boxes[2] = new Rectangle(box3.x, box3.y, box3_size, box3_size);

        //construct a grid of cells
        Cell[][] grid = new Cell[25][25];
        
        for(int j=0; j<500; j=j+20) {
            for(int i=0; i<500; i=i+20) {
                
                grid[i/20][j/20] = new Cell();
                grid[i/20][j/20].p0 = new Point(i,j);
                grid[i/20][j/20].p1 = new Point(i,j+20);
                grid[i/20][j/20].p2 = new Point(i+20,j+20);
                grid[i/20][j/20].p3 = new Point(i+20,j);
                
                //test the emptiness of each cell in the grid
                grid[i/20][j/20].isFree(boxes);            
            }
        }
        
        //TODO calculate a path from start to goal
        path.add(new Point(goal.x+goal_size/2,goal.y+goal_size/2));
        
        return path;
    }
    
}
