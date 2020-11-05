package sample.Film;


import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

class Film{
    public String tittel;
    public double utLeiePris;
    public Person leidAv;

    // konstruktør inn her


    public Film(String tittel, double utLeiePris, Person leidAv) {
        this.tittel = tittel;
        this.utLeiePris = utLeiePris;
        this.leidAv = leidAv;
    }
}

class Person{
    public String navn;
    public String telefonnr;

    public Person(String navn, String telefonnr) {
        this.navn = navn;
        this.telefonnr = telefonnr;
    }
    // konstruktør inn her
}

class FilmUtleie{

    // instansier arrayet her

    ArrayList<Film> filmArrayList = new ArrayList<>();

    public FilmUtleie() {

        // legg inn filmene som skal være til utleie her
        // to filmer holder
        Film film = new Film("Matrix",200,null);
        Film fil1 = new Film("Lord of the ring",100,null);

        filmArrayList.add(film);
        filmArrayList.add(fil1);

    }

    public String leiUt(String navn, String telefonnr, String tittel) {
        // sjekk om filmen finnes og at den ikke er leid ut (at leidAv er null)

        // returner meldinger om det var vellykket utleie
        // om ikke filmen fantes
        // eller om filmen fantes men den var utleid

        String resultat = "";
        boolean funnet = false;
        for (Film enFilm : filmArrayList){
            if(enFilm.tittel.equals(tittel)){
                // filmen funnet
                funnet=true;
                if(enFilm.leidAv==null){
                    // det går ann å leie filmen
                    // opprett personobjekt og lenk det inn i filmen
                    Person enPerson = new Person(navn, telefonnr);
                    enFilm.leidAv = enPerson;
                    resultat += "Filmen ble korrekt leid ut\n";
                    resultat += "Prisen er"+enFilm.utLeiePris+" kr";

                }
                else{
                    resultat += "Filmen ble funnet, men den er leid ut!";
                }
            }
        }
        if(!funnet){
            resultat +="Filmen finnes ikke";
        }
        return resultat;
    }

    public String leverInn(String tittel){
        // sjekk om filmen finnes og at den er utleid (at det er et personobjekt på leidAv)
        // dersom dette nullstill person-pekeren i filmen og returner at den ble levert
        // returner feilmelding dersom filmen ikke finnes
        // returner også feilmelding dersom filmen finnes med ikke er utleid

        for(Film film : filmArrayList){
            if(film.tittel.equals(tittel)){
                if(film.leidAv!=null){
                    film.leidAv =null;
                }
                else{
                    return " filmen finnes med ikke er utleid";
                }
            }
            else{
                return " ";
            }
        }
return " ";
    }

    @Override
    public String toString(){
        // løp igjennom hele registrert og formater
        String ut="";
        // løp igjennom hele registrert og formater
        for (Film enFilm : filmArrayList){
            ut +="Tittel : "+enFilm.tittel+". Pris "+enFilm.utLeiePris+" kr \n";
            if(enFilm.leidAv!=null){
                ut+="er utleid til "+enFilm.leidAv.navn+" "+enFilm.leidAv.telefonnr+"\n";
            }
            else{
                ut+= "er ikke utleid\n";
            }
            ut+="---------------\n";
        }
        return ut;

    }
}


public class filmController {

    // instansier Filmutleien her

    FilmUtleie filmUtleie = new FilmUtleie();

    @FXML
    private TextField txtKundeNavn;

    @FXML
    private TextField txtKundeTelefon;

    @FXML
    private TextField txtFilmTittel;

    @FXML
    private Label lblOversikt;

    @FXML
    void leiUt(ActionEvent event) {
        // kall leiUt og legg resultatet i oversikts-labelen
        String navn = txtKundeNavn.getText();
        String tel = txtKundeTelefon.getText();
        String tittel = txtFilmTittel.getText();
       String result = filmUtleie.leiUt(navn,tel,tittel);

        lblOversikt.setText(result);
    }

    @FXML
    void leverInn(ActionEvent event) {
        // kall lever inn og legg resultatet i oversikts-labelen
        String tittel = txtFilmTittel.getText();
        String result=filmUtleie.leverInn(tittel);
        lblOversikt.setText(result);
    }

    @FXML
    void visUtleie(ActionEvent event) {
        // kall toString metoden og legg resultatet i oversikts-labelen


        lblOversikt.setText(filmUtleie.toString());
    }
}
