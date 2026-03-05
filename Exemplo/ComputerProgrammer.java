public class ComputerProgrammer {
    private String nome;

    public ComputerProgrammer(String nome) {
        this.nome = nome;
    }

    public String introduce() {
        return "Hello, my name is " + nome;
    }

    public String describeJob() {
        return "I work as a(n) ComputerProgrammer, I’m learning OOP and Java!";
    }

    public String getName() {
        return nome;
    }
}