/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class de connexion.fxml
 *
 * @author Ludivine and Antoine
 */
public class ConnexionController implements Initializable {

    @FXML    private TextField emailTF;
    @FXML    private PasswordField mdpPF;
    @FXML    private Button connexionButton;
   
    private Statement stmt; 
    private ResultSet rs;
    private String prenom; 
    private String nom; 
    private String email;
    private Connection con;
    private Connexion connexion; 
    private ProjetBaseDeDonnee main;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    /**
     * Permet de se connecter par le bais d'un profil, un chercheur ou un 
     * laborantin s'il existe dans la BDD
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    public void connexionButton(ActionEvent event) throws  IOException, SQLException
    {       
            //FAIRE UN LABEL POUR AFFICHER UN MESSAGE D'ERREUR 
        if (emailTF.getText().isEmpty() == false && mdpPF.getText().isEmpty() == false) {
        try {
              
            con = connexion.getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT FONCTION, PRENOM, NOM, EMAIL FROM PERSONNE WHERE email ='" + emailTF.getText() + "' AND mot_de_passe = '" + mdpPF.getText() + "'");
            while (rs.next()) {
                String res=rs.getString(1); 
//                prenom= rs.getString(2);
//                nom=rs.getString(3);
//                email=rs.getString(4);
//                
                //ajout le nom, prénom, email et fonction à la personne connecté
//                main.getPersonne().setPrenom(prenom);
//                main.getPersonne().setNom(nom);
//                main.getPersonne().setEmail(email);
//                main.getPersonne().setFonction(res);
                
                if ("chercheur".equals(res)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Chercheur.fxml"));
                    Parent ajoutParent = (Parent) loader.load();
                 
                    AcceuilChercheurController ACCo = loader.getController();
                    Scene ajoutScene = new Scene(ajoutParent);
                    ACCo.setConnection(connexion);
                    //ACCo.setMain(main);
                    

                    //This line gets the Stage information
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(ajoutScene);
                    window.show();
                }
                else if ("laborantin".equals(res)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Laborantin.fxml"));
                    Parent ajoutParent = (Parent) loader.load();
                    
                    
                    LaborantinController LCO = loader.getController();
                    Scene ajoutScene = new Scene(ajoutParent);
                    LCO.setConnection(connexion);
                    //LCO.setMain(main);
                    
              
                    
                    //This line gets the Stage information
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(ajoutScene);
                    window.show();
                }
            }
          
        } catch (Exception e) {
            System.out.println(e);
        }
        }
 
    }
//        Parent ajoutParent = FXMLLoader.load(getClass().getResource("Laborantin.fxml"));
//        Scene ajoutScene = new Scene(ajoutParent);
//
//        //This line gets the Stage information
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        window.setScene(ajoutScene);
//        window.show();
    
    
    /**
     * 
     * @param MainBDD 
     */
    public void setMainCoco(ProjetBaseDeDonnee mainE) {
        main = mainE;
  
    }
    
    public void setConnexion(Connexion conect){
        connexion = conect; 
   
    }
    
    
}
