package bignumbers;

import java.util.HashSet;

/**
 * The concrete implementation of list of numbers interface.
 */
public class ElementNode implements ListOfNumbers {

  private int value;
  private ListOfNumbers next;
  private int carry;

  /**
   * Create a node with a value, which is a single digit in the big number.
   *
   * @param val the value of this digit.
   * @throws IllegalArgumentException if the value is not a valid digit.
   */
  public ElementNode(int val) {
    if (val < 0 || val > 9) {
      throw new IllegalArgumentException("Digit must be between 0 and 9.");
    }

    this.value = val;
    this.next = new EmptyNode();
    this.carry = 0;
  }

  /**
   * Create a node with a value and another node instance that is the next one to this.
   *
   * @param value  the value of this digit
   * @param next the next node
   * @throws IllegalArgumentException if the value is not a valid digit, or the next node is null
   */
  public ElementNode(int value, ListOfNumbers next) {
    if (next == null) {
      throw new IllegalArgumentException("The next node is null.");
    }

    if (value < 0 || value > 9) {
      throw new IllegalArgumentException("Digit must be between 0 and 9.");
    }

    this.value = value;
    this.next = next;
    this.carry = 0;
  }

  /**
   * Create a node with the digit as a String.
   *
   * @param number the number.
   */
  public ElementNode(String number) {
    if (number == null || number.length() == 0) {
      throw new IllegalArgumentException("Number is not a valid number.");
    }

    // Split the number into an array.
    String[] numberSplit = number.split("");
    HashSet<String> allNumbers = getHashOfSingleDigitsAsString();

    // Loop through the list of digits and make sure they are valid.
    for (String item : numberSplit) {
      if (!allNumbers.contains(item)) {
        throw new IllegalArgumentException("Number is not a valid number.");
      }
    }

    if (numberSplit[0].equals("0") && numberSplit.length > 1) {
      throw new IllegalArgumentException("Number isn't valid.");
    }

    ListOfNumbers result = new ElementNode(0);

    for (int i = 0; i < numberSplit.length - 1; i++) {
      int digit = Integer.parseInt(numberSplit[i]);
      result = result.addDigit(digit);
      result = result.leftShift(1);
    }

    result = result.addDigit(
            Integer.parseInt(numberSplit[numberSplit.length - 1])
    );

    this.value = result.getValue();
    this.next = result.getNextNode();
  }

  @Override
  public int count() {
    int count = 1;
    ListOfNumbers tempNode = this;

    while (!(tempNode.getNextNode() instanceof EmptyNode)) {
      tempNode = tempNode.getNextNode();
      count++;
    }

    return count;
  }

  @Override
  public ListOfNumbers leftShift(int numberOfShifts) {
    if (numberOfShifts < 0) {
      return this.rightShift(-numberOfShifts);
    }

    if (this.next instanceof EmptyNode && this.value == 0) {
      return this;
    }

    if (numberOfShifts == 0) {
      return this;
    }

    ListOfNumbers tempNode = new ElementNode(0, this);

    for (int i = 0; i < numberOfShifts - 1; i++) {
      tempNode = new ElementNode(0, tempNode);
    }

    return tempNode;
  }

  @Override
  public ListOfNumbers rightShift(int numberOfShifts) {
    if (numberOfShifts < 0) {
      return leftShift(-numberOfShifts);
    } else if (numberOfShifts >= this.count()) {
      return new ElementNode(0);
    }

    ListOfNumbers tempNode = this;

    for (int i = 0; i < numberOfShifts; i++) {
      tempNode = tempNode.getNextNode();
    }

    return tempNode;
  }

  @Override
  public ListOfNumbers addDigit(int singleDigit) throws IllegalArgumentException {
    if (singleDigit < 0 || singleDigit > 9) {
      throw new IllegalArgumentException("The digit must be between 0 and 9.");
    }

    this.carry = singleDigit;
    ListOfNumbers next = this;

    while (true) {
      // Deal with the issue of carrying the digit.
      if (next.getCarryDigit() + next.getValue() < 10) {
        next.setNodeValue(next.getCarryDigit() + next.getValue());
        next.setNextNode(next.getNextNode());

        break;
      } else {
        next.setNodeValue(next.getCarryDigit() + next.getValue() - 10);

        if (next.getNextNode() instanceof EmptyNode) {
          next.setNextNode(new ElementNode(1));

          break;
        } else {
          next.setNextNode(next.getNextNode());
          next.getNextNode().setCarry(1);
        }
      }

      next = next.getNextNode();
    }

    return this;
  }

