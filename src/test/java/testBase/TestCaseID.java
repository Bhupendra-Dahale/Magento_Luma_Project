package testBase;

import java.lang.annotation.Retention;  //specify how long annotation information should be retained
import java.lang.annotation.RetentionPolicy;  //it's an enum to define how long annotaion should be available for reflection 

//Annotation tells java compiler that TestCasaeID annotation should be retained at runtime
//uses reflection API's to manipulate annotation during program execution
@Retention(RetentionPolicy.RUNTIME) 
public @interface TestCaseID {
    String value(); // A method to store the test case ID
}

//@interface  -  Custome Annotation Type
//TestCaseID  -  name of the Annotation
