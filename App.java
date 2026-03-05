public class App {
    public static void main(String[] args) throws Exception {
        Heroi h1 = new Heroi("Mago", 20, 5, 20);
        System.out.println(h1.receberDano());
        System.out.println(h1.receberDano());
        System.out.println(h1.ganharEscudo());
        System.out.println(h1.estaVivo());
    }
}