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
    
    public int start_row;
    public int start_col;
    public int goal_row;
    public int goal_col;
    public ArrayList<Point> path = new ArrayList();
    public Cell[][] grid;
    public boolean target_found;
    
    public ArrayList PlanMotion(Point box1, int box1_size, Point box2, int box2_size, Point box3, int box3_size, Point start, int start_size, Point goal, int goal_size){
       
        path.clear();
        path.add(new Point(goal.x+goal_size/2,goal.y+goal_size/2));
        
        //create rectangle objects for each obstacle so we can test for containment of points
        Rectangle[] boxes = new Rectangle[3];
        
        //we augment each box by the robots size to create point SMPP (TODO)
        boxes[0] = new Rectangle(box1.x, box1.y, box1_size, box1_size);
        boxes[1] = new Rectangle(box2.x, box2.y, box2_size, box2_size);
        boxes[2] = new Rectangle(box3.x, box3.y, box3_size, box3_size);

        //construct a grid of cells
        grid = new Cell[25][25];
        
        for(int i=0; i<500; i=i+20) {
            for(int j=0; j<500; j=j+20) {
                
                grid[i/20][j/20] = new Cell();
                grid[i/20][j/20].p0 = new Point(j,i);
                grid[i/20][j/20].p1 = new Point(j,i+20);
                grid[i/20][j/20].p2 = new Point(j+20,i+20);
                grid[i/20][j/20].p3 = new Point(j+20,i);
                
                //test the emptiness of each cell in the grid
                grid[i/20][j/20].isFree(boxes); 
                grid[i/20][j/20].path_no = 0;       
                
                //create rectangle object to look for source or destination points in grid
                Rectangle r = new Rectangle(grid[i/20][j/20].p0.x, grid[i/20][j/20].p0.y, 20, 20);
                
                if(r.contains(start)) {
                    start_row = i/20;
                    start_col = j/20;
                    grid[i/20][j/20].path_no = 1;
                }
                
                if(r.contains(goal)) {
                    goal_row = i/20;
                    goal_col = j/20;
                }
                
            }
        }

        for(int i=0; i<500; i=i+20) {
            for(int j=0; j<500; j=j+20) {
                
                //set neighbors of each cell (must be done after initialization of all cells)
                grid[i/20][j/20].setNeighbors(i/20, j/20, grid);
            }
        }
        
        //run the shortest path breadth first expansion to compute path
        target_found = false;
        int bfi = 1;
        while(!target_found) {
            breadthFirst(bfi);
            bfi++;
        }
        target_found = false;
        
        //retrace through breadth first expansion to create path
        while(bfi != 0) {
            bfi--;
            appendPath(bfi);          
        }
        
        return path;
    }
    
    
    public void breadthFirst(int bfi) {
        
        for(int i=0; i<500; i=i+20) {
            for(int j=0; j<500; j=j+20) {
                
                if(grid[i/20][j/20].path_no == bfi) {
                    
                    for(int k=0; k<grid[i/20][j/20].neighbors.size(); ++k) {

                       if((grid[i/20][j/20].neighbors.get(k).path_no == 0) && grid[i/20][j/20].free) {

                           grid[i/20][j/20].neighbors.get(k).path_no = grid[i/20][j/20].path_no + 1;
                           
                           if((i/20 == goal_row) && (j/20 == goal_col)) {
                               target_found = true;
                               goal_row = i/20;
                               goal_col = j/20;
                           }
                       }
                   }                   
                }
            }
        }
    }
    
    public void appendPath(int bfi) {
        
        for(int i=0; i<grid[goal_row][goal_col].neighbors.size(); ++i) {
            
            if(grid[goal_row][goal_col].neighbors.get(i).path_no == bfi) {
                
                goal_row = grid[goal_row][goal_col].neighbors.get(i).p0.y/20;
                goal_col = grid[goal_row][goal_col].neighbors.get(i).p0.x/20;
                path.add(new Point((goal_col*20)+10,(goal_row*20)+10));
                return;
            }          
        }
    }
}
