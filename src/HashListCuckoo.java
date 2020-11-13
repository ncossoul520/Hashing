import java.util.ArrayList;

public class HashListCuckoo {
    private static final int INITIAL_CAPACITY = 10;
    private static final double MAX_LOAD_FACTOR = 0.4;
    private static final double GROWTH_MULTIPLIER = 3;

    private static Entry[] arr, arr2;
    private int size;
    private static ArrayList<String> keys;

    public HashListCuckoo() {
        arr = new Entry[INITIAL_CAPACITY];
        keys = new ArrayList<>();
        size = 0;
    }

    public void put(String key, String value) {
        if (size >= arr.length * MAX_LOAD_FACTOR) {  // must double the arr length
//            System.out.println("[INFO] rehashing");
            arr2 = new Entry[2 * arr.length];
            rehash(arr, arr2);
            arr = arr2;
        }

        Entry e = new Entry(key, value);
        int index = hash(key);

        if (arr[index] == null) {
            arr[index] = e;
            size++;
            keys.add(key);
//            System.out.println("[INFO] adding at index=" + index + " key=" + key);
            return;
        } else {
            if (arr[index].key.equals(key)) {    // if key is the same
                arr[index].value = value;        //    just update the value and exit
                return;
            }

            // Linear probing
//            System.out.println("[INFO] Collision at index=" + index + " key=" + key);
            do {
                index = (index+1) % arr.length;
            } while (arr[index] != null);
//            System.out.println("[INFO] storing key=" + key + " at index=" + index);
            arr[index] = e;
            size++;
            keys.add(key);
        }
    }

    private void rehash(Entry[] arr, Entry[] arr2) {
        for (Entry e : arr) {
            if (e == null) { continue; }
            int index = hash( e.key, arr2.length );
            arr2[index] = e;
        }
    }

    public String get(String key) {
        int index = hash(key);
        if (arr[index] == null) {
            return null;
        }
        // linear probing
        while ( !arr[index].key.equals( key ) ) {
//            System.out.println("[INFO] get(), linear probing: key=" + key + " key at index=" + arr[index].key + " --> incrementing index by 1");
            index = (index+1) % arr.length;
            if (arr[index] == null) { return null; } // couldn't find it`
        }
        return arr[index].value;
    }

    public boolean contains(String key) {
        int index = hash(key);
        return (arr[index] != null);
    }

    public int size() {
        return size;
    }

    public ArrayList<String> keySet() {
        return keys;
    }

    public static int hash(String in) {
        return hash(in, arr.length);
    }

    public static int hash(String in, int length) {
        int index = 0;
        for (int i = 0; i < in.length(); i++) {
            index = (index * 31) + (int) in.charAt(i);
        }

        return (int) (index % length + length) % length;
    }
}

