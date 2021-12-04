package com.gulukal.myPhoneBook.view;

import java.util.Optional;

import com.gulukal.myPhoneBook.Main;
import com.gulukal.myPhoneBook.model.Person;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

	private Main main; // all data kept in main application

	@FXML
	private TableView<Person>           tblPerson;
	@FXML
	private TableColumn<Person, String> colFirstName;
	@FXML
	private TableColumn<Person, String> colLastName;

	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblTelephone;
	@FXML
	private Label lblCity;
	@FXML
	private Label lblStreet;
	@FXML
	private Label lblZIP;
	@FXML
	private Label lblBirthday;

	public PersonOverviewController() {
		super();
	}

	@FXML
	private void initialize() {
		colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty()); // kiþi listesini
		colLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty()); // yükle ve hazirla

		showPersonDetails(null); // ayrintili bilgileri sifirla

		/*
		 * Alternatif olarak önce br change listener yaratilip addListener metoduna
		 * eklenebilir lambda expression kullanmak þstemeyenlere þiddetle önerilir
		 */
		ChangeListener<Person> kjkj = new ChangeListener<Person>() {
			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				showPersonDetails(newValue);
			}
		};

		// seçili satirin özelliklerine bir changeListener ekliyor.
		tblPerson.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	public void setMain(Main main) {
		this.main = main;
		tblPerson.setItems(main.getPeople());
	}

	private void showPersonDetails(Person person) {
		if (person == null) {
			lblFirstName.setText("");
			lblLastName.setText("");
			lblTelephone.setText("");
			lblStreet.setText("");
			lblCity.setText("");
			lblZIP.setText("");
			lblBirthday.setText("");
		}
		else {
			lblFirstName.setText(person.getFirstName());
			lblLastName.setText(person.getLastName());
			lblTelephone.setText(person.getTelephone());
			lblStreet.setText(person.getStreet());
			lblCity.setText(person.getCity());
			lblZIP.setText("" + person.getZip());
			lblBirthday.setText(person.getFormattedDate(person.getBirthday()));
		}
	}

	@FXML
	private void delete() {
		int selected = tblPerson.getSelectionModel().getSelectedIndex(); // tabloda hangi satirin seçili oldugunu
																			// söylüyor
		if (selected >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(this.main.getPrimary());

			Optional<ButtonType> type = alert.showAndWait();
			if (type.get().getText().equalsIgnoreCase("OK"))
				tblPerson.getItems().remove(selected); // aktif olan satiri listeden siliyor
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(this.main.getPrimary());
			alert.setTitle("DIKKAT");
			alert.setHeaderText("Kisi seçili degil!");
			alert.setContentText("Lütfen bir kisi seçiniz");
			alert.show();
		}
	}
}
