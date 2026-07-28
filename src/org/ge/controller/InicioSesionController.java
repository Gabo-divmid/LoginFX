/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.ge.controller;

import java.io.IOException;
import org.ge.DAO.UsuarioDAO;
import org.ge.Utils.SecurityUtil;
import org.ge.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InicioSesionController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Label lblMensaje;

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDAO = new UsuarioDAO();
        lblMensaje.setText("");
        //btnIniciarSesion.setOnAction(e -> eventoInicioSesion());

    }

    @FXML
    public void eventoInicioSesion(ActionEvent evento) {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        //verificación si los datos estan vacios
        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor, complete todos sus datos.");
            return;
        }
        //Datos completos
        String passwordHash = SecurityUtil.hashSHA256(password);
        //llamar al dato para iniciar sesion
        Usuario usuarioIniciado = usuarioDAO.IniciarSesion(usuario, passwordHash);
        // ! =
        if (usuarioIniciado != null) {
            //lblMensaje.setStyle("-fx-background-color: #60682e;");
            lblMensaje.setText("Inicio correcto");
            abrirDashboard(usuarioIniciado);
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }

    private void abrirDashboard(Usuario usuario) {
        String rutaFXML = "";
        String tituloDashboard = "";

        switch (usuario.getRol().toLowerCase()) {
            case "admin":
                rutaFXML = "/org/ge/view/AdminDashboradView.fxml";
                tituloDashboard = "Panel de Administración";
                break;
            case "empleado":

                break;

        }
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent raiz = cargadorFXML.load();
            
            AdminDashBoardController controlado = cargadorFXML.getController();
            controlado.IniciarUsuario(usuario);            
            
            Stage escenario = new Stage();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle(tituloDashboard);
            escenario.show();
            
            Stage escenaActual = (Stage) btnIniciarSesion.getScene().getWindow();
            escenaActual.close();
            
        } catch (IOException e) {
            System.err.println("Error al cargar la vista:" + rutaFXML+ e.getMessage());
            lblMensaje.setText("Error interno");
        }
    }

}
