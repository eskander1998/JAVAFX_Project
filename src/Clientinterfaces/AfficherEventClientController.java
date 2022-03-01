/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import model.Evenement;
import model.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceEvenement;
import util.MaConnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherEventClientController implements Initializable {
 public static int place_reste= 0;
    private Label fruitNameLable;
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    ServiceEvenement cs = new ServiceEvenement();

    List<Evenement> evenementss = cs.ListClasse();
    private Image image;
    private MyListener myListener;
    @FXML
    private VBox EventChoisieCarte;
    private Label TitreEventLable;
    private Label LieuEventLabel;
    @FXML
    private Label DateEventCarte;
    @FXML
    private Label PlaceRestanteCarte;
    @FXML
    private Label TarifEventCarte;
    @FXML
    private Label TitreEventCarte;
    @FXML
    private Label LieuEventCarte;
    @FXML
    private Button btnLirePlus;
    @FXML
    private Label idEvent;
    @FXML
    private Label idOrganisateurLabel;
    @FXML
    private Label imgEventLabel;
    private FontAwesomeIconView GestionUserIcon;
    @FXML
    private Label PlaceRestante;
    @FXML
    private Label EvenementEffectueCarte;
    @FXML
    private ComboBox combobox_recherType;
    
                private Statement ste;
                MaConnexion db=new MaConnexion();
               private Connection con=db.getCnx();
    private FontAwesomeIcon btnUser;
               
               
    private List<Evenement> getData() {
    List<Evenement> evenements = cs.ListClasse();
    
          ObservableList<Evenement> data =
                  
        FXCollections.observableArrayList(evenements );

     
        return data;
    }

    private void setChosenEvent(Evenement e) {
        String idOrga=String.valueOf(e.getId_organisateur());
        String id =String.valueOf(e.getId());
        idOrganisateurLabel.setText(idOrga);
        idEvent.setText(id);
        TitreEventCarte.setText(e.getTitre());
        LieuEventCarte.setText(e.getLieu());
        
        Date d=e.getDate_event();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");  
        String strDate = dateFormat.format(d); 
        DateEventCarte.setText(strDate);
        imgEventLabel.setText(e.getImage());
       place_reste= e.getCapacite()-e.getNb_reservation();
       if(place_reste<=0)
       { PlaceRestanteCarte.setText("Complet");}
       
       else {PlaceRestanteCarte.setText(String.valueOf(place_reste));}
       
       if(e.getEtat()=="effectue")
       {
           EvenementEffectueCarte.setText("Cette événement a déja eu lieu");
       }
       else {
           
        EvenementEffectueCarte.setText(null);
       }
       float prix= e.getTarif();
       String prixeS=String.valueOf(prix);
        TarifEventCarte.setText(prixeS);
        
        String imgg= e.getImage();
        String ch="/imgEvent/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        fruitImg.setImage(imageF);
       
        EventChoisieCarte.setStyle("-fx-background-color: #" + "6187ED" + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // evenementss.addAll(getData());
       


       ObservableList<String> typee;
        typee = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        combobox_recherType.setItems(typee);
        
        if (evenementss.size() > 0) {
            setChosenEvent(evenementss.get(0));
            myListener = new MyListener() {
                public void onClickListener(Evenement e) {
                    setChosenEvent(e);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenementss.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEventController itemEventController = fxmlLoader.getController();
                itemEventController.setData(evenementss.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LirePlus_Reserver(ActionEvent event) {
        
      
        
        String titre=  TitreEventCarte.getText();
//        String IdOrganisateur = idOrganisateurLabel.getText();
        String LieuEvent= LieuEventCarte.getText();
        String DateEvent= DateEventCarte.getText();
        
        String ImagEvent= (imgEventLabel.getText());
        String tarif= TarifEventCarte.getText();
        String Idevent= idEvent.getText();
        int id_evenement= Integer.parseInt(Idevent);
        String Descri=" ";
          int id_orga=0;
          String imageFf=" ";
          String lieu=" ";
        try {
     String requete = "SELECT description,id_organisateur,image,lieu FROM evenement  where ?=id  order by id desc";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setInt(1, id_evenement);
     ResultSet e = pst.executeQuery();
     while (e.next()) {
  Descri = e.getString("description");
  id_orga= e.getInt("id_organisateur");
  imageFf= e.getString("image");
  lieu= e.getString("lieu");
  
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String IdOrganisateur=  String.valueOf(id_orga);

        
        String ch="/imgEvent/";
        String imgF= ch+imageFf;
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        
    

        if("Complet".equals(PlaceRestanteCarte.getText()))
        {
              Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte !");
               alert.setHeaderText("Vous ne  pouvez pas réserver cette événement");
               alert.setContentText(" Cette événement est complet");
               
               alert.showAndWait();
        }
        else if("Cette événement a déja eu lieu".equals(EvenementEffectueCarte.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte !");
               alert.setHeaderText("Vous ne  pouvez pas réserver cette événement");
               alert.setContentText(" Cette événement a déja eu lieu");
               
               alert.showAndWait();
        }
                else
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RéservationClientEvent.fxml"));
        try {
            Parent root = loader.load();
            RéservationClientEventController pdc = loader.getController();
            pdc.setTitreEventLabel(titre);
            pdc.setDescriptionEventLabel(Descri);
            pdc.setDateEventLabel(DateEvent);
            pdc.setOrganisateurEventLabel(IdOrganisateur);
            pdc.setTarifEventLabel(tarif);
            pdc.setimgEvent(imageF);
            pdc.setidEventLabel(Idevent);
            pdc.setlieuEventLabel(lieu);
            
            btnLirePlus.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    private void GestionUser(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReservationEventClient.fxml"));
        try {
            Parent root = loader.load();
            GestionUserIcon.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercherType(ActionEvent event) {
         
                 grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
       ObservableList<String> typee;
        typee = FXCollections.observableArrayList("Kickboxing","BodyBuilding","Mma","Boxing","Fitness","Karate" );
        combobox_recherType.setItems(typee);
          String Typee= combobox_recherType.getSelectionModel().getSelectedItem().toString();
        if (evenementss.size() > 0) {
            setChosenEvent(evenementss.get(0));
            myListener = new MyListener() {
                public void onClickListener(Evenement e) {
                    setChosenEvent(e);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenementss.size(); i++) {
                
                if(Typee.equals(evenementss.get(i).getType()))
                    {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

              ItemEventController itemEventController = fxmlLoader.getController();
                itemEventController.setData(evenementss.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }}
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }


}

