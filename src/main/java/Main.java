/*
Класс Main нужен для анализа массива данных с информаций о людях с использованием стримов из библиотеки Stream API.
 */
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> streamMinors = persons.stream();
        System.out.println(streamMinors.filter(person -> person.getAge() < 18).count());

        System.out.println();

        Stream<Person> streamConscripts = persons.stream();
        streamConscripts.filter(person -> person.getAge() >= 18).
                filter(person -> person.getAge() <= 27).
                map(person -> person.getFamily()).
                collect(Collectors.toList()).forEach(System.out::println);

        System.out.println();

        Stream<Person> streamWorkable = persons.stream();
        List<Person> personWork = streamWorkable.filter(person ->{
            if (person.getSex() == Sex.MAN){
                return person.getAge() <= 65;
            }
            else{
                return person.getAge() <= 60;
            }
        }).filter(person -> person.getAge() >= 18).
                filter(person -> person.getEducation() == Education.HIGHER).
                sorted(new PersonComparator()).
                collect(Collectors.toList());

        for (Person p: personWork){
            System.out.println(p);
        }
    }
}
