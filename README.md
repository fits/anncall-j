# Anncall-J

アノテーションを使ってメソッドを実行するための簡易的なフレームワーク

## Example

```java
import anncallj.annotation.Call;
import anncallj.processor.Caller;
import anncallj.processor.Callers;

class Sample1 {
    public static void main(String... args) {
        Caller<String> c1 = Callers.regexCaller(Def1.class);

        String r1 = c1.call("testdata105");
        // test105
        System.out.println(r1);

        int r2 = c1.call("109 plus 130");
        // 239
        System.out.println(r2);
    }

    public static class Def1 {
        @Call("testdata([0-9]+)")
        public static String testData(int num) {
            return "test" + num;
        }

        @Call("([0-9]+) plus ([0-9]+)")
        public static int testData(int first, int second) {
            return first + second;
        }
    }
}
```

```java
import anncallj.annotation.Call;
import anncallj.processor.Caller;
import anncallj.processor.Callers;

public class Sample2 {
    private static Caller<Object> caller = Callers.ognlCaller(Sample2.class);

    public static void main(String... args) {
        System.out.println(caller.<Integer>call(5));
    }

    @Call("#this == 0")
    public static int zero(Integer n) {
        return 1;
    }

    @Call("#this > 0")
    public static int number(Integer n) {
        return n * caller.<Integer>call(n - 1);
    }
}
```


