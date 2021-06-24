package Union_find;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Union_find_unit_test_run {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Union_find_unit_test.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("----JUNIT TEST RESULTS----\n\n\t" + result.wasSuccessful() + "\n");
    }
}