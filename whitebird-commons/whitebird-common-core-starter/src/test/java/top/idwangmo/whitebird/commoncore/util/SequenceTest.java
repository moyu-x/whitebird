package top.idwangmo.whitebird.commoncore.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SequenceTest {

    @Test
    public void testRepeated() {
        Set<Long> sets = new HashSet<>();
        int maxTimes = 1000 * 10;
        Sequence sequence = new Sequence();
        for (int i = 0; i < maxTimes; i++) {
            long id = sequence.nextId();
            System.out.println(id);
            sets.add(id);
        }

        assertEquals(maxTimes, sets.size());
    }

}