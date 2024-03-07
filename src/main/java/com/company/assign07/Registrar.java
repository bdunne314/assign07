
package com.mycompany;
import java.util.ArrayList;
import java.util.HashMap;


public class Registrar {

   private HashMap<Integer, ArrayList<Integer>> courseOfferings = new HashMap<Integer, ArrayList<Integer>>();

   /* NO-ARG CONSTRUCTOR */ 
   Registrar() {
      System.out.println("Call Registrar default, no-args constructor  ");
   }

   /* CONSTRUCTOR with ARGS */
   Registrar (int courseNumber) {
       if (courseOfferings.containsKey(courseNumber)) {
		   this.courseOfferings.put(courseNumber, new ArrayList<Integer>());
	   }
    }

   public boolean addStudent(int courseNumber, int[] ids) {
	  if (courseOfferings.containsKey(courseNumber)) {
		// this course number exists already, so add them
		if (ids == null) {
		    this.courseOfferings.put(courseNumber, new ArrayList<Integer>());
		}
		if (ids.length + this.courseOfferings.get(courseNumber).size() >= 16) {
			System.out.println("registration can not support " + ids.length + " students for course number: " + courseNumber);
			return false;
		} else {
		    for (int x : ids) {
			    this.courseOfferings.get(courseNumber).add(x);			
		    }
		    return true;
	    }
	  }
	  else {
		// new course number, so add it to the map
		this.courseOfferings.put(courseNumber, new ArrayList<Integer>());
		if (ids == null) {
		    return true;
		}
		if (ids.length + this.courseOfferings.get(courseNumber).size() >= 16) {
			System.out.println("registration can not support " + ids.length + " students for course number: " + courseNumber);
			return false;
		} else {
			for (int x : ids) {
				this.courseOfferings.get(courseNumber).add(x);			
			}
			return true;
		}
	  }
	  }	
   public boolean removeStudent(int courseNumber, Integer id) {
	  int index = 0;
	  if (courseOfferings.containsKey(courseNumber)) {
		  for (Integer x : this.courseOfferings.get(courseNumber)) {
			  if (x == id) {
				  System.out.println("student id " + x + " needs to be removed ");
				  this.courseOfferings.get(courseNumber).remove(index);
				  return true;
			  }
			  index++;
		  }
		  return false;
	  }
	  // no course number present, so no student ro remove
	  return false;
   }
   
   // this method returns null if courseNumber was not found in the offering, otherwise it will return a string of student ids
   public String getStudentList(int courseNumber) {
	  if (courseOfferings.containsKey(courseNumber)) {
	      String output = "";
		  for (int x : this.courseOfferings.get(courseNumber)) {
			  output += x + ",";
		  }
		  return output;
	  } else {
		  // no students to return, since the course is not present
		  return null;
	  }
   } 

   
   
   public String toString() {
	  String output = "";
	  for (Integer i : courseOfferings.keySet()) {
		  output += i + " has " +  courseOfferings.get(i).size() + " number of students registered.\n";
	  }
	  return output;
   }
}

