package com.haw.navigation.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tower on 27.09.2014.
 */
public class MEMS_GUI {
    private JPanel panel1;
    private JTextField textField_Vx;
    private JTextField textField_Vy;
    private JTextField textField_Vz;
    private JTextField textField_Roll;
    private JTextField textField_Pitch;
    private JTextField textField_Yaw;
    private JTextField textField_q2;
    private JTextField textField_q1;
    private JTextField textField_q3;
    private JTextField textField_q4;
    private JButton startButton;
    private JButton pauseButton;

    private int z=1;

    public void init() {
        JFrame frame = new JFrame("MEMS_GUI");
        frame.setContentPane(new MEMS_GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void MainGUI() {

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                {
  /*                  textField_q1.setText(q1);           //Da sollen jew. die Werte aus dem Chip reingeschrieben werden als quatrenionen
                    textField_q2.setText(q2);           //Erstmal nur so als Platzhalter reingeschrieben
                    textField_q3.setText(q3);
                    textField_q4.setText(q4);

                    textField_Vx.setText(Vx);
                    textField_Vy.setText(Vy);
                    textField_Vz.setText(Vz);

                    textField_Roll.setText(Roll);
                    textField_Pitch.setText(Pitch);
                    textField_Yaw.setText(Yaw);
*/
                    //Wenn Start gedrückt wird werden die Werte in einer Schleife in die Felder geschrieben
                    //Wenn Pause gedrückt wird, wird die aktualisierung gestoppt indem aus der Schleife gesprungen wird
                    //Werte können somit in Ruhe angesehen werden
                    pauseButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            z=0;
                        }
                    });

                }while (z==1);
            }
        });
    }

}
