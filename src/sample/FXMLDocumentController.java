package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Bil{
    public String bilNummer;
    public Date startTid;
    public int plass;
    public boolean kortTid; // true = korttids parkering

    public Bil(String bilNummer, Date startTid, boolean kortTid) {
        // konstruktør
        this.bilNummer = bilNummer;
        this.startTid = startTid;
        this.kortTid = kortTid;
    }

    public String formaterKvittering(){
        // formater kvitteringen etter oppgitt format

        Date nå = new Date();

        String starttidDato = new SimpleDateFormat("dd.MM.YYYY HH:mm").format(startTid);
        String slutttidDato = new SimpleDateFormat("dd.MM.YYYY HH:mm").format(nå);

        String ut ="Kvittering for bilnr"+this.bilNummer+"starttid: "+"\n"+starttidDato +" til "+"\n"+slutttidDato+
                "Betalt"+avgift();

        return ut ;


    }

    public double getPris(){
        // returner 10 eller 20 kroner avhengig av kortTid eller ikke
        if(kortTid){
            return 20 ;
        }
        else{
            return 10;
        }
    }
    public double avgift(){
        // regner ut tiden som er gått i timer og ganger med prisen

        Date nå = new Date();

        long varighet = nå.getTime() - startTid.getTime();
        int timer = (int)varighet/1000;
        double  pris = getPris()*timer ;
        return pris ;
    }
    }


class Parkeringshus{
    // opprett arrayet av biler
    ArrayList<Bil> bilArraylist = new ArrayList<>();

    public void reserverPlass(Bil enBil){
        // legg bilen i arrayet
        bilArraylist.add(enBil);
    }

    public String frigjørPlass(String bilNummeret){

        for(Bil bil : bilArraylist){
            if(bilNummeret.equals(bil.bilNummer)){
                bilArraylist.remove(bil);
                return bil.formaterKvittering();
            }

        }

            return "Finner ikke bilen";
        }
        /*
         ** må finne bilen i arrayet
         ** når den er funnet slett den fra arrayet
         ** og formater kvitteringen som returneres
         ** dersom bilen ikke finnes skal man returnere en passenede tekst
         */
    }


public class FXMLDocumentController {

    // opprett parkeringshuset
    Parkeringshus parkeringshus = new Parkeringshus();

    @FXML
    private Label lblAvgift;

    @FXML
    private TextField txtBilnummer;

    @FXML
    void kjørUt(ActionEvent event) {
        // kall frigjør plass og legg ut kvitteringen i lblAvgift
        String bilNummer = txtBilnummer.getText();
      String result =  parkeringshus.frigjørPlass(bilNummer);

        lblAvgift.setText(result);

    }

    @FXML
    void regKorttid(ActionEvent event) {
        // opprett en bil
        String bilNummer = txtBilnummer.getText();
        Date nå = new Date();
        Bil bil = new Bil(bilNummer,nå,true);
        parkeringshus.reserverPlass(bil);
        // og kall på reserver plass
    }

    @FXML
    void regLangtid(ActionEvent event) {
        // opprett en bil
        // og kall på reserver plass
        String bilNummer = txtBilnummer.getText();
        Date nå = new Date();
        Bil bil = new Bil(bilNummer,nå,false);
        parkeringshus.reserverPlass(bil);

    }
}