package com.mycompany.asm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestStdMgmtClass {

    @BeforeEach
    void setup() {
        StdMgmt.stList.clear(); // Reset danh sách trước mỗi test
    }

    @Test
    void testAddSt() {
        StdMgmt.stList.add(new Std("S001", "Alice", 8.5));
        StdMgmt.stList.add(new Std("S002", "Bob", 7.0));

        assertEquals(2, StdMgmt.stList.size(), "List size should be 2 after adding 2 students.");
        assertEquals("Alice", StdMgmt.stList.get(0).getN(), "First student's name should be Alice.");
        assertEquals(8.5, StdMgmt.stList.get(0).getS(), "First student's score should be 8.5.");
    }

    @Test
    void testEditSt() {
        StdMgmt.stList.add(new Std("S001", "Alice", 8.5));
        for (Std st : StdMgmt.stList) {
            if (st.getId().equals("S001")) {
                st.setN("Alice Updated");
                st.setS(9.0);
            }
        }

        Std student = StdMgmt.stList.get(0);
        assertEquals("Alice Updated", student.getN(), "Student name should be updated.");
        assertEquals(9.0, student.getS(), "Student score should be updated.");
    }

    @Test
    void testDelSt() {
        StdMgmt.stList.add(new Std("S001", "Alice", 8.5));
        StdMgmt.stList.add(new Std("S002", "Bob", 7.0));

        StdMgmt.stList.removeIf(st -> st.getId().equals("S001"));
        assertEquals(1, StdMgmt.stList.size(), "List size should be 1 after deleting a student.");
        assertEquals("Bob", StdMgmt.stList.get(0).getN(), "Remaining student should be Bob.");
    }

    @Test
    void testSortSt() {
        StdMgmt.stList.add(new Std("S001", "Alice", 8.5));
        StdMgmt.stList.add(new Std("S002", "Bob", 9.0));
        StdMgmt.stList.add(new Std("S003", "Charlie", 7.0));

        StdMgmt.quickSort(0, StdMgmt.stList.size() - 1);
        assertEquals("Bob", StdMgmt.stList.get(0).getN(), "Student with highest score should be first.");
        assertEquals("Charlie", StdMgmt.stList.get(2).getN(), "Student with lowest score should be last.");
    }

    @Test
    void testSearchSt() {
        StdMgmt.stList.add(new Std("S001", "Alice", 8.5));
        StdMgmt.stList.add(new Std("S002", "Bob", 7.0));

        Std result = StdMgmt.stList.stream()
                .filter(st -> st.getId().equals("S002"))
                .findFirst()
                .orElse(null);

        assertNotNull(result, "Search should find the student.");
        assertEquals("Bob", result.getN(), "Found student's name should be Bob.");
    }
}