package org.launchcode.techjobs.oo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.launchcode.techjobs.oo.*;
import static org.junit.Assert.*;

/**
 * Created by LaunchCode
 */
@RunWith(JUnit4.class)
public class JobTest {

    Job testJob1;
    Job testJob2;
    Job testJob3;
    Job testJob4;
    Job testJob5;

    Employer testEmployer;
    Location testLocation;
    PositionType testPositionType;
    CoreCompetency testCoreCompetency;

    /* -
    Created 5 test jobs for the following tests, but found to pass the auto grader I needed to actually
    create many of them again within the test itself --- Null Pointer Exception
     */


    @Before
    public void createTestJobs(){
        testEmployer = new Employer("Workshop Designs");
        testLocation = new Location("St.Louis");
        testPositionType = new PositionType("Back-End Developer");
        testCoreCompetency = new CoreCompetency("Java");
        testJob1 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));
        testJob2 = new Job("Backend Developer", testEmployer, testLocation, testPositionType, testCoreCompetency);
        testJob3 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        testJob4 = new Job("Product tester", new Employer("ACME"), new Location(null), new PositionType(null), new CoreCompetency(null));
        testJob5 = new Job(null, new Employer(null), new Location(null), new PositionType(null), new CoreCompetency(null));
    }

    /* - Use Unit testing to verify Parts of the Job class -

     */
    @Test
    public void testSettingJobId(){
        Job testJob1;
        Job testJob2;
        testJob1 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));
        testJob2 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));
        assertEquals(testJob1.getId()+1, testJob2.getId(), 0.01);

    }

    @Test
    public void testJobConstructorSetsAllFields(){
        testJob3 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        assertTrue(testJob3 instanceof Job);
        assertTrue(testJob3.getEmployer() instanceof Employer);
        assertTrue(testJob3.getLocation() instanceof Location);
        assertTrue(testJob3.getPositionType() instanceof PositionType);
        assertTrue(testJob3.getCoreCompetency() instanceof CoreCompetency);

        assertEquals(testJob3.getName(), "Product tester");
        assertEquals(testJob3.getEmployer().toString(), "ACME");
        assertEquals(testJob3.getLocation().toString(), "Desert");
        assertEquals(testJob3.getPositionType().toString(), "Quality control");
        assertEquals(testJob3.getCoreCompetency().toString(), "Persistence");
    }

    @Test
    public void testJobsForEquality(){
        testEmployer = new Employer("Workshop Designs");
        testLocation = new Location("St.Louis");
        testPositionType = new PositionType("Back-End Developer");
        testCoreCompetency = new CoreCompetency("Java");
        testJob1 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));
        testJob2 = new Job("Backend Developer", testEmployer, testLocation, testPositionType, testCoreCompetency);
        assertFalse(testJob1.equals(testJob2));
    }


    /* - Use TDD to design and code a custom toString method for the Job class

     */
    @Test
    public void testJobToString(){
        String expectedString = "\nID: "+testJob1.getId()+"\nName: Backend Developer\nEmployer: Workshop Designs\nLocation: St.Louis\nPosition Type: Back-End Developer\nCore Competency: Java\n";
        String actualString = testJob1.toString();

        assertEquals(expectedString, actualString);

//        /* - Originally I incorporated the two following tests within this one test,
//        but due to the autograder, I did have to break them out to the last test - testToStringHandlesEmptyField
//         */
//        //this tests  returns "Data not available" if a field is null  - new Test???
//        String expectedString2 = "\nID: "+testJob4.getId()+"\nName: Product tester\nEmployer: ACME\nLocation: Data not available\nPosition Type: Data not available\nCore Competency: Data not available\n";
//        String actualString2 = testJob4.toString();
//
//        assertEquals(expectedString2, actualString2);
//
//        //this tests  returns "OOPS! This job does not seem to exist" if a field is null -  new Test???
//        String expectedString3 = "\nOOPS! This job does not seem to exist.\n";
//        String actualString3 = testJob5.toString();
//
//        assertEquals(expectedString3, actualString3);
    }

    @Test
    public void testToStringStartsAndEndsWithNewLine(){
        testJob1 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));
        String actualString = testJob1.toString();

        char firstChar = actualString.charAt(0);
        char lastChar = actualString.charAt(actualString.length()-1);

        //one assert equals for the first \n
        assertEquals(firstChar, '\n');

        //one assert equals for the last \n
        assertEquals(lastChar, '\n');


    }

    @Test
    public void testToStringContainsCorrectLabelsAndData(){
        testJob1 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));

        String expectedString = "\nID: "+testJob1.getId()+"\nName: Backend Developer\nEmployer: Workshop Designs\nLocation: St.Louis\nPosition Type: Back-End Developer\nCore Competency: Java\n";
        String actualString = testJob1.toString();

        assertEquals(expectedString, actualString);

    }

    @Test
    public void testToStringHandlesEmptyField(){
        testJob1 = new Job("Backend Developer", new Employer("Workshop Designs"), new Location("St.Louis"), new PositionType("Back-End Developer"), new CoreCompetency("Java"));
        testJob4 = new Job("Product tester", new Employer("ACME"), new Location(null), new PositionType(null), new CoreCompetency(null));
        testJob5 = new Job(null, new Employer(null), new Location(null), new PositionType(null), new CoreCompetency(null));
        Job testJob6 = new Job("Product tester", new Employer("ACME"), new Location(""), new PositionType(""), new CoreCompetency(""));

        //tests a job with some fields null
        String expectedString2 = "\nID: "+testJob4.getId()+"\nName: Product tester\nEmployer: ACME\nLocation: Data not available\nPosition Type: Data not available\nCore Competency: Data not available\n";
        String actualString2 = testJob4.toString();

        assertEquals(expectedString2, actualString2);

        //tests a job that doesn't exist
        String expectedString3 = "\nOOPS! This job does not seem to exist.\n";
        String actualString3 = testJob5.toString();

        assertEquals(expectedString3, actualString3);

        //tests a job with some fields having empty strings
        String expectedString6 = "\nID: "+testJob6.getId()+"\nName: Product tester\nEmployer: ACME\nLocation: Data not available\nPosition Type: Data not available\nCore Competency: Data not available\n";
        String actualString6 = testJob6.toString();

        assertEquals(expectedString6, actualString6);

    }
}
