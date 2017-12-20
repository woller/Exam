package Streams;

import java.util.stream.Stream;

public class Streams
{
    public static void main(String[] args)
    {
        //Opret en Stream<Student> ud fra students listen. Du bruge denne Stream til de følgende opgaver. Du skal også
        // undlade at bruge almindelige while og for-løkker her.
        Stream<Student> studentStream = LambdaExerciseSetup.getListOfStudents().stream();

        //Udskriv alle aktive Students fra students
        //studentStream.filter((student) -> student.isActive).forEach(activeStudent -> System.out.println(activeStudent.name));

        //Udskriv alle Students fra students sorteret efter stigende start-år. Implementér selv sorteringsalgoritmen.
        //studentStream.sorted((s1, s2) -> s1.yearStarted - s2.yearStarted).forEach(student -> System.out.println(student.name));

        //Udskriv alle aktive Students fra students sorteret efter stigende start-år
        studentStream.filter((s) -> s.isActive).sorted((s1, s2) -> s1.yearStarted - s2.yearStarted).
                forEach(s -> System.out.println(s.name));



    }
}
