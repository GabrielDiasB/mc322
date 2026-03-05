public class Person {
    private String nome;
    private String occupation;

    public Person(String nome, String occupation) {
        this.nome = nome;
        this.occupation = occupation;
    }

    public String introduce() {
        return "Hello, my name is " + nome;
    }

    public String describeJob() {
        return "I work as a(n) " + occupation;
    }

    public String getName() {
        return nome;
    }
}