/*
 * Copyright (c) 2020 Cesar Coelho
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.cesar.coelho.template;

import com.cesar.coelho.utils.UILookAndFeel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class provides a simple form for the control of the consumer.
 */
public class AppGUI extends javax.swing.JFrame {

    private final static String FRAME_NAME = "My App";

    /**
     * Creates the GUI form
     *
     */
    public AppGUI() {
        initComponents();
    }

    /**
     * Main command line entry point.
     *
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        try {
            String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
            // If the Look and Feel is not the default
            if (systemLookAndFeel != null && systemLookAndFeel.contains("MetalLookAndFeel")) {
                // If it is the default, then adapt the size of the font
                UILookAndFeel.adaptFontSizeToDisplay();
            } else {
                UIManager.setLookAndFeel(systemLookAndFeel); // Set as the system
            }
        } catch (UnsupportedLookAndFeelException e) {
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
            // handle exception
        }

        final AppGUI gui = new AppGUI();
        gui.init();
        gui.setVisible(true);
    }

    /**
     * Initializes the GUI
     *
     */
    public void init() {
        this.setLocationRelativeTo(null);
        this.setTitle(AppGUI.FRAME_NAME);

        this.prepareTopBar();
        this.bottomPanel.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeApplication();
            }
        });

        //middle = new SelectionBarPanel();
        //middle.addDefaultTabs();
        //middlePanel.add(middle);
    }

    private void closeApplication() {
        boolean areYouSure = false;

        if (JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit the App?",
                "Are you sure?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            areYouSure = true;
        }

        if (areYouSure) {
            this.exitApplication();
        }
    }

    private void exitApplication() {
        System.exit(0);
    }

    private void prepareTopBar() {
        // topPanel.setBackground(Color.BLACK);
        // topLabel.setText("");
        // topLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // aboutFrame.displayWindow();
            }
        });

        topRightCornerLabel.setText("César Coelho");
        // topRightCornerLabel.setIcon(GUIHelper.getRocketLogo());

        cornerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // cornerPanel.setBackground(GUIHelper.COLOR_TRANSPARENT);
        cornerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //aboutFrame.displayWindow();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        topLabel = new javax.swing.JLabel();
        cornerPanel = new javax.swing.JPanel();
        topRightCornerLabel = new javax.swing.JLabel();
        spacerLabel = new javax.swing.JLabel();
        middlePanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        rightSidePanel = new javax.swing.JPanel();
        bottomRightCornerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Title");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.BorderLayout(5, 2));

        topPanel.setMinimumSize(new java.awt.Dimension(100, 22));
        topPanel.setName("topPanel"); // NOI18N
        topPanel.setLayout(new java.awt.BorderLayout());

        topLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        topLabel.setText("A picture");
        topLabel.setName("topLabel"); // NOI18N
        topPanel.add(topLabel, java.awt.BorderLayout.CENTER);

        cornerPanel.setName("cornerPanel"); // NOI18N
        cornerPanel.setLayout(new java.awt.BorderLayout());

        topRightCornerLabel.setForeground(new java.awt.Color(255, 255, 255));
        topRightCornerLabel.setText("My corner label");
        topRightCornerLabel.setName("topRightCornerLabel"); // NOI18N
        cornerPanel.add(topRightCornerLabel, java.awt.BorderLayout.CENTER);

        spacerLabel.setText("     ");
        spacerLabel.setName("spacerLabel"); // NOI18N
        cornerPanel.add(spacerLabel, java.awt.BorderLayout.LINE_END);

        topPanel.add(cornerPanel, java.awt.BorderLayout.LINE_END);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        middlePanel.setName("middlePanel"); // NOI18N
        middlePanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(middlePanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setMinimumSize(new java.awt.Dimension(318, 20));
        bottomPanel.setName("bottomPanel"); // NOI18N
        bottomPanel.setLayout(new java.awt.BorderLayout());

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        statusLabel.setText("A status bar");
        statusLabel.setName("statusLabel"); // NOI18N
        statusLabel.setPreferredSize(new java.awt.Dimension(298, 40));
        bottomPanel.add(statusLabel, java.awt.BorderLayout.CENTER);

        rightSidePanel.setName("rightSidePanel"); // NOI18N

        bottomRightCornerLabel.setText("  ");
        bottomRightCornerLabel.setName("bottomRightCornerLabel"); // NOI18N
        rightSidePanel.add(bottomRightCornerLabel);

        bottomPanel.add(rightSidePanel, java.awt.BorderLayout.LINE_END);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel bottomRightCornerLabel;
    private javax.swing.JPanel cornerPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JPanel rightSidePanel;
    private javax.swing.JLabel spacerLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel topLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel topRightCornerLabel;
    // End of variables declaration//GEN-END:variables
}
