public class App {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("Alice", "Engineer");
        System.out.println(p1.introduce());
        System.out.println(p1.describeJob());
        System.out.println();

        ComputerProgrammer p2 = new ComputerProgrammer("Bruno");
        System.out.println(p2.introduce());
        System.out.println(p2.describeJob());
        System.out.println();

        WebDeveloper p3 = new WebDeveloper("Carla");
        System.out.println(p3.introduce());
        System.out.println(p3.describeJob());
        System.out.println(p3.describeWebsite());
        System.out.println();
    }
}