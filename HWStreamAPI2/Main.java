package HWStreamAPI2;

import java.util.*;
import java.util.stream.*;

public class Main {

    // Метод для получения и вывода из списка имени и фамилии
    public static void getPersonFullName (List list){
        int num = 0;
        Iterator<String> it = list.iterator();
        if (list.isEmpty()){
            System.out.println("не зарегестрировано");
        }
        while (it.hasNext()) {
            String person = it.next();
            num++;
            System.out.println(num + ". " + person);
        }
    }

    private static List<String> potentiallyRecruitPeople = new ArrayList<>(); // Список для хранения имени и фамилии призывников
    private static List<String> potentiallyWorkingPeople = new ArrayList<>(); // Список для хранения имени и фамилии работоспособных людей
    private static List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John"); // Список имён для генерацмии списка переписи
    private static List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown"); // Список фамилий для генерацмии списка переписи
    private static List<Person> persons = new ArrayList<>(); // Список переписи

    public static void main(String[] args) {

        // Генерация списка переписи
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Вывод на экран полного списка переписи
        System.out.println(persons + "\n");

        // Стрим для выявления несовершеннолетних
        long minorAges = persons.stream()
                .filter(x -> x.getAge() <= 18)
                .count();
        System.out.println("Зарегестрировано несовершеннолетних:\n" + minorAges);

        // Стрим для выявления призывников
        potentiallyRecruitPeople = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .map(x -> x.getFamily() + " " + x.getName())
                .collect(Collectors.toList());

        // Вывод на экран фамилии и имени призывников
        System.out.println("Граждане призывного возраста:");
        getPersonFullName(potentiallyRecruitPeople);

        // Стрим для выявления работоспособных людей
        potentiallyWorkingPeople = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() >= 18 )
                .filter(x -> x.getAge() <= 65 )
                .filter (x -> x.getSex().equals(Sex.MAN) || x.getSex().equals(Sex.WOMEN) && x.getAge() <= 60 )
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .map(x -> x.getFamily() + " " + x.getName())
                .collect(Collectors.toList());

        // Вывод на экран фамилии и имени работоспособных людей
        System.out.println("\nПотенциально работоспособные граждане:");
        getPersonFullName(potentiallyWorkingPeople);


    }


}

