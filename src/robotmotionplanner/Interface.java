/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotmotionplanner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Brian
 */
public class Interface extends javax.swing.JFrame {
    
    Point box1 = new Point(20,20);
    int box1_size = 200;
    Point box2 = new Point(300,100);
    int box2_size = 150;
    Point box3 = new Point(150,300);
    int box3_size = 100;
    Point start = new Point(50,400);
    int start_size = 40;
    Point goal = new Point(400,50);
    int goal_size = 40;
    
    Point roboPos = new Point(60,410);
    boolean go = false;
    int roboIndex = 0;
    
    MotionPlanner planner = new MotionPlanner();
    ArrayList<Point> path = new ArrayList();
    
    int TICKRATE = 100;
    Timer tick = new Timer();
    
    int prevx;
    int prevy;
    boolean selectedBox1 = false;
    boolean selectedBox2 = false;
    boolean selectedBox3 = false;
    boolean selectedStart = false;
    boolean selectedGoal = false;
    
    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
        
        path = planner.PlanMotion(box1, box1_size, box2, box2_size, box3, box3_size, start, start_size, goal, goal_size);
        if(path.size() > 0){
            Point g = new Point(goal.x + 20, goal.y + 20);
            Point s = new Point(start.x + 20, start.y + 20);
            path.add(0, g);
            path.add(s);
        }
        
