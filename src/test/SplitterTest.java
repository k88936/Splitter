import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;



public class SplitterTest
{
    @BeforeEach
    void setUp()
    {
        System.out.println("@BeforeEach，测试开始");
    }
 
    @AfterEach
    void tearDown()
    {
        System.out.println("@AfterEach，测试结束");
    }
 
    @Test
    void sayHello()
    {
       String result ="hello world";
        System.out.println(result);
        // assertEquals("您好，欢迎访问 pan_junbiao的博客",result);
    }
}