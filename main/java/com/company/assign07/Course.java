
package com.mycompany;

public class Course {

   private int    id;
   private String courseTitle;
   private int courseNumber;

   /* NO-ARG CONSTRUCTOR */ 
   Course() {
      System.out.println("Call Student default, no-args constructor  ");
   }

   /* CONSTRUCTOR with ARGS */
   Course (int id, String courseTitle, int courseNumber) {
       this.id = id;
       this.courseTitle = courseTitle;
       this.courseNumber = courseNumber;
       System.out.println("Call course constructor with args .....");
    }

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getCourseTitle() {
      return courseTitle;
   } 
   public void setCourseTitle(String courseTitle) { 
      this.courseTitle = courseTitle; 
   }
   public int getCourseNumber() { 
      return courseNumber; 
   } 
   public void setCourseNumber(int courseNumber) {
      this.courseNumber = courseNumber; 
   }
   public String toString() {
	  return this.id + ": " + this.courseTitle + ", " + this.courseNumber;
   }
}

