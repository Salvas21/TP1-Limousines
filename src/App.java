import models.Company;

public class App {
    public static void main(String[] args) {
        try {
            new Company(args).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
