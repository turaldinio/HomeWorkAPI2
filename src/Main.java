import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


        long peopleYong18 = persons.stream().filter(x -> x.getAge() < 18).count();

        List<String> lastNameConscripts = persons.stream(). //фамилии призывников
                filter(x -> x.getSex() == Sex.MAN).
                filter(x -> x.getAge() > 18 && x.getAge() < 27).
                map(Person::getName).
                collect(Collectors.toList());

        List<String> resultList = persons.stream().
                filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> {
                    if (x.getSex() == Sex.MAN && x.getAge() > 18 && x.getAge() < 65) {
                        return true;
                    } else {
                        if (x.getSex() == Sex.WOMAN && x.getAge() > 18 && x.getAge() < 60) {
                            return true;
                        }
                    }
                    return false;
                }).
                sorted(Comparator.comparing(Person::getFamily)).map(Person::getFamily).collect(Collectors.toList());
    }
}

