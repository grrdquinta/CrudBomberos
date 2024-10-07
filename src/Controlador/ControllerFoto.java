/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlBombero;

import Vista.NewJFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerFoto  implements MouseListener {
    private mdlBombero modelo;
    private NewJFrame vista;
    public ControllerFoto(mdlBombero modelo, NewJFrame vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        

        modelo.cargarImagenDesdeBD(vista);
        vista.btnSubirImagen.addMouseListener(this);
        //modelo.Mostrar(vista.jtbBombero);
        
        
    } 

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnSubirImagen) {
            try
            {
                modelo.seleccionarImagenYGuardar(vista);
            }
            catch(Exception ex)
            {
                System.out.println("error al intentar subir imagen " + ex);
            }
        }       
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}

