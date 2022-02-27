package Java3.Lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Checker {
    private static List<Method> classMethods;
    private static List<Method> classTestMethods;
    private static Object testObject;


    public static void start(Class<?> testClass) {
        classMethods = Arrays.asList(testClass.getMethods());
        classTestMethods = getAndSortTestMethods();

        try {
            testObject = testClass.newInstance();

            findAndInvokeBeforeSuiteMethod();
            invokeTestMethods();
            findAndInvokeAfterSuiteMethod();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void invokeTestMethods() throws InvocationTargetException, IllegalAccessException {
        for (Method classMethod : classTestMethods) {
            if (classMethod != null) {
                classMethod.invoke(testObject);
            }
        }
    }

    private static List<Method> getAndSortTestMethods() {
       List<Method> methods = new ArrayList<>();
        for (Method m : classMethods) {
            if (m.getAnnotation(Test.class) != null) {
                methods.add(m);
            }
        }

        methods.sort((o1, o2) -> {
            int o1Priority = o1.getAnnotation(Test.class).priority();
            int o2Priority = o2.getAnnotation(Test.class).priority();
            return Integer.compare(o1Priority, o2Priority);
        });

        return methods;
    }

    private static void findAndInvokeBeforeSuiteMethod() throws InvocationTargetException, IllegalAccessException {
        Method beforeSuite = null;
        for (Method classMethod : classMethods) {
            if (classMethod.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuite == null) {
                    beforeSuite = classMethod;
                } else {
                    throw new RuntimeException("Бльше одного метода @BeforeSuite");
                }
            }
        }
        assert beforeSuite != null;
        beforeSuite.invoke(testObject);
    }

    private static void findAndInvokeAfterSuiteMethod() throws InvocationTargetException, IllegalAccessException {
        Method afterSuite = null;
        for (Method classMethod : classMethods) {
            if (classMethod.getAnnotation(AfterSuite.class) != null) {
                if (afterSuite == null) {
                    afterSuite = classMethod;
                } else {
                    throw new RuntimeException("Бльше одного метода @AfterSuite");
                }
            }
        }
        if (afterSuite != null) {
            afterSuite.invoke(testObject);
        }
    }

}
