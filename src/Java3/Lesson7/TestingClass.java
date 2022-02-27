package Java3.Lesson7;

public class TestingClass {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite -- OK");
    }

//    @BeforeSuite
//    public void beforeSuite2() {
//        System.out.println("Before Suite OK");
//    }

    @Test(priority = 2)
    public void test1 () {
        System.out.println("test1 -- OK");
    }

    @Test(priority = 1)
    public void test2 () {
        System.out.println("test2 -- OK");
    }

    @Test
    public void test3 () {
        System.out.println("test3 -- OK");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite -- OK");
    }

}
