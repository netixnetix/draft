package steps;
import com.github.javafaker.Faker;

public class FakerData {
    private static final Faker faker = new Faker();

    public static String Login() {
        return faker.name().username();
    }

    public static String FirstName() {
        return faker.regexify("[А-Яа-я]{4,15}");
    }

    public static String Pwd() {
        return faker.internet().password(8, 16, true, true, true);
    }

}
