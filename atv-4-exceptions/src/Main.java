import br.ufal.ic.p2.Facade;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Facade facade = new Facade();
        facade.initAccount();
        System.out.println();
        facade.withdraw();
        facade.getBalance();
    }
}