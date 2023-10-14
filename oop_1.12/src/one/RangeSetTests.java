package one;
import org.junit.Test;

public class RangeSetTests {
    @Test
    public void testContains() {
        RangeSet<Integer> rangeSet = new RangeSet<>();
        rangeSet.add(new Range<>(1, 5));
        rangeSet.printRanges();
        System.out.println("Содержится элемент 3 в диапазоне:" + rangeSet.contains(3));
        System.out.println("-".repeat(40));
    }

    @Test
    public void testIsEmpty() {
        RangeSet<Integer> rangeSet = new RangeSet<>();
        rangeSet.add(new Range<>(1, 5));
        rangeSet.printRanges();
        System.out.println("Диапазон пустой:" + rangeSet.isEmpty());
        System.out.println("-".repeat(40));
    }

    @Test
    public void testRemove() {
        RangeSet<Integer> rangeSet = new RangeSet<>();
        rangeSet.add(new Range<>(1, 10));
        rangeSet.add(new Range<>(20, 30));
        rangeSet.add(new Range<>(40, 50));
        rangeSet.printRanges();
        rangeSet.remove(new Range<>(5, 41));
        System.out.println("Новые диапазоны после удаления:");
        rangeSet.printRanges();
        System.out.println("-".repeat(40));
    }

    @Test
    public void testSize() {
        RangeSet<Integer> rangeSet = new RangeSet<>();
        rangeSet.add(new Range<>(5, 10));
        rangeSet.add(new Range<>(18, 28));
        rangeSet.printRanges();
        System.out.println("Количество диапазонов:" + rangeSet.size());
        System.out.println("-".repeat(40));
    }

    @Test
    public void testIntersects() {
        RangeSet<Integer> rangeSet = new RangeSet<>();
        rangeSet.add(new Range<>(5, 10));
        rangeSet.printRanges();
        System.out.println("Диапазоны пересекаются:" + rangeSet.intersects(new Range<>(2, 4)));

    }
}
