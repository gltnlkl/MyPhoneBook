package com.gulukal.myPhoneBook.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private StringProperty            firstName;
	private StringProperty            lastName;
	private StringProperty            telephone;
	private StringProperty            street;
	private StringProperty            city;
	private IntegerProperty           zip;
	private ObjectProperty<LocalDate> birthday;
	private StringProperty symbol;

	private Person() {
		this.firstName = new SimpleStringProperty();
		this.lastName  = new SimpleStringProperty();
		this.telephone = new SimpleStringProperty();
		this.street    = new SimpleStringProperty();
		this.city      = new SimpleStringProperty();
		this.zip       = new SimpleIntegerProperty();
		this.birthday  = new SimpleObjectProperty<LocalDate>(null);
	}

	public Person(String firstName, String lastName) {
		this();
		this.firstName.set(firstName);
		this.lastName.set(lastName);
	}

	public Person(String firstName, String lastName, String telephone, String street, String city, int zip,
			LocalDate birthday) {

		this(firstName, lastName);
		this.telephone.set(telephone);
		this.street.set(street);
		this.city.set(city);
		this.zip.set(zip);
		this.birthday.set(birthday);
	}

	private Person(Builder builder) {

		this();
		this.firstName.set(builder.firstName);
		this.firstName.set(builder.firstName);
		this.lastName.set(builder.lastName);
		this.telephone.set(builder.telephone);
		this.street.set(builder.street);
		this.city.set(builder.city);
		this.zip.set(builder.zip);
		this.birthday.set(builder.birthday);
	}

	public String getFirstName() {
		return this.firstName.get();
	}

	public StringProperty getFirstNameProperty() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName.get();
	}

	public StringProperty getLastNameProperty() {
		return this.lastName;
	}

	public String getTelephone() {
		return this.telephone.get();
	}

	public String getStreet() {
		return this.street.get();
	}

	public String getCity() {
		return this.city.get();
	}

	public int getZip() {
		return this.zip.get();
	}

	public LocalDate getBirthday() {
		return this.birthday.get();
	}

	public String getFormattedDate(LocalDate date) {
		String                  retVal         = "";
		final String            DATE_FORMAT    = "dd.MMM.yyyy";
		final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
		if (date != null)
			retVal = DATE_FORMATTER.format(date);
		return retVal;
	}

	@Override
	public String toString() {
		return "Person [getFirstName()=" + this.getFirstName() + ", getLastName()=" + this.getLastName()
				+ ", getTelephone()=" + this.getTelephone() + ", getStreet()=" + this.getStreet() + ", getCity()="
				+ this.getCity() + ", getZip()=" + this.getZip() + ", getBirthday()=" + this.getBirthday() + "]";
	}

	public static class Builder { // inner class must be public and static
		private String    firstName = "";
		private String    lastName  = "";
		private String    telephone = "";
		private String    street    = "";
		private String    city      = "";
		private int       zip       = 0;
		private LocalDate birthday  = LocalDate.MIN;

		public Builder firstName(String firstName) { // set metodlarý public olmalý
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}

		public Builder street(String street) {
			this.street = street;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder zip(int zip) {
			this.zip = zip;
			return this;
		}

		public Builder birthday(LocalDate birthday) {
			this.birthday = birthday;
			return this;
		}

		public Person build() {
			return new Person(this); // this bu (Builder) class'tan üretilmiþ bir nesne
		}
	}
}
