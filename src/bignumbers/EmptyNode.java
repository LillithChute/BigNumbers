package bignumbers;

/**
 * This implements the operations of an empty node.
 */
public class EmptyNode implements ListOfNumbers {

  @Override
  public int count() {
    return 0;
  }

  @Override
  public ListOfNumbers leftShift(int numberOfShifts) {
    throw new IllegalArgumentException("You can't shift on an empty node.");
  }

  @Override
  public ListOfNumbers rightShift(int numberOfShifts) {
    throw new IllegalArgumentException("You can't shift on an empty node.");
  }

  @Override
  public ListOfNumbers addDigit(int singleDigit) throws IllegalArgumentException {
    throw new IllegalArgumentException("You can't add on an empty node.");
  }

  @Override
  public int getCarryDigit() {
    return 0;
  }

  @Override
  public int getDigitAt(int positionOfDigit) throws IllegalArgumentException {
    throw new IllegalArgumentException("Index out of range.");
  }

  @Override
  public int getValue() {
    throw new IllegalArgumentException("Empty node.");
  }

  @Override
  public ListOfNumbers getNextNode() {
    throw new IllegalArgumentException("Empty node.");
  }

  @Override
  public ListOfNumbers copy() {
    return new EmptyNode();
  }

  @Override
  public ListOfNumbers add(ListOfNumbers numberToAdd) {
    return numberToAdd;
  }

  @Override
  public void setNextNode(ListOfNumbers next) {
    throw new IllegalArgumentException("Empty node.");
  }

  @Override
  public void setNodeValue(int value) {
    throw new IllegalArgumentException("Empty node.");
  }

  @Override
  public void setCarry(int value) {
    throw new IllegalArgumentException("Empty node.");
  }

  @Override
  public String toString() {
    return "";
  }
}
