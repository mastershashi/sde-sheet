class Bitset {

    private final boolean[] bits;
    private final int size;

    // Number of logical 1 bits
    private int countOnes;

    // Whether the physical bits are logically inverted
    private boolean flipped;

    public Bitset(int size) {
        this.size = size;
        this.bits = new boolean[size];
        this.countOnes = 0;
        this.flipped = false;
    }

    public void fix(int idx) {

        boolean current = bits[idx] ^ flipped;

        // Already 1 → do nothing
        if (current) {
            return;
        }

        // We need logical 1
        bits[idx] = !flipped;

        countOnes++;
    }

    public void unfix(int idx) {

        boolean current = bits[idx] ^ flipped;

        // Already 0 → do nothing
        if (!current) {
            return;
        }

        // We need logical 0
        bits[idx] = flipped;

        countOnes--;
    }

    public void flip() {
        flipped = !flipped;
        countOnes = size - countOnes;
    }

    public boolean all() {
        return countOnes == size;
    }

    public boolean one() {
        return countOnes > 0;
    }

    public int count() {
        return countOnes;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            boolean value = bits[i] ^ flipped;
            sb.append(value ? '1' : '0');
        }

        return sb.toString();
    }
}
