
package org.ge.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.ge.model.Usuario;

public class EmpleadoDashBoardController implements Initializable {

    @FXML
    private Label lblBienvenido; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

   
    public void iniciarUsuario(Usuario usuario) {
       
        
     
        if (usuario != null && lblBienvenido != null) {
            lblBienvenido.setText("Bienvenida(o) " + usuario.getUsername());
        }
    } 
}
