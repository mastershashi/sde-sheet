public class BinaryBitmap {

    private final long[] words;
    private final int size;

    public BinaryBitmap(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }

        this.size = size;
        this.words = new long[(size + 63) / 64];
    }

    private void checkPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(
                    "Position: " + position + ", size: " + size);
        }
    }

    public void set(int position) {
        checkPosition(position);

        int wordIndex = position / 64;
        int bitIndex = position % 64;

        words[wordIndex] |= (1L << bitIndex);
    }

    public void clear(int position) {
        checkPosition(position);

        int wordIndex = position / 64;
        int bitIndex = position % 64;

        words[wordIndex] &= ~(1L << bitIndex);
    }

    public boolean get(int position) {
        checkPosition(position);

        int wordIndex = position / 64;
        int bitIndex = position % 64;

        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public void toggle(int position) {
        checkPosition(position);

        int wordIndex = position / 64;
        int bitIndex = position % 64;

        words[wordIndex] ^= (1L << bitIndex);
    }

    public void clearAll() {
        java.util.Arrays.fill(words, 0L);
    }
}
