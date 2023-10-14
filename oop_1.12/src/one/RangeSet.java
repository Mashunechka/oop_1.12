package one;

import java.util.*;

public class RangeSet<T extends Comparable<T>> {
    private List<Range<T>> ranges = new ArrayList<>();

    public void add(Range<T> range) { //метод добавляющий новый диапазон в список
        List<Range<T>> mergedRanges = new ArrayList<>();
        boolean merged = false;

        // Перебираем все существующие диапазоны
        for (Range<T> existingRange : ranges) {

            // Если конец текущего диапазона меньше начала нового диапазона,
            // то просто добавляем текущий диапазон в список объединенных диапазонов
            if (existingRange.getEnd().compareTo(range.getStart()) < 0) {
                mergedRanges.add(existingRange);

            // Если начало текущего диапазона больше конца нового диапазона,
            // то добавляем новый диапазон, если ранее не было объединения,
            // и затем добавляем текущий диапазон в список объединенных диапазонов
            } else if (existingRange.getStart().compareTo(range.getEnd()) > 0) {
                if (!merged) {
                    mergedRanges.add(range);
                    merged = true;
                }
                mergedRanges.add(existingRange);

            // Если диапазоны перекрываются частично или полностью,
            // то создаем новый диапазон, соединяющий начала и концы обоих диапазонов,
            // и добавляем его в список объединенных диапазонов
            } else {
                T newStart = existingRange.getStart().compareTo(range.getStart()) > 0 ? range.getStart() : existingRange.getStart();
                T newEnd = existingRange.getEnd().compareTo(range.getEnd()) > 0 ? existingRange.getEnd() : range.getEnd();
                Range<T> mergedRange = new Range<>(newStart, newEnd);
                mergedRanges.add(mergedRange);
            }
        }
        // Если не было осуществлено объединение, то добавляем новый диапазон в конец списка
        if (!merged) {
            mergedRanges.add(range);
        }

        // Обновляем список диапазонов
        ranges = mergedRanges;
    }

    public void remove(Range<T> range) { //метод удаляющий определённый диапазон
        List<Range<T>> updatedRanges = new ArrayList<>();

        // Проходимся по каждому существующему диапазону в списке
        for (Range<T> existingRange : ranges) {

            // Если конец существующего диапазона меньше начала удаляемого диапазона
            // или начало существующего диапазона больше конца удаляемого диапазона,
            // то добавляем существующий диапазон в список обновленных диапазонов
            if (existingRange.getEnd().compareTo(range.getStart()) < 0 || existingRange.getStart().compareTo(range.getEnd()) > 0) {
                updatedRanges.add(existingRange);

            // Если начало существующего диапазона больше или равно началу удаляемого диапазона
            // и конец существующего диапазона меньше или равно концу удаляемого диапазона,
            // то не добавляем ничего в список обновленных диапазонов
            } else if (existingRange.getStart().compareTo(range.getStart()) >= 0 && existingRange.getEnd().compareTo(range.getEnd()) <= 0) {

            // Если начало существующего диапазона меньше начала удаляемого диапазона
            // и конец существующего диапазона больше конца удаляемого диапазона,
            // то добавляем два новых диапазона в список обновленных диапазонов:
            // от начала существующего диапазона до начала удаляемого диапазона
            // и от конца удаляемого диапазона до конца существующего диапазона
            } else if (existingRange.getStart().compareTo(range.getStart()) < 0 && existingRange.getEnd().compareTo(range.getEnd()) > 0) {
                updatedRanges.add(new Range<>(existingRange.getStart(), range.getStart()));
                updatedRanges.add(new Range<>(range.getEnd(), existingRange.getEnd()));

            // Если начало существующего диапазона меньше начала удаляемого диапазона,
            // то добавляем новый диапазон от начала существующего диапазона до начала удаляемого диапазона
            } else if (existingRange.getStart().compareTo(range.getStart()) < 0) {
                updatedRanges.add(new Range<>(existingRange.getStart(), range.getStart()));

            // Если конец существующего диапазона больше конца удаляемого диапазона,
            // то добавляем новый диапазон от конца удаляемого диапазона до конца существующего диапазона
            } else if (existingRange.getEnd().compareTo(range.getEnd()) > 0) {
                updatedRanges.add(new Range<>(range.getEnd(), existingRange.getEnd()));
            }
        }
        // Обновляем список диапазонов
        ranges = updatedRanges;
    }

    public boolean intersects(Range<T> range) {
        // Перебираем все существующие диапазоны
        for (Range<T> existingRange : ranges) {

            // Если начало существующего диапазона больше конца переданного диапазона
            // или конец существующего диапазона меньше начала переданного диапазона,
            // то диапазоны не пересекаются
            if (existingRange.getStart().compareTo(range.getEnd()) > 0 || existingRange.getEnd().compareTo(range.getStart()) < 0) {

            // Диапазоны не пересекаются, продолжаем проверку
            } else {
                // Диапазоны пересекаются, возвращаем true
                return true;
            }
        }
        // Если проверка всех диапазонов не нашла пересечения, возвращаем false
        return false;
    }

    public boolean contains(T element) {

        // Проходим по всем диапазонам
        for (Range<T> range : ranges) {
            // Если элемент больше или равен началу диапазона и меньше или равен концу диапазона, то возвращаем true
            if (range.getStart().compareTo(element) <= 0 && element.compareTo(range.getEnd()) <= 0) {
                return true;
            }
        } // Если не найдено ни одного подходящего диапазона, возвращаем false
        return false;
    }

    public boolean isEmpty() { //метод проверяет, является ли набор пустым
        return ranges.isEmpty();
    }

    public void clear() { //метод удаляет все диапазоны из набора
        ranges.clear();
    }

    public int size() { //метод возвращает количество диапазонов в наборе
        return ranges.size();
    }

    public void printRanges() { //метод вывода диапазонов на экран
        for (Range range : ranges) {
            System.out.println("(" + range.getStart() + ", " + range.getEnd() + ") ");
        }
    }

}

class Range<T extends Comparable<T>> {
    private T start;
    private T end;

    public Range(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public void setEnd(T end) {
        this.end = end;
    }
}