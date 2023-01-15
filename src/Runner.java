import animal.AbsAnimal;
import data.AnimalTypeData;
import data.CommandsData;
import factories.AnimalFactory;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String... args) {

        ArrayList<AbsAnimal> listAnimals = new ArrayList();

        //     System.out.println("Введите команду:\n ADD - добавить животное,\n LIST - вывести информацию по животным \n EXIT - выйти" );

        while (true) {
            System.out.println("Введите команду:\n ADD - добавить животное,\n LIST - вывести информацию по животным \n EXIT - выйти");
            String inputCommand = scanner.next().toUpperCase(Locale.ROOT).trim();

            boolean existCommand = false;
            for (CommandsData command : CommandsData.values()) {
                if (command.name().equals(inputCommand)) {
                    existCommand = true;
                    break;
                }
            }
            if (!existCommand) {
                System.out.println("Введена несуществующая команда");
                System.out.println("Повторите ввод ");
                continue;
            }

            CommandsData commandsData = CommandsData.valueOf(inputCommand);

            switch (commandsData) {
                case ADD: {
                    System.out.println("Выполняем добавление животного.\nВведите тип животного (CAT/DOG/DUCK)");
     //               String inputAnimal = scanner.next().toUpperCase(Locale.ROOT).trim();
                    while (true) {
                        String inputAnimal = scanner.next().toUpperCase(Locale.ROOT).trim();
                        boolean existAnimalType = false;
                        for (AnimalTypeData animalType : AnimalTypeData.values()) {
                            if (animalType.name().equals(inputAnimal)) {
                                existAnimalType = true;
                                break;
                            }
                        }
                        if (!existAnimalType) {
                            System.out.println("Введён недопустимый тип животного.\nПовторите ввод (CAT/DOG/DUCK)");
                            continue;
                        }

                        AnimalTypeData animalTypeData = AnimalTypeData.valueOf(inputAnimal);
                        AbsAnimal newAnimal = fillAnimalData(animalTypeData);
                        listAnimals.add(newAnimal);
                        newAnimal.say();
                    break;
                    }
                    break;
                }

                case LIST: {
                    System.out.println("Информация по зарегистрированным животным:");
                    listAnimals.forEach(animal -> System.out.println(animal.toString()));
                    break;
                }
                case EXIT: {
                    System.out.println("До свидания!");
                    System.exit(0);
                }
            }
        }
    }


    public  static AbsAnimal fillAnimalData(AnimalTypeData animalTypeDat) {
        AnimalFactory animalFactory = new AnimalFactory();

        AbsAnimal animal = animalFactory.create(animalTypeDat);

        System.out.println("как зовут животное?");
        animal.setName(scanner.next());

        System.out.println("Какой цвет у животного?");
        animal.setColor(scanner.next());

        System.out.println("Какой возраст у животного?");
        String ageStr = scanner.next();
        while (!isNumber(ageStr)) {
            System.out.println("Введен неверный возрас");
            System.out.println("Повторите ввод");
            ageStr = scanner.next();
        }
        animal.setAge(Integer.parseInt(ageStr));

        System.out.println("Какой вес у животного?");
        String weightStr = scanner.next();
        while (!isNumber(weightStr)) {
            System.out.println("Введен неверный вес");
            System.out.println("Повторите ввод");
            weightStr = scanner.next();
        }
        animal.setWeight(Integer.parseInt(weightStr));

        return animal;
    }

        private static boolean isNumber(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException ignoring) {
                return false;
            }
        }
    }





