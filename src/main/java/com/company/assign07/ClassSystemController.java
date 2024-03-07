package com.mycompany;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional; 




   
@RestController
public class ClassSystemController {
	private List<Student> myStudent = new ArrayList();
	private List<Course> myCourse = new ArrayList();
	private final AtomicInteger studentCounter = new AtomicInteger();
	private final AtomicInteger courseCounter = new AtomicInteger();
	private Registrar registrar = new Registrar();
	
	
    public ClassSystemController(){
		myStudent.add(new Student(studentCounter.incrementAndGet(), "Student", "One", "09/01/1980", "student1@jhu.edu"));
		myStudent.add(new Student(studentCounter.incrementAndGet(), "Student", "Two", "03/01/1940", "student2@jhu.edu"));
		myCourse.add(new Course(courseCounter.incrementAndGet(), "API Design", 123));
		myCourse.add(new Course(courseCounter.incrementAndGet(), "Web Security", 101));
		myCourse.add(new Course(courseCounter.incrementAndGet(), "Java Programming", 222));
    }
	
	public static boolean isEmailValid(String email) {
	   // example from: https://www.baeldung.com/java-email-validation-regex
	   String regexPattern = "^(.+)@(\\S+)$";
	   return Pattern.compile(regexPattern).matcher(email).matches();
	}
	
	public static boolean isDobValid(String dob) {
	   // example from: https://www.baeldung.com/java-email-validation-regex
	   String regexPattern = "^\\d{4}-\\d{2}-\\d{2}$";
	   return Pattern.compile(regexPattern).matcher(dob).matches();
	}
   

	/******************************
	 *	 STUDENT 
	 *
	 ******************************/

