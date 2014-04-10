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
import java.awt.geom.Rectangle2D;
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
    
    Point mouseStart = new Point(0,0);
    
    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RobotFrame = new RobotWorkspace();
        RobotFrame.setBackground(new java.awt.Color(255, 255, 255));
        RobotFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RobotFrame.setPreferredSize(new java.awt.Dimension(500, 500));
        RobotFrame.addMouseListener(new java.awt.event.MouseAdapter() {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RobotFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RobotFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RobotFrameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotFrameMousePressed
        mouseStart = evt.getPoint();
        //System.out.println(evt.getPoint());
    }//GEN-LAST:event_RobotFrameMousePressed

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
    // End of variables declaration//GEN-END:variables

    class RobotWorkspace extends JPanel implements MouseMotionListener{
        
        public RobotWorkspace(){
            addMouseMotionListener(this);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            
            g2.setPaint(Color.red);
            g2.fill(new Rectangle2D.Double(box1.x,box1.y,box1_size,box1_size));
            
            g2.setPaint(Color.blue);
            g2.fill(new Rectangle2D.Double(box2.x,box2.y,box2_size,box2_size));
            
            g2.setPaint(Color.green);
            g2.fill(new Rectangle2D.Double(box3.x,box3.y,box3_size,box3_size));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(e.getX() > box1.x && e.getX() < box1.x+box1_size && e.getY() > box1.y && e.getY() < box1.y+box1_size){
                System.out.println(mouseStart);
                box1.x = box1.x+(e.getX()-mouseStart.x);
                box1.y = box1.y+(e.getY()-mouseStart.y);
                RobotFrame.repaint();        
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }

        
        
    }
    
}