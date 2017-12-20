package Streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dotMapExercise
{
    public static void main(String[] args)
    {
        Stream<Student> studentStream = LambdaExerciseSetup.getListOfStudents().stream();
        //Lav en liste af Strings, hvor hver String er en “toString()” udgave af Student klassen. (Hint: brug map() )
        List<String> collect = studentStream.map(Object::toString).collect(Collectors.toList());
        for (String s : collect)
        {
            System.out.println(s);
        }
    }
}
