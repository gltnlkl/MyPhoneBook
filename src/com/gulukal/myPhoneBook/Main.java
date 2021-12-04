package com.gulukal.myPhoneBook;

import java.io.IOException;

import com.gulukal.myPhoneBook.model.Person;
import com.gulukal.myPhoneBook.view.PersonOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primary;
	private BorderPane rootPane;
	private ObservableList<Person> people;

	@SuppressWarnings("exports")
	public ObservableList<Person> getPeople() {
		if (this.people == null) {
			this.people = FXCollections.observableArrayList();
		}
		return this.people;
	}

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		initPrimaryStage(primaryStage);
		initRootLayout();
		initTelephoneEntries();
		showPersonOverview();
		symbol();
	}

	private void symbol() {

	}

	private void initTelephoneEntries() {
		this.getPeople().add(new Person("Babür", "Somer", "533 437 9929", null, null, 0, null)); // klasik yontem ile
																									// ekleme
		this.getPeople().add(new Person.Builder().firstName("Eren").lastName("Yasak").build()); // builder ile ekleme
		this.getPeople().add(new Person.Builder().firstName("Ali").lastName("Ergül").build());
		this.getPeople().add(new Person.Builder().firstName("Çaðlayan").lastName("Kaya").build());
		this.getPeople().add(new Person.Builder().firstName("Çaðri").lastName("Türkmen").build());
		this.getPeople().add(new Person.Builder().firstName("Canan").lastName("Havva").build());
		this.getPeople().add(new Person.Builder().firstName("Görkem").lastName("Sönmez").build());
		this.getPeople().add(new Person.Builder().firstName("Gülten").lastName("Ulukal").build());
		this.getPeople().add(new Person.Builder().firstName("Mert Can").lastName("Aydin").build());
		this.getPeople().add(new Person.Builder().firstName("Melih").lastName("Dumanli").build());
		this.getPeople().add(new Person.Builder().firstName("Mustafa").lastName("Öztürk").build());
		this.getPeople().add(new Person.Builder().firstName("Onur").lastName("Gündoðdu").build());
		this.getPeople().add(new Person.Builder().firstName("Recep").lastName("Ergan").build());
		this.getPeople().add(new Person.Builder().firstName("Sercan").lastName("Üstün").build());
		this.getPeople().add(new Person.Builder().firstName("Burak").lastName("Güneþ").city("Samatya").build());

//		for (Person person : people) {
//			System.out.println(person);
//		}
	}

	private void initPrimaryStage(Stage primaryStage) {
		this.primary = primaryStage;
		this.primary.getIcons().add(new Image(getClass().getResourceAsStream("resources/TelephoneBook.png")));
		this.primary.setTitle("Telefon Rehberi");
	}

	private void showPersonOverview() {
		FXMLLoader loader = new FXMLLoader(); // herhangi bir scene'i ekranda göstermek için, FXML'ini yüklememiz
												// gerekli
		loader.setLocation(getClass().getResource("view/PersonOverview.fxml")); // FXML dosyasi nerede belirtiyoruz
		try {
			AnchorPane personOverview = (AnchorPane) loader.load(); // FXML'i okuyoruz
			this.rootPane.setCenter(personOverview); // RootPane borderpane cinsinden olduðundan ALT/ÜST/SAÐ/SOL/ORTA
														// alanlari bulunmakta
														// ORTA'ya personoverview'u ekliyor

			PersonOverviewController controller = loader.getController(); // FXML dosyasi içinde tanimli controller'i
																			// dinamik
																			// bir þekilde sinifimiza dahil ediyoruz
			controller.setMain(this); // controller'a main',in içerdiði bilgiler iletiliyor
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void initRootLayout() {
		try {
			this.rootPane = (BorderPane) FXMLLoader.load(getClass().getResource("view/TelephoneBook.fxml"));
			Scene scene = new Scene(this.rootPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			this.primary.setScene(scene);
//			primaryStage.initStyle(StageStyle.UNDECORATED);   // arayuz ikonlarını kapatir
			this.primary.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("exports")
	public Stage getPrimary() {
		return this.primary;
	}
}
