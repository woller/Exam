package Streams;

import java.util.stream.Stream;

public class ListToMapExercise
{
    public static void main(String[] args)
    {
        //Opret en Map<Integer, List<Student>> som grupperer Student efter start-år. Dvs. for hver år skal der være en liste med Students.
        Stream<Student> studentStream = LambdaExerciseSetup.getListOfStudents().stream();

        //Map<Integer, List<Student>> studentsByYear = studentStream

        //Map<Integer, List<Student>> studentsByYear = studentStream.collect(Collectors.toMap(Student::getYearStarted, Function.identity()));
    }
}
