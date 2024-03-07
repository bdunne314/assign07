package com.mycompany;
import javax.validation.constraints.Email;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

   private int    id;
   private String firstName;
   private String lastName;
   private String dateOfBirth;
   // referenced: https://www.emaillistvalidation.com/blog/mastering-email-validation-in-spring-boot-a-comprehensive-guide
   @Email(message = "Please provide a valid email address")
   private String email;

   /* NO-ARG CONSTRUCTOR */ 
   Student() {
      System.out.println("Call Student default, no-args constructor  ");
   }

   /* CONSTRUCTOR with ARGS */
   Student (int id, String firstname, String lastname, String dateOfBirth, String email) {
       this.id = id;
       this.firstName = firstname;
       this.lastName = lastname;
	   this.dateOfBirth = dateOfBirth;
	   this.email = email;
    }


   
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getFirstName() {
      return firstName;
   } 
   public String getDateOfBirth() {
      return dateOfBirth;
   } 
   public String getEmail() {
      return email;
   }    
   public void setFirstName(String firstName) { 
      this.firstName = firstName; 
   }
   public String getLastName() { 
      return lastName; 
   } 
   public void setLastName(String lastName) {
      this.lastName = lastName; 
   }
   public void setDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth; 
   }
   public void setEmail(String email) {
      this.email = email; 
   }   
   public String toString() {
	  return this.id + ": " + this.lastName + ", " + this.firstName;
   }
}

