module MyPhoneBook {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens com.gulukal.myPhoneBook.view to javafx.graphics, javafx.fxml;
	
	exports com.gulukal.myPhoneBook.view to javafx.graphics, javafx.fxml;
	exports com.gulukal.myPhoneBook;
	
}



