/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotmotionplanner;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

/**
 *
 * @author Kyle
 */
public class Cell {
    
    //clockwise points from top left corner
    public Point p0, p1, p2, p3;
    public boolean free;
    public Vector<Cell> neighbors = new Vector<>();
    public int path_no;

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
    
    //fills the neighbors vector with all neighbors of the cell regardless of free designation
    //uses row and column of cells position in grid as input
    public void setNeighbors(int row, int column, Cell[][] grid) {
        
        //just did cases besides bounderies (TODO)
        if((row !=0) && (column != 0) && (row != 24) && (column !=24)) {
            
            neighbors.add(grid[row-1][column]);
            neighbors.add(grid[row+1][column]);
            neighbors.add(grid[row][column-1]);
            neighbors.add(grid[row][column+1]);
            neighbors.add(grid[row-1][column-1]);
            neighbors.add(grid[row+1][column+1]);
            neighbors.add(grid[row+1][column-1]);
            neighbors.add(grid[row-1][column+1]);        
        } else if((row == 0) && (column == 0)){
            
            neighbors.add(grid[row][column+1]);
            neighbors.add(grid[row+1][column]);
            neighbors.add(grid[row+1][column+1]);
        } else if((row == 0) && (column == 24)){
            
            neighbors.add(grid[row][column-1]);
            neighbors.add(grid[row+1][column]);
            neighbors.add(grid[row+1][column-1]);
        } else if((row == 24) && (column == 0)){
            
            neighbors.add(grid[row-1][column]);
            neighbors.add(grid[row][column+1]);
            neighbors.add(grid[row-1][column+1]);
        } else if((row == 24) && (column == 24)){
            
            neighbors.add(grid[row-1][column]);
            neighbors.add(grid[row][column-1]);
            neighbors.add(grid[row-1][column-1]);
        } else if(row == 0){
            
            neighbors.add(grid[row][column-1]);
            neighbors.add(grid[row][column+1]);
            neighbors.add(grid[row+1][column]);
            neighbors.add(grid[row+1][column-1]);
            neighbors.add(grid[row+1][column+1]);
        } else if(row == 24){
            
            neighbors.add(grid[row][column-1]);
            neighbors.add(grid[row][column+1]);
            neighbors.add(grid[row-1][column]);
            neighbors.add(grid[row-1][column-1]);
            neighbors.add(grid[row-1][column+1]);
        } else if(column == 0){
            
            neighbors.add(grid[row-1][column]);
            neighbors.add(grid[row-1][column+1]);
            neighbors.add(grid[row][column+1]);
            neighbors.add(grid[row+1][column+1]);
            neighbors.add(grid[row+1][column]);
        } else if(column == 24){
            
            neighbors.add(grid[row-1][column]);
            neighbors.add(grid[row-1][column-1]);
            neighbors.add(grid[row][column-1]);
            neighbors.add(grid[row+1][column-1]);
            neighbors.add(grid[row+1][column]);
        }
    }
}
