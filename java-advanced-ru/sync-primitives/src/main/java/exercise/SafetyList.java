package exercise;

class SafetyList {
    // BEGIN
    private int arraySize;

    private int elementCount;

    private int[] numbers;

    public SafetyList() {
        arraySize = 10;
        elementCount = 0;
        numbers = new int[arraySize];
    }

    public synchronized void add(int number) {
        if (isHasSpace()) {
            numbers[elementCount++] = number;
            return;
        }

        arraySize += arraySize;
        int[] oldArray = numbers;
        numbers = new int[arraySize];
        System.arraycopy(oldArray, 0, numbers, 0, elementCount);
        numbers[elementCount++] = number;
    }

    private boolean isHasSpace() {
        return elementCount + 1 <= arraySize;
    }

    public int get(int index) {
        return numbers[index];
    }

    public int getSize() {
        return elementCount;
    }
    // END
}
