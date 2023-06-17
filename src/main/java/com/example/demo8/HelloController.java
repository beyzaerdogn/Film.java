package com.example.demo8;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HelloController extends Parent {

    @FXML
    private TextField guessTextField;
    @FXML
    private FlowPane infoPane;
    @FXML
    private Label resultLabel;
    private Stage stage;
    private List<Film> films;
    private Film selectedFilm;
    private List<Label> infoLabels;
    private int guessCount;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        // CSV dosyasını okuyarak filmleri oluştur
        loadFilmsFromCSV();
        // Oyunu başlat
          startGame();
    }

    private void loadFilmsFromCSV() {
        films = new ArrayList<>();
        String csvFilePath = "D:\\demo8\\src\\main\\java\\imdb_top_250 (1) - Kopya.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String no = data[0];
                String title = data[1];
                String year = data[2];
                String genre = data[3];
                String origin = data[4];
                String director = data[5];
                String star = data[6];
                String imdbLink = data[7];

                Film film = new Film(no, title, year, genre, origin, director, star, imdbLink);
                films.add(film);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame() {
        Random random = new Random();
        int randomIndex = random.nextInt(films.size());
        Collections.shuffle(films);
        selectedFilm = films.get(randomIndex);
        infoLabels = new ArrayList<>();
        guessCount = 0;

        // Arayüzdeki tüm karoları temizle
        infoPane.getChildren().clear();

        // Seçilen film için bilgi karolarını oluştur
        createInfoTiles(selectedFilm);

        // Sonucu sıfırla
        resultLabel.setText("");

        // Tahmin metin alanını etkinleştir
        guessTextField.setDisable(false);
    }

    private void createInfoTiles(Film film) {
        createInfoTile("No", film.getNo());
        createInfoTile("Title", film.getTitle());
        createInfoTile("Year", film.getYear());
        createInfoTile("Genre", film.getGenre());
        createInfoTile("Origin", film.getOrigin());
        createInfoTile("Director", film.getDirector());
        createInfoTile("Star", film.getStar());
        createInfoTile("IMDB Link", film.getImdbLink());
    }

    private void createInfoTile(String label, String value) {
        Label infoLabel = new Label(label + ": " + value);
        infoLabel.getStyleClass().add("info-label");
        infoLabels.add(infoLabel);
        infoPane.getChildren().add(infoLabel);
    }

    @FXML
    private void guessFilm() {
        String guess = guessTextField.getText();
        guessCount++;

        if (guess.equalsIgnoreCase(selectedFilm.getTitle())) {
            // Tahmin doğru
            resultLabel.setText("Correct Guess!");
            disableGame();
        } else {
            // Tahmin yanlış
            if (guessCount >= 3) {
                resultLabel.setText("You've reached the maximum number of guesses!");
                disableGame();
            } else {
                resultLabel.setText("Wrong guess. Try again!");
            }
        }
    }

    private void disableGame() {
        guessTextField.setDisable(true);
    }

    @FXML
    private void showAnswer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Answer");
        alert.setHeaderText(null);
        alert.setContentText("The film was: " + selectedFilm.getTitle());
        alert.showAndWait();

        disableGame();
    }

    @FXML
    private void exitGame() {
        stage.close();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}






