import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sis.report.*;
import sis.studentinfo.BasicGradingStrategyTest;
import sis.studentinfo.HonorsGradingStrategyTest;
import sis.studentinfo.RosterRepoterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    StudentTest.class, CourseSessionTest.class, CourseReportTest.class
        , RosterRepoterTest.class, DateUtilTest.class, ReportCardTest.class
        , BasicGradingStrategyTest.class, HonorsGradingStrategyTest.class
})
public class AllTests {

}