  @Override
  public int getCarryDigit() {
    return this.carry;
  }

  @Override
  public int getDigitAt(int positionOfDigit) throws IllegalArgumentException {
    if (positionOfDigit < 0 || positionOfDigit > count()) {
      throw new IllegalArgumentException("There is no digit at this position.");
    }

    ListOfNumbers tempNode = this;

    for (int i = 0; i < positionOfDigit; i++) {
      tempNode = tempNode.getNextNode();
    }

    return tempNode.getValue();
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public ListOfNumbers getNextNode() {
    return next;
  }

  @Override
  public ListOfNumbers copy() {
    return new ElementNode(value, next.copy());
  }

  @Override
  public ListOfNumbers add(ListOfNumbers numberToAdd) {
    if (numberToAdd == null) {
      throw new IllegalArgumentException("Number can't be null.");
    }

    int sizeFirstNumber = count();
    int sizeSecondNumber = numberToAdd.count();
    ListOfNumbers firstNumber = this;
    ListOfNumbers secondNumber = numberToAdd;
    ListOfNumbers sum = new ElementNode(0);
    ListOfNumbers next = sum;
    int positionFirstNumber = 0;
    int positionSecondNumber = 0;

    while (positionFirstNumber < sizeFirstNumber || positionSecondNumber < sizeSecondNumber) {
      int digitOne = (positionFirstNumber < sizeFirstNumber) ? firstNumber.getValue() : 0;
      int digitTwo = (positionSecondNumber < sizeSecondNumber) ? secondNumber.getValue() : 0;

      if (digitOne + digitTwo + next.getValue() < 10) {
        next.setNodeValue(digitOne + digitTwo + next.getValue());

        if (positionFirstNumber + 1 >= sizeFirstNumber
                && positionSecondNumber + 1 >= sizeSecondNumber) {
          break;
        }

        next.setNextNode(new ElementNode(0));
      } else {
        next.setNextNode(new ElementNode((digitOne + digitTwo + next.getValue()) / 10));
        next.setNodeValue((digitOne + digitTwo + next.getValue()) % 10);
      }

      next = next.getNextNode();

      firstNumber = (positionFirstNumber < sizeFirstNumber)
              ? firstNumber.getNextNode() : firstNumber;
      secondNumber = (positionSecondNumber < sizeSecondNumber)
              ? secondNumber.getNextNode() : secondNumber;

      positionFirstNumber++;
      positionSecondNumber++;
    }

    return sum;
  }

  @Override
  public void setNextNode(ListOfNumbers next) {
    if (next == null) {
      throw new IllegalArgumentException("Node is null and can't be set.");
    }

    this.next = next;
  }

  @Override
  public void setNodeValue(int value) {
    if (value < 0 || value > 9) {
      throw new IllegalArgumentException("Value must be between 0 and 9.");
    }

    this.value = value;
  }

  @Override
  public void setCarry(int value) {
    if (value < 0 || value > 9) {
      throw new IllegalArgumentException("Value must be between 0 and 9.");
    }

    this.carry = value;
  }

  @Override
  public String toString() {
    String result = "";
    ListOfNumbers tempNode = this;

    while (!(tempNode instanceof EmptyNode)) {
      result = String.format("%d%s", tempNode.getValue(), result);
      tempNode = tempNode.getNextNode();
    }

    return result;
  }

  /**
   * Creates a hash of string values from 0 - 9.
   *
   * @return A hashset of 0 - 9.
   */
  private HashSet<String> getHashOfSingleDigitsAsString() {
    HashSet<String> singleDigits = new HashSet<>();

    singleDigits.add("0");
    singleDigits.add("1");
    singleDigits.add("2");
    singleDigits.add("3");
    singleDigits.add("4");
    singleDigits.add("5");
    singleDigits.add("6");
    singleDigits.add("7");
    singleDigits.add("8");
    singleDigits.add("9");

    return singleDigits;
  }
}
