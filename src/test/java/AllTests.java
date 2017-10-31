import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sis.report.CourseSessionTest;
import sis.report.DateUtilTest;
import sis.report.StudentTest;
import sis.studentinfo.RosterRepoterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    StudentTest.class, CourseSessionTest.class
    , RosterRepoterTest.class, DateUtilTest.class
})
public class AllTests {

}
