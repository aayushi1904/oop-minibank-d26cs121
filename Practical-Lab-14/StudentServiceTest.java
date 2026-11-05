import java.io.File;

public class StudentServiceTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void assertEquals(Object expected, Object actual, String msg) {
        if (expected == null && actual == null) return;
        if (expected != null && expected.equals(actual)) return;
        throw new AssertionError(msg + " -> Expected: " + expected + ", Actual: " + actual);
    }

    private static void assertEquals(double expected, double actual, double delta, String msg) {
        if (Math.abs(expected - actual) <= delta) return;
        throw new AssertionError(msg + " -> Expected: " + expected + ", Actual: " + actual);
    }

    private static void assertNotNull(Object obj, String msg) {
        if (obj == null) throw new AssertionError(msg + " -> Object is null");
    }

    public static void testAddAndCount() {
        StudentService service = new StudentService();
        assertEquals(0, service.getStudentCount(), "Initial count must be 0");
        service.addStudent(new Student("D26CS121", "Aayushi Gohil", "CSE", 9.45));
        assertEquals(1, service.getStudentCount(), "Count should be 1 after 1st add");
        service.addStudent(new Student("D26CS131", "Aaravi Shah", "CSE", 8.80));
        assertEquals(2, service.getStudentCount(), "Count should be 2 after 2nd add");
    }

    public static void testValidationExceptions() {
        StudentService service = new StudentService();
        boolean caughtNull = false;
        try {
            service.addStudent(null);
        } catch (IllegalArgumentException e) {
            caughtNull = true;
        }
        if (!caughtNull) throw new AssertionError("Adding null student must throw IllegalArgumentException");

        service.addStudent(new Student("D26CS121", "Aayushi Gohil", "CSE", 9.45));
        boolean caughtDuplicate = false;
        try {
            service.addStudent(new Student("D26CS121", "Duplicate Student", "CSE", 7.50));
        } catch (IllegalArgumentException e) {
            caughtDuplicate = true;
        }
        if (!caughtDuplicate) throw new AssertionError("Adding duplicate ID must throw IllegalArgumentException");
    }

    public static void testPersistence() {
        String testDb = "test_persistence.dat";
        try {
            StudentService service = new StudentService();
            service.addStudent(new Student("D26CS121", "Aayushi Gohil", "CSE", 9.45));
            service.addStudent(new Student("D26IT205", "Diya Joshi", "IT", 9.15));
            service.saveData(testDb);

            StudentService freshLoadedService = new StudentService();
            assertEquals(0, freshLoadedService.getStudentCount(), "Fresh service count must be 0");
            freshLoadedService.loadData(testDb);

            assertEquals(2, freshLoadedService.getStudentCount(), "Loaded count should be 2");
        } catch (Exception e) {
            throw new RuntimeException("Persistence test failed: " + e.getMessage(), e);
        } finally {
            new File(testDb).delete();
        }
    }

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("          RUNNING UNIT TESTS (SERVICE LAYER)     ");
        System.out.println("=================================================");

        run("testAddAndCount", StudentServiceTest::testAddAndCount);
        run("testValidationExceptions", StudentServiceTest::testValidationExceptions);
        run("testPersistence", StudentServiceTest::testPersistence);

        System.out.println("-------------------------------------------------");
        System.out.println("Tests Run: " + (passed + failed) + ", Passed: " + passed + ", Failed: " + failed);
        System.out.println("=================================================");
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            System.out.println(" [PASS] " + name);
            passed++;
        } catch (Throwable t) {
            System.out.println(" [FAIL] " + name + " -> " + t.getMessage());
            failed++;
        }
    }
}