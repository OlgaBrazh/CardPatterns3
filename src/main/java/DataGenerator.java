import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {

    static {
        new Faker(new Locale("ru"));
    }


    private DataGenerator() {

    }
    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[]{"Москва", "Санкт-Петербург", "Пермь", "Екатеринбург", "Оренбург"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().name();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();

    }
    public static class Registration {
        private Registration() {

        }

        public static RegistrationInfo generateInfo(String locale) {

            return new RegistrationInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

        @Value
        public static class RegistrationInfo {

            private String city;
            private String name;
            private String phone;

        }

    }

