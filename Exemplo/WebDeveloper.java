public class WebDeveloper {
    private String nome;

    public WebDeveloper(String nome) {
        this.nome = nome;
    }

    public String introduce() {
        return "Hello, my name is " + nome;
    }

    public String describeJob() {
        return "I work as a(n) ComputerProgrammer, I’m learning OOP and Java!";
    }

    public String describeWebsite() {
        return "My professional website is made from HTML, CSS, Javascript and Java!";
    }
}