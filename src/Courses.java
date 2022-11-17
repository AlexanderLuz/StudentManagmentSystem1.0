public enum Courses {
    HISTORY101("History-101", 600, 101, Teacher.UMBRIDGE, Year.FRESHMAN, 27),
    MATHEMATICS101("Mathematics-101", 800, 201, Teacher.DUMBLEDORE, Year.FRESHMAN, 9),
    ENGLISH101("English-101", 400, 301, Teacher.HAGRID, Year.FRESHMAN, 10),
    CHEMISTRY101("Chemistry-101", 750, 401, Teacher.SNAPE, Year.FRESHMAN, 2),
    COMPUTER_SCIENCE101("Computer Science-101", 1500, 501, Teacher.DUMBLEDORE, Year.SENIOR, 1);

    private final String Name;
    private final int Cost;
    private final int ID;
    private final Teacher Prof;
    private final Year MinimumYear;
    private final int Capacity;

    private Courses(String Name, int Cost, int ID, Teacher Prof, Year MinimumYear, int Capacity) {
        this.Name = Name;
        this.Cost = Cost;
        this.ID = ID;
        this.Prof = Prof;
        this.MinimumYear = MinimumYear;
        this.Capacity = Capacity;
    }

    public static Courses getEnum(int index) {
        return switch(index) {
            case 101 -> HISTORY101;
            case 201 -> MATHEMATICS101;
            case 301 -> ENGLISH101;
            case 401 -> CHEMISTRY101;
            default -> COMPUTER_SCIENCE101;
        };
    }

    public String getName() {
        return Name;
    }

    public int getCost() {
        return Cost;
    }

    public int getID() {
        return ID;
    }

    public Teacher getProf() {
        return Prof;
    }

    public Year getMinimumYear() {
        return MinimumYear;
    }

    public void printCourseInfo() {
        System.out.println();
        System.out.print(getID()+". ");
        System.out.printf("%-20s", getName());
        ConsoleOutputFunctions.printBreak();
        System.out.printf("%-5s", getCost());
        ConsoleOutputFunctions.printBreak();
        System.out.printf("%-25s", getProf());
        ConsoleOutputFunctions.printBreak();
        System.out.printf("%-20s", getMinimumYear().getName());
        ConsoleOutputFunctions.printBreak();
        System.out.print("Capacity: "+University.CoursesCapacities.get(this)+"/"+getCapacity());
    }

    public int getCapacity() {
        return Capacity;
    }
}
