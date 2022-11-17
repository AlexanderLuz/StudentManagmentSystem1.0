import java.util.Scanner;

public class Input_Methods {
    private final Scanner scanner = new Scanner(System.in);
    public String askForInput() {
        return scanner.nextLine();
    }
    public boolean askForContinue(String question, int i) {
        ConsoleOutputFunctions.printFiller(25,"-");
        System.out.println(question+" ("+i+" for yes)");
        int answer = Integer.parseInt(askForInput());
        return answer == i;
    }
}
