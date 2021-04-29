package com;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimationLadybird implements ActionListener, KeyListener {
    private Button go;
    private TransformGroup wholeLadybird;
    private Transform3D translateTransform;
    private Transform3D rotateTransformX;
    private Transform3D rotateTransformY;
    private Transform3D rotateTransformZ;

    private JFrame mainFrame;

    private float sign=1.0f;
    private float zoom=0.6f;
    private float xloc=0.3f;
    private float yloc=-1.0f;
    private float zloc=0.0f;
    private int moveType=1;
    private Timer timer;

    public AnimationLadybird(TransformGroup wholeLadybird, Transform3D trans, JFrame frame){
        go = new Button("Go");
        this.wholeLadybird=wholeLadybird;
        this.translateTransform=trans;
        this.mainFrame=frame;

        rotateTransformX= new Transform3D();
        rotateTransformY= new Transform3D();
        rotateTransformZ= new Transform3D();

        Ladybird.canvas.addKeyListener(this);
        timer = new Timer(100, this);

        Panel p =new Panel();
        p.add(go);
        mainFrame.add("North",p);
        go.addActionListener(this);
        go.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // start timer when button is pressed
        if (e.getSource()==go){
            if (!timer.isRunning()) {
                timer.start();
                go.setLabel("Stop");
            }
            else {
                timer.stop();
                go.setLabel("Go");
            }
        }
        else {
            Move(moveType);
            translateTransform.setScale(new Vector3d(zoom,zoom,zoom));
            translateTransform.setTranslation(new Vector3f(xloc,yloc,zloc));
            wholeLadybird.setTransform(translateTransform);
        }
    }

    private void Move(int mType){
        if(mType==1){ //fly forward and back
            xloc += 0.1 * sign;
            if (Math.abs(xloc *2) >= 3 ) {
                sign = -1.0f * sign;
                rotateTransformY.rotY(Math.PI);
                translateTransform.mul(rotateTransformY);
            }
        }
        if(mType==2){
            yloc += 0.1 * sign;
            if ( yloc >= 1 || yloc <=  -2) {
                sign = -1.0f * sign;
                rotateTransformY.rotY(Math.PI);
                translateTransform.mul(rotateTransformY);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Invoked when a key has been typed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='1') {
            if(moveType==1){
                rotateTransformX.rotX(sign * Math.PI/2);
                rotateTransformZ.rotZ((sign * -Math.PI/2));
                translateTransform.mul(rotateTransformX);
                translateTransform.mul(rotateTransformZ);
                moveType=2;
            }
            else if(moveType==2){
                rotateTransformY.rotY(sign * -Math.PI/2);
                rotateTransformZ.rotZ((sign * Math.PI/2));
                translateTransform.mul(rotateTransformY);
                translateTransform.mul(rotateTransformZ);
                moveType=1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
    }

}
