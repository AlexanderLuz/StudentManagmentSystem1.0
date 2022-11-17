public final class ConsoleOutputFunctions {
    public static void printFiller(int i, String filler) {
        System.out.println();
        for (int j = 0; j < i; j++) {
            System.out.print(filler);
        }
        System.out.println();
    }
    public static void printBreak() {
        System.out.print(" | ");
    }
}