	@PostMapping(value = "/student")
	// UC_S1: Instantiate Student object and populate it with data
	// example: http://localhost:8080/student?firstName=Jane&lastName=Doe&dateOfBirth=1990-12-31&email=jane@jhu.edu
	// required = false from reading this posting: https://www.baeldung.com/spring-request-param
    public ResponseEntity addStudent(@RequestParam(value="firstName", required = false) String first, @RequestParam(value="lastName") String last, @RequestParam(value="dateOfBirth") String dob, @RequestParam(value="email") String email) {
		
		// before adding student, check the email
		boolean validEmail = isEmailValid(email);
		boolean validDob  = isDobValid(dob);
		if (validEmail && validDob) {
			myStudent.add(new Student(studentCounter.incrementAndGet(), first, last, dob, email));
			return ResponseEntity.ok(myStudent);
		}
		else {
			System.out.println(email + " was invlaid. No student added");
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
    }

	// UC_S2: Obtain an individual Student object with a given Student Id
	// example: http://localhost:8080/studentId?id=2
	@GetMapping(value = "/studentId")
    public ResponseEntity getStudentId(@RequestParam(value="id") Long id) {
        Student itemToReturn = null;
        for(Student student : myStudent){
            if(student.getId() == id)
                itemToReturn = student;
        }
        return ResponseEntity.ok(itemToReturn);
    }
	
	@GetMapping(value = "/student")
	// UC_S3: Obtain a list of all students. Each student should be listed with all attributes.
	// example: http://localhost:8080/student
    public ResponseEntity studentIndex() {
		System.out.println("+++++++++++++++++++ inside student GET ++++++++++++++++");
        return ResponseEntity.ok(myStudent);
    }
	
	@PutMapping(value = "/student")
	// UC_S4: Update Student object with a given Student Id
	// example: http://localhost:8080/student?id=2&firstName=John&lastName=Doe&dateOfBirth=11/01/1970&email=johndoe@jh.edu
    public ResponseEntity updateStudent(@RequestParam(value="firstName") String first, @RequestParam(value="lastName") String last, @RequestParam(value="dateOfBirth") String dob, @RequestParam(value="email") String email, @RequestParam(value="id") Long id) {
        myStudent.forEach(Student ->  {
            if(Student.getId() == id){
                Student.setFirstName(first);
				Student.setLastName(last);
				Student.setDateOfBirth(dob);
				Student.setEmail(email);
            }
        });
        return ResponseEntity.ok(myStudent);
    }	

	@DeleteMapping(value = "/student")
	// UC_S5: Delete Student object with a given Student Id
	// example: http://localhost:8080/student?id=1
    public ResponseEntity removeStudent(@RequestParam(value="id") Integer id) {
        Student itemToRemove = null;
        for(Student student : myStudent){
            if(student.getId() == id)
                itemToRemove = student;
        }
        myStudent.remove(itemToRemove);
        return ResponseEntity.ok(myStudent);
    }
	

	/******************************
	 *	 COURSE 
	 *
	 ******************************/
	 	@PostMapping(value = "/course")
	// UC_C1: Instantiate Course object and populate it with data
	// example: http://localhost:8080/course?courseNumber=888&courseTitle=python programming
    public ResponseEntity addCourse(@RequestParam(value="courseTitle") String title, @RequestParam(value="courseNumber") int number) {
        myCourse.add(new Course(courseCounter.incrementAndGet(), title, number));
        return ResponseEntity.ok(myCourse);
    }

	// UC_C2: Obtain an individual Course object with a given Course Number
	// example: http://localhost:8080/CourseNumber?courseNumber=123
	@GetMapping(value = "/CourseNumber")
    public ResponseEntity getCourseId(@RequestParam(value="courseNumber") int courseNumber) {
        Course itemToReturn = null;
        for(Course c : myCourse){
            if(c.getCourseNumber() == courseNumber)
                itemToReturn = c;
        }
        return ResponseEntity.ok(itemToReturn);
    }
	
	@GetMapping(value = "/course")
	// UC_C3: Obtain a list of all course. Each course should be listed with all attributes.
	// example: http://localhost:8080/course
    public ResponseEntity courseIndex() {
		System.out.println("+++++++++++++++++++ inside course GET ++++++++++++++++");
        return ResponseEntity.ok(myCourse);
    }
	
	@PutMapping(value = "/course")
	// UC_C4: Update Course object with a given Course Number
	// example: http://localhost:8080/course?courseNumber=123&courseTitle=API Design Update
    public ResponseEntity updateCourse(@RequestParam(value="courseNumber") int number, @RequestParam(value="courseTitle") String title) {
        myCourse.forEach(Course ->  {
            if(Course.getCourseNumber() == number){
                Course.setCourseTitle(title);
            }
        });
        return ResponseEntity.ok(myCourse);
    }	
	
	@DeleteMapping(value = "/course")
	// UC_C5: Delete Course object with a given Course Number
	// example: http://localhost:8080/course?courseNumber=888
    public ResponseEntity removeSCourse(@RequestParam(value="courseNumber") int number) {
        Course itemToRemove = null;
        for(Course course : myCourse){
            if(course.getCourseNumber() == number)
                itemToRemove = course;
        }
        myCourse.remove(itemToRemove);
        return ResponseEntity.ok(myCourse);
    }
	
	
	/******************************
	 *	 REGISTRAR 
	 *
	 ******************************/
	 @PostMapping(value = "/registrar")
	// UC_R1: Register a given student to a given course
	// example:
	// reference for including an array of ints: https://stackoverflow.com/questions/14324881/in-spring-how-do-i-bind-a-list-of-integers-to-a-requestparam
    public ResponseEntity addRegister(@RequestParam(value="courseNumber") int number, @RequestParam(value="studentIds", required = false) int[] sIds) {
		
        // TODO error check if student id was not found
		boolean added = registrar.addStudent(number, sIds);
		if (added) {
			String output = "";
			for (Integer x : sIds) {
				output += x + ",";
			}
			return ResponseEntity.ok("successfully added student (ids=" + output + ") to registration of course " + number );
		} else {
			return ResponseEntity.ok("could not add student to registrar");
		}
    }
	
	@GetMapping(value = "/registrar")
	// UC_R2: Obtain list of all students registered to a given course
	// example: http://localhost:8080/registrar?courseNumber=123
    public ResponseEntity getRegister(@RequestParam(value="courseNumber") int number) {
		String enrolledStudents = registrar.getStudentList(number);
		System.out.println("enrolledStudents = "  + enrolledStudents);
		if (enrolledStudents == null) {
			return ResponseEntity.ok("Course number not found");	
		}			
		return ResponseEntity.ok("Student (ids) enrolled in course number (" + number + ") are :" + enrolledStudents);		
    }	
	
	@DeleteMapping(value = "/registrar")
	// UC_R3: Drop a given student registered to a given course
	// example: http://localhost:8080/course?courseNumber="888.777"&courseTitle="python programming"
    public ResponseEntity deleteRegister(@RequestParam(value="courseNumber") int number, @RequestParam(value="studentId") Integer sId) {
		boolean dropped = registrar.removeStudent(number, sId);
		System.out.println("was student dropped = "  + dropped);
		return ResponseEntity.ok(dropped);		
    }	

	
	
}