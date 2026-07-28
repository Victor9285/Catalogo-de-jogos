
public class Jogo {
    private String name;
    private String released;

    public Jogo(String name, String released) {
        this.name = name;
        this.released = released;
        }
    public String getname() {
        return name;
    }
    public String getreleased() {
        return released;
    } 
    

    @Override
    public String toString() {
        return "Jogo: " + getname() + "\n"
        + "Lancamento: " + getreleased() + "\n";
    }
}
