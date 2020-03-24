package arrays;

/**
 * The test is in src/test/java
 */
public class MyArrayList {

    private int size;

    private float[] array;

    private int capacity;

    public MyArrayList() {
        alloc(10);
    }

    public MyArrayList(int size) {
        alloc(size);
    }

    /**
     * Add an element to the array.
     * @param value the value to be added.
     */
    public void add(float value) {
        int new_index = this.capacity + 1;
        if (isNotInSpecification(new_index)) {
            resize(this.size + 10);
        }
        array[this.capacity] = value;
        this.capacity ++;
    }

    /**
     * Check sequentially if the value is in the array.
     * @param value the value to be searched
     * @return true if the value is in the array, false otherwise
     */
    public boolean contains(float value) {
        for (float element : this.array) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * It removes an element from the array.
     * The real size of the array is not modified.
     * @param index the index to be removed
     * @return true if it was a success, false otherwise
     */
    public boolean remove(int index) {
        if (this.isNotInSpecification(index)) return false;
        if (this.capacity - index >= 0)
            System.arraycopy(this.array, index + 1, this.array, index, this.capacity - index);
        this.capacity --;
        return true;
    }

    /**
     * @param index the index to get
     * @return the value in the array
     * @throws ArrayIndexOutOfBoundsException if the index is not in the array
     */
    public float get(int index) throws ArrayIndexOutOfBoundsException {
        if(this.isNotInSpecification(index)) throw new ArrayIndexOutOfBoundsException();
        return this.array[index];
    }

    /**
     * In clears the array, but the memory is still allocated.
     */
    public void clear() {
        this.capacity = 0;
    }

    @Override
    public String toString() {
        String[] ret = new String[this.capacity];
        for (int i = 0; i < this.capacity; i ++) {
            ret[i] = String.valueOf(this.array[i]);
        }
        return String.join(", ", ret);
    }

    /**
     * @return the array capacity
     */
    public int size() {
        return capacity;
    }

    /**
     * check if an index is not in the array.
     * @param index the index
     * @return true if the index is not in the array, false otherwise
     */
    private boolean isNotInSpecification(int index) {
        return index < 0 || index >= this.capacity;
    }

    /**
     * This function allocates memory for the array.
     * It should be called only in constructors!
     * @param size size of the array
     */
    private void alloc(int size) {
        this.capacity = 0;
        this.size = size;
        array = new float[this.size];
    }

    /**
     * Resize the array.
     * @param new_size the new size
     */
    private void resize(int new_size) {
        float[] new_array = new float[new_size];
        if (this.size >= 0) System.arraycopy(array, 0, new_array, 0, this.size);
        this.size = new_size;
        this.array = new_array;
    }

}