        tick.scheduleAtFixedRate(new TickTask(), 0, TICKRATE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RobotFrame = new javax.swing.JPanel();
        go_button = new javax.swing.JButton();
        reset_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RobotFrame = new RobotWorkspace();
        RobotFrame.setBackground(new java.awt.Color(255, 255, 255));
        RobotFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RobotFrame.setPreferredSize(new java.awt.Dimension(500, 500));
        RobotFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RobotFrameMouseReleased(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RobotFrameMousePressed(evt);
            }
        });

        javax.swing.GroupLayout RobotFrameLayout = new javax.swing.GroupLayout(RobotFrame);
        RobotFrame.setLayout(RobotFrameLayout);
        RobotFrameLayout.setHorizontalGroup(
            RobotFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        RobotFrameLayout.setVerticalGroup(
            RobotFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        go_button.setText("Go");
        go_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go_buttonActionPerformed(evt);
            }
        });

        reset_button.setText("Reset");
        reset_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RobotFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reset_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(go_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(go_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reset_button))
                    .addComponent(RobotFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RobotFrameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotFrameMousePressed
        prevx = evt.getX();
        prevy = evt.getY();
        
        if(evt.getX() > start.x && evt.getX() < start.x+start_size && evt.getY() > start.y && evt.getY() < start.y+start_size)
            selectedStart = true;
        else if(evt.getX() > goal.x && evt.getX() < goal.x+goal_size && evt.getY() > goal.y && evt.getY() < goal.y+goal_size)
            selectedGoal = true;
        else if(evt.getX() > box1.x && evt.getX() < box1.x+box1_size && evt.getY() > box1.y && evt.getY() < box1.y+box1_size)
            selectedBox1 = true;
        else if(evt.getX() > box2.x && evt.getX() < box2.x+box2_size && evt.getY() > box2.y && evt.getY() < box2.y+box2_size)
            selectedBox2 = true;
        else if(evt.getX() > box3.x && evt.getX() < box3.x+box2_size && evt.getY() > box3.y && evt.getY() < box3.y+box2_size)
            selectedBox3 = true;
        
    }//GEN-LAST:event_RobotFrameMousePressed

    private void RobotFrameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotFrameMouseReleased
        selectedBox1 = false;
        selectedBox2 = false;
        selectedBox3 = false;
        selectedStart = false;
        selectedGoal = false;
    }//GEN-LAST:event_RobotFrameMouseReleased

    private void go_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go_buttonActionPerformed
        go = true;
    }//GEN-LAST:event_go_buttonActionPerformed

    private void reset_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_buttonActionPerformed
        go = false;
        roboIndex = 0;
        roboPos.x = start.x + 10;
        roboPos.y = start.y + 10;
        RobotFrame.repaint();
    }//GEN-LAST:event_reset_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RobotFrame;
    private javax.swing.JButton go_button;
    private javax.swing.JButton reset_button;
    // End of variables declaration//GEN-END:variables

    class RobotWorkspace extends JPanel implements MouseMotionListener{

        public RobotWorkspace(){
            addMouseMotionListener(this);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            
            //Draw boxes, start and goal
            g2.setPaint(Color.red);
            g2.fill(new Rectangle2D.Double(box1.x,box1.y,box1_size,box1_size));
            
            g2.setPaint(Color.blue);
            g2.fill(new Rectangle2D.Double(box2.x,box2.y,box2_size,box2_size));
            
            g2.setPaint(Color.green);
            g2.fill(new Rectangle2D.Double(box3.x,box3.y,box3_size,box3_size));
            
            g2.setPaint(Color.black);
            g2.fill(new Ellipse2D.Double(start.x,start.y,start_size,start_size));
            
            g2.fill(new Ellipse2D.Double(goal.x,goal.y,goal_size,goal_size));
          
            //Draw planned path
            if(path.size()>0){
                GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, path.size());
                polyline.moveTo(path.get(0).x,path.get(0).y);
                for(int i = 1; i < path.size(); i++){
                    polyline.lineTo(path.get(i).x, path.get(i).y);
                }
                g2.draw(polyline);
            }
            
            //Draw robot
            g2.setPaint(Color.orange);
            g2.fill(new Ellipse2D.Double(roboPos.x,roboPos.y,20,20));
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(selectedStart){
                if(start.x+(e.getX()-prevx)+start_size <= this.getWidth() && start.y+(e.getY()-prevy)+start_size <= this.getHeight() && start.x+(e.getX()-prevx) >= 0 && start.y+(e.getY()-prevy) >= 0){
                    start.x = start.x+(e.getX()-prevx);
                    start.y = start.y+(e.getY()-prevy);
                }
            }
            else if(selectedGoal){
                if(goal.x+(e.getX()-prevx)+goal_size <= this.getWidth() && goal.y+(e.getY()-prevy)+goal_size <= this.getHeight() && goal.x+(e.getX()-prevx) >= 0 && goal.y+(e.getY()-prevy) >= 0){
                    goal.x = goal.x+(e.getX()-prevx);
                    goal.y = goal.y+(e.getY()-prevy);
                }
            }
            else if(selectedBox1){
                if(box1.x+(e.getX()-prevx)+box1_size <= this.getWidth() && box1.y+(e.getY()-prevy)+box1_size <= this.getHeight() && box1.x+(e.getX()-prevx) >= 0 && box1.y+(e.getY()-prevy) >= 0){
                    box1.x = box1.x+(e.getX()-prevx);
                    box1.y = box1.y+(e.getY()-prevy);
                }
            }
            else if(selectedBox2){
                if(box2.x+(e.getX()-prevx)+box2_size <= this.getWidth() && box2.y+(e.getY()-prevy)+box2_size <= this.getHeight() && box2.x+(e.getX()-prevx) >= 0 && box2.y+(e.getY()-prevy) >= 0){
                    box2.x = box2.x+(e.getX()-prevx);
                    box2.y = box2.y+(e.getY()-prevy);
                }
            }
            else if(selectedBox3){
                if(box3.x+(e.getX()-prevx)+box3_size <= this.getWidth() && box3.y+(e.getY()-prevy)+box3_size <= this.getHeight() && box3.x+(e.getX()-prevx) >= 0 && box3.y+(e.getY()-prevy) >= 0){
                    box3.x = box3.x+(e.getX()-prevx);
                    box3.y = box3.y+(e.getY()-prevy);
                }
            }
 
            this.repaint();
            
            prevx = e.getX();
            prevy = e.getY();
            
            reset_buttonActionPerformed(null);
            
            path = planner.PlanMotion(box1, box1_size, box2, box2_size, box3, box3_size, start, start_size, goal, goal_size);
            if(path.size() > 0){
                Point g = new Point(goal.x + 20, goal.y + 20);
                Point s = new Point(start.x + 20, start.y + 20);
                path.add(0, g);
                path.add(s);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }

        
        
    }
    
    class TickTask extends TimerTask{
        @Override
        public void run(){
            
            if(go && path.size() > 0 && roboIndex < path.size()){
                roboPos.x = path.get(path.size() - 1 - roboIndex).x-10;
                roboPos.y = path.get(path.size() - 1 - roboIndex).y-10;
                RobotFrame.repaint();
                roboIndex++;
            }
        }  
    }
    
}
