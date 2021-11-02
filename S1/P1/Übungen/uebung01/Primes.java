import java.util.stream.IntStream;

class Primes
{
    /**
     * Gibt eine unendliche Folge von Primzahlen aus.
     * Erzeugt alle Zahlen ab zwei und filtert dann all diejenigen aus,
     * die einen Teiler zwischen 2 und der Zahl selbst (exklusiv) haben.
     */
    static void primes()
    {
        IntStream.iterate(2, i -> i + 1)
                .filter(i -> IntStream.range(2, i).noneMatch(j -> i % j == 0))
                .forEach(System.out::println);
    }
}
