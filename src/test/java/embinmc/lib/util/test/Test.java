package embinmc.lib.util.test;

import embinmc.lib.util.FileUtil;
import embinmc.lib.util.ListUtil;
import embinmc.lib.util.MathUtil;
import embinmc.lib.util.Util;
import embinmc.lib.util.function.preset.Suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {
        final int testSize = 10;
        List<Integer> test = new ArrayList<>(testSize);
        Util.repeat(testSize, i -> {
            System.out.println("Index: " + i);
            test.add(i);
        });
        int index = 1;
        for (int i : test) {
            System.out.println("Checking index: " + index);
            if (i != index) {
                throw new RuntimeException("Failed at index " + index);
            }
            index++;
        }

        test(() -> MathUtil.isIntBetween(5, 4, 6));
        test(() -> MathUtil.powInt(2, 15) == 32768);
        test(() -> MathUtil.powInt(2, 16) == 65536);
        test(() -> MathUtil.powInt(2, 8) == 256);
        test(() -> MathUtil.powInt(10, 5) == 100_000);
        test(Util::always);
        test(Suppliers.ALWAYS);

        List<String> strList = Util.create(new ArrayList<>(), list -> {
            list.add("foo");
            list.add("bar");
            list.add("doo");
        });
        System.out.println(strList);
        strList.add("wack");
        System.out.println(strList);

        List<String> strList2 = ListUtil.mutableOf("goobie", "soupie");
        System.out.println(strList2);
        strList2.add("noob");
        System.out.println(strList2);

        System.out.println("Checking testdir");
        System.out.println(FileUtil.getPathsInFolder("testdir"));
        System.out.println(FileUtil.getFoldersInFolder("testdir"));

        System.out.println("Checking testdir2");
        System.out.println(FileUtil.getPathsInFolder("testdir2"));
        System.out.println(FileUtil.getFoldersInFolder("testdir2"));

        System.out.println("test success maybe");
    }

    private static void test(Supplier<Boolean> test) {
        if (test.get()) return;
        throw new RuntimeException("Test failed!");
    }
}
