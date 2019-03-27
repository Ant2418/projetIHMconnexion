/*ludi
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Antoine
 */
public class ProjetBaseDeDonnee extends Application {
    
//    private String nom; 
//    private String prenom;
//    private String email; 
//    private String fonction; 
//    private Connection con; 
    private Personne pers;
    private Connexion maCo; 
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
        Parent root = (Parent) loader.load();
        // Permet d'associer ton controleur à page à charger pour faire le lien
        ConnexionController coCo = loader.getController();

        // permet de faire la connection et de la stocker dans l'appli
        maCo = new Connexion();
      
        Scene scene = new Scene(root);
        
        coCo.setConnexion(maCo); 
        
        //CREER UN OBJECT PERSONNE 
        pers= new Personne("0", "0", "0", "0");
        
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Personne getPersonne(){
        return pers; 
    }
    
    public Connexion getConnect(){
        return maCo; 
    }

}
