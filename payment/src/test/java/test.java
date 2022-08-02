import org.junit.jupiter.api.Test;

public class test {
    @Test
    public void test() {
        int i = 1;
        int n = 5;
        int count = 0;
        while (i <= n) {
            i = i * 2;
            count++;
        }
        System.out.println(count);
    }
}
