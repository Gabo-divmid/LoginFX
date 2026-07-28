
package org.ge.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.ge.model.Usuario;

public class CajeroDashBoardController implements Initializable {

    @FXML
    private Label lblBienvenido; 
    @FXML
    private Button btnSalir;

    private Usuario usuarioSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void iniciarUsuario(Usuario usuario) {
        this.usuarioSesion = usuario;
        
        if (lblBienvenido != null && usuario != null) {
            lblBienvenido.setText("Bienvenida(o) Cajero: " + usuario.getUsername());
        }
        
        System.out.println("Sesión iniciada con éxito en módulo de Cajero: " + usuario.getUsername());
    }

    @FXML
    public void cerrarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/ge/view/InicioSesionView.fxml"));
            Parent root = loader.load();

            Stage stageLogin = new Stage();
            stageLogin.setScene(new Scene(root));
            stageLogin.setTitle("Inicio de Sesión");
            stageLogin.show();

            Stage escenaActual = (Stage) btnSalir.getScene().getWindow();
            escenaActual.close();
            
        } catch (IOException e) {
            System.err.println("Error al regresar al inicio de sesión: " + e.getMessage());
        }
    }
}
