package pro.sky.homework2_14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringListImpl stringList;

    @BeforeEach
    void setUp() {
        stringList = new StringListImpl();
    }

    @Test
    void testAddItem() {
        assertEquals("Hello", stringList.add("Hello"));
        assertEquals(1, stringList.size());
        assertTrue(stringList.contains("Hello"));
    }

    @Test
    void testAddItemAtIndex() {
        stringList.add("First");
        stringList.add("Second");
        assertEquals("NewItem", stringList.add(1, "NewItem"));
        assertEquals("First", stringList.get(0));
        assertEquals("NewItem", stringList.get(1));
        assertEquals("Second", stringList.get(2));
        assertEquals(3, stringList.size());
    }

    @Test
    void testSetItem() {
        stringList.add("OldItem");
        assertEquals("NewItem", stringList.set(0, "NewItem"));
        assertEquals("NewItem", stringList.get(0));
    }

    @Test
    void testRemoveItem() {
        stringList.add("ToRemove");
        assertEquals("ToRemove", stringList.remove("ToRemove"));
        assertFalse(stringList.contains("ToRemove"));
        assertEquals(0, stringList.size());
    }

    @Test
    void testGetItem() {
        stringList.add("First");
        assertEquals("First", stringList.get(0));
    }

    @Test
    void testClear() {
        stringList.add("Item1");
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    // Negative Tests
    @Test
    void testAddNullItem() {
        Exception exception = assertThrows(NullItemException.class, () -> {
            stringList.add(null);
        });
        assertNotNull(exception);
    }

    @Test
    void testAddAtInvalidIndex() {
        Exception exception = assertThrows(BadIndexException.class, () -> {
            stringList.add(-1, "Invalid");
        });
        assertNotNull(exception);

        exception = assertThrows(BadIndexException.class, () -> {
            stringList.add(1, "Invalid");
        });
        assertNotNull(exception);
    }

    @Test
    void testSetAtInvalidIndex() {
        Exception exception = assertThrows(BadIndexException.class, () -> {
            stringList.set(-1, "Invalid");
        });
        assertNotNull(exception);

        exception = assertThrows(BadIndexException.class, () -> {
            stringList.set(0, "Invalid");
        });
        assertNotNull(exception);
    }

    @Test
    void testRemoveNonExistentItem() {
        Exception exception = assertThrows(ElementNotFoundException.class, () -> {
            stringList.remove("NonExistent");
        });
        assertNotNull(exception);
    }

    @Test
    void testGetAtInvalidIndex() {
        Exception exception = assertThrows(BadIndexException.class, () -> {
            stringList.get(-1);
        });
        assertNotNull(exception);

        exception = assertThrows(BadIndexException.class, () -> {
            stringList.get(0);
        });
        assertNotNull(exception);
    }

    @Test
    void testFullStorageException() {
        for (int i = 0; i < 10; i++) { // Fill the array to its initial capacity.
            stringList.add("Item" + i);
        }

        Exception exception = assertThrows(FullStorageException.class, () -> {
            stringList.add("ExtraItem");
        });

        assertNotNull(exception);
    }
}