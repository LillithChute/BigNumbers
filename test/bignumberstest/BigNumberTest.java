package bignumberstest;

import org.junit.Before;
import org.junit.Test;

import bignumbers.ElementNode;
import bignumbers.ListOfNumbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This test class will test required functionality for the BigNumbers operations.
 */
public class BigNumberTest {
  private ListOfNumbers testBigNumbers;

  @Before
  public void setup() {
    testBigNumbers = new ElementNode(0);
  }

  // Test constructors.
  @Test
  public void buildNumberTest() {
    testBigNumbers = new ElementNode("1234");
    assertEquals("1234", testBigNumbers.toString());

    testBigNumbers = new ElementNode("0");
    assertEquals("0", testBigNumbers.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void buildNumberNullTest() {
    testBigNumbers = new ElementNode(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void buildNumberEmptyTest() {
    testBigNumbers = new ElementNode("");
  }

  @Test (expected = IllegalArgumentException.class)
  public void buildNumberLeadingZeroTest() {
    testBigNumbers = new ElementNode("045");
  }

  @Test (expected = IllegalArgumentException.class)
  public void buildNumberAlphaTest() {
    testBigNumbers = new ElementNode("Zen");
  }

  @Test (expected = IllegalArgumentException.class)
  public void buildNumberCommaTest() {
    testBigNumbers = new ElementNode("4,500");
  }

  @Test (expected = IllegalArgumentException.class)
  public void buildNumberWithSpaceTest() {
    testBigNumbers = new ElementNode("14 500");
  }

  // Additional constructor test.
  @Test
  public void constructorTest() {
    assertEquals("0", testBigNumbers.toString());
    ListOfNumbers test = new ElementNode(9, new ElementNode(6));
    assertEquals("69", test.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void constructorWithNullNextNodeTest() {
    ListOfNumbers test = new ElementNode(9, null);
  }

  // No negatives allowed
  @Test (expected = IllegalArgumentException.class)
  public void invalidNegativeNumberTest() {
    testBigNumbers = new ElementNode(-3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidPositiveNumberTest() {
    testBigNumbers = new ElementNode(10);
  }

  @Test
  public void leftShiftZeroTest() {
    testBigNumbers = testBigNumbers.leftShift(2);
    assertEquals("0", testBigNumbers.toString());
    testBigNumbers = testBigNumbers.leftShift(0);
    assertEquals("0", testBigNumbers.toString());
  }

  @Test
  public void leftShiftByOneTest() {
    testBigNumbers = testBigNumbers.addDigit(6);
    assertEquals("6", testBigNumbers.toString());

    testBigNumbers = testBigNumbers.leftShift(1);
    testBigNumbers = testBigNumbers.addDigit(5);
    assertEquals("65", testBigNumbers.toString());
  }

  @Test
  public void leftShiftWithNegativeTest() {
    testBigNumbers = new ElementNode(1);
    testBigNumbers = testBigNumbers.leftShift(3);
    assertEquals("1000", testBigNumbers.toString());

    testBigNumbers = testBigNumbers.leftShift(-1);
    assertEquals("100", testBigNumbers.toString());
  }

  @Test
  public void rightShiftByOneTest() {
    testBigNumbers = new ElementNode(9, new ElementNode(8, new ElementNode(7)));
    testBigNumbers = testBigNumbers.rightShift(1);
    assertEquals("78", testBigNumbers.toString());
  }

  @Test
  public void rightShiftByTwoTest() {
    testBigNumbers = new ElementNode(3, new ElementNode(2, new ElementNode(1)));
    testBigNumbers = testBigNumbers.rightShift(2);
    assertEquals("1", testBigNumbers.toString());
  }

  @Test
  public void rightShiftZeroTest() {
    testBigNumbers = new ElementNode(0);
    testBigNumbers = testBigNumbers.rightShift(1);
    assertEquals("0", testBigNumbers.toString());
  }

  @Test
  public void addDigitTest() {
    testBigNumbers = testBigNumbers.addDigit(4);
    assertEquals("4", testBigNumbers.toString());
    testBigNumbers = testBigNumbers.addDigit(6);
    assertEquals("10", testBigNumbers.toString());
    testBigNumbers = testBigNumbers.addDigit(6);
    assertEquals("16", testBigNumbers.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void addDigitNegativeNumberTest() {
    testBigNumbers = testBigNumbers.addDigit(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addDigitTooBigNumberTest() {
    testBigNumbers = testBigNumbers.addDigit(15);
  }

  @Test
  public void countTest() {
    testBigNumbers = new ElementNode(2);
    assertEquals(1, testBigNumbers.count());

    testBigNumbers = new ElementNode("123456789");
    assertEquals(9, testBigNumbers.count());
  }

  @Test
  public void copy() {
    testBigNumbers = testBigNumbers.addDigit(6);
    testBigNumbers = testBigNumbers.addDigit(6);
    ListOfNumbers copyTest = testBigNumbers.copy();
    assertEquals(testBigNumbers, testBigNumbers);
    assertNotEquals(copyTest, testBigNumbers);
  }

  @Test
  public void getDigitAt() {
    assertEquals(0, testBigNumbers.getDigitAt(0));
    testBigNumbers = testBigNumbers.addDigit(9);
    assertEquals(9, testBigNumbers.getDigitAt(0));

    testBigNumbers = testBigNumbers.leftShift(2);
    testBigNumbers = testBigNumbers.addDigit(5);

    assertEquals(9, testBigNumbers.getDigitAt(2));
    assertEquals(0, testBigNumbers.getDigitAt(1));
    assertEquals(5, testBigNumbers.getDigitAt(0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void getBUmberAtNegativePositionTest() {
    testBigNumbers = new ElementNode("1");
    testBigNumbers.getDigitAt(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void getNumberOutOfBoundsTest() {
    testBigNumbers = new ElementNode("123");
    testBigNumbers.getDigitAt(3);
  }

  @Test
  public void getValueTest() {
    assertEquals(0, testBigNumbers.getValue());
    testBigNumbers = testBigNumbers.addDigit(9);
    assertEquals(9, testBigNumbers.getValue());
  }

  @Test
  public void getNextNodeTest() {
    testBigNumbers = testBigNumbers.addDigit(6);
    testBigNumbers = testBigNumbers.leftShift(2);
    testBigNumbers = testBigNumbers.addDigit(3);
    assertEquals("60", testBigNumbers.getNextNode().toString());
  }

  @Test
  public void addTest() {
    testBigNumbers = new ElementNode("20");
    ListOfNumbers otherNode = new ElementNode("2000");
    assertEquals("2020", testBigNumbers.add(otherNode).toString());
  }

  @Test
  public void setNodeValueTest() {
    testBigNumbers = new ElementNode("12345");
    testBigNumbers.setNodeValue(9);
    assertEquals(9, testBigNumbers.getValue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setNodeValueToNegativeNumberTest() {
    testBigNumbers = new ElementNode("12345");
    testBigNumbers.setNodeValue(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void setValueTooBigTest() {
    testBigNumbers = new ElementNode("30000");
    testBigNumbers.setNodeValue(24);
  }

  @Test
  public void setNextNodeTest() {
    testBigNumbers = new ElementNode("123");
    testBigNumbers.setNextNode(new ElementNode("23"));
    assertEquals("233", testBigNumbers.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setNextNodeToNull() {
    testBigNumbers.setNextNode(null);
  }

  @Test
  public void setCarry() {
    testBigNumbers.setCarry(4);
    assertEquals(4, testBigNumbers.getCarryDigit());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setCarryValueToNegativeNumberTest() {
    testBigNumbers.setCarry(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void setCarryToBigTest() {
    testBigNumbers.setCarry(25);
  }}
