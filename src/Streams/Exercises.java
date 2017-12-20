package Streams;

import java.util.Calendar;
import java.util.function.*;

public class Exercises
{
    /*Opret en Predicate, som afgør om en Student er aktiv eller ej. Brug denne Predicate til at udskrive true/false
    i følgende streng: “Student xxx er aktiv: true/false”*/

    public static void main(String[] args)
    {
        Student testStudent = new Student("Jørgen", "joergen@jorge.com", 3, true);

        Predicate<Student> active = (student) -> student.isActive;

        System.out.println("Student " + testStudent.name + " er aktiv: " + active.test(testStudent));


        //Opret en Consumer som udskriver alle 4 fields fra  Student klassen. Sat pænt op :-)
        Consumer<Student> consumer = (student) ->
        {
            System.out.println("Student:\nName: " + student.name + "\nEmail: " + student.email +
                    "\nYear started: " + student.yearStarted + "\nIs active: " + student.isActive);
        };
        consumer.accept(testStudent);

        //Opret en Function som tager et Student objekt som argument, og returnerer en Integer, der viser hvor mange år der er gået siden skolestart.
        Function<Student, Integer> yearsSince = (student) -> Calendar.getInstance().get(Calendar.YEAR) - student.yearStarted;
        System.out.println("Student " + testStudent.name + " has studied for " + yearsSince.apply(testStudent) + " years");

        //Opret en UnaryOperator, som tager imod et String objekt og returnerer strengen i omvendt rækkefølge.
        UnaryOperator<String> reverseThatSith = (string) ->
        {
            String returnString = "";
            for (int i = string.length() - 1; i >= 0; i--)
            {
                returnString += string.charAt(i);
            }
            return returnString;
        };
        System.out.println(reverseThatSith.apply("Darth Vader"));

        //Opret en BinaryOperator som tager imod to Student objekter, og returnerer den Student som er startet senest af de to.
        BinaryOperator<Student> startedLast = (s1, s2) -> s1.yearStarted < s2.yearStarted?s2:s1;

        Student testStudentTo = new Student("Stephanos", "stephanos@allsaints.com", 34, false);

        System.out.println("This student started last:");
        consumer.accept(startedLast.apply(testStudent, testStudentTo));

        //Opret en BiFunction som tager to Student som argument og returnerer en String. Strengen skal være sådan her: “X er i gruppe med Y”, hvor X og Y er navnet på de to Students
        BiFunction<Student, Student, String> biFunction = (studentOne, studentTwo) -> studentOne.name + " er i gruppe med " + studentTwo.name;
        System.out.println(biFunction.apply(testStudent, testStudentTo));


    }

}
