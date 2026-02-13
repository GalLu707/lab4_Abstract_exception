/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_abstract_exception;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author USER
 */
public class GuiBase extends JFrame{
    
    private final Font base = new Font("Comic Sans MS", Font.BOLD, 0x14);
    
    public GuiBase(String tittle, int base, int altura){
        super(tittle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(base, altura);
        setLocationRelativeTo(null);
        
    }
    
    protected JButton createBtn(String txt){
        JButton btn = new JButton(txt);
        
        btn.setFont(base);//ns si se va a poner :p
        btn.setBackground(new Color(33, 255, 226));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10,16,10,16));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        
        return btn;
    }
    
    protected JTextField createTextField(int x, int y, int w, int h){
        JTextField tf = new JTextField();
        tf.setFont(base.deriveFont(14f));
        tf.setBounds(x, y, w, h);
        tf.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 200),1,true),
                new EmptyBorder(10,12,10,12)
        ));
        return tf;
    }
    
    protected JLabel createLabel(String txt, int x, int y, int w, int h){
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 24f));
        l.setBounds(x,y,w,h);
        return l;
        
    }
    
    protected JLabel createLabelTitle(String txt, int x, int y, int w, int h){
        JLabel l = new JLabel(txt);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 32f));
        l.setBounds(x,y,w,h);
        return l;
 
    }
    
    protected JPanel createPanelPrincipal(){
        JPanel p = new JPanel(new BorderLayout()){
            
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                
                GradientPaint gp = new GradientPaint(0,0, new Color(245, 248,255) ,getHeight(), getWidth(), 
                        new Color(225, 235, 250)
                 );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
            
        };
        return p;
    }
    
    protected JScrollPane createTable(String[] columnas, Object[][] datos, int rowHeight){
        JTable tbl = new JTable(datos, columnas);
        Color primary = new Color(33, 150, 243);
        
        tbl.setShowHorizontalLines(false);
        tbl.setShowVerticalLines(false);
        tbl.setRowHeight(rowHeight);
        
        tbl.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
        
        @Override
        public Component getTableCellRendererComponent(JTable t , Object v, boolean s, boolean f, int r, int c){
            super.getTableCellRendererComponent(t,v,s,f,r,c);
            setFont(base.deriveFont(Font.BOLD, 14f));
            setForeground(Color.white);
            setBackground(primary);
            setBorder(new EmptyBorder(10,12,10,12));
            setHorizontalAlignment(SwingConstants.CENTER);
            setOpaque(true);
            return this;

            
    }
        });
        tbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            
            public Component getTableRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c){
                super.getTableCellRendererComponent(t,v,s,f,r,c);
                setOpaque(false);
                setBorder(new EmptyBorder(8,12,8,12));
                return this;
            }
                

        });
        
        
     tbl.setIntercellSpacing(new Dimension(0,0));
     JScrollPane scroll = new JScrollPane(tbl);
     scroll.setBorder(null);
     scroll.setViewportBorder(null);
     scroll.setOpaque(false);
     scroll.getViewport().setOpaque(false);
     return scroll;
        
        
   }     
    }
    

 