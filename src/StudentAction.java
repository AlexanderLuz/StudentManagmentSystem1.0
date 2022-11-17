public enum StudentAction {
    FINISHED(0),
    SHOW_STATUS(1),
    SHOW_PAYMENT_STATUS(2),
    ENROLL_IN_NEW_COURSE(3),
    PAY_FOR_COURSES(4);

    StudentAction(int Index) {
    }

    public static StudentAction getEnum(int parseInt) {
        return switch(parseInt) {
            case 0 -> FINISHED;
            case 2 -> SHOW_PAYMENT_STATUS;
            case 3 -> ENROLL_IN_NEW_COURSE;
            case 4 -> PAY_FOR_COURSES;
            default -> SHOW_STATUS;
        };
    }
}
