/*
Класс PersonComparator нужен для сравнения людей по фамилии
 */
import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    public int compare(Person a, Person b){

        return a.getFamily().compareTo(b.getFamily());
    }
}