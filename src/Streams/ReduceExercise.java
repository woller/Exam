package Streams;

import java.util.stream.Stream;

public class ReduceExercise
{
    public static void main(String[] args)
    {
        Stream<Student> studentStream = LambdaExerciseSetup.getListOfStudents().stream();
        //Udregn det gennemsnitlige start-år for alle Student i students. (Hint: reduce() )
        //forkert - men lettere: System.out.println(studentStream.mapToInt(student -> student.yearStarted).average());
        System.out.println(studentStream.map(student -> student.yearStarted).reduce(new ImmutableAverager(),
                ImmutableAverager::accept, ImmutableAverager::combine).average());
    }

    //den er immutable for at man kan parallelisere
    static class ImmutableAverager {
        private final int total;
        private final int count;

        public ImmutableAverager() {
            this.total = 0;
            this.count = 0;
        }

        public ImmutableAverager(int total, int count) {
            this.total = total;
            this.count = count;
        }

        public double average() {
            return count > 0 ? ((double) total) / count : 0;
        }

        public ImmutableAverager accept(int i) {
            return new ImmutableAverager(total + i, count + 1);
        }

        public ImmutableAverager combine(ImmutableAverager other) {
            return new ImmutableAverager(total + other.total, count + other.count);
        }
    }
}
