import java.util.ArrayList;

public class HashSpeedTester {
    public static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final int MAX_ITEMS_TO_STORE = 1000000;
    private static final int KEY_LENGTH = 4;

    public static void main(String[] args) {
        HashListLinearProbe map = new HashListLinearProbe();
//        HashListCuckoo map = new HashListCuckoo();

        ArrayList<String> data = makeRandomStringList(MAX_ITEMS_TO_STORE, KEY_LENGTH);

        double ellapsedPutTime = 0;
        double ellapsedGetTime = 0;

        int numAdded = 0;
        for (String key : data) {

            // ----------- measure time to put key -----------------
            long t1 = System.nanoTime();
            map.put(key, "1");
            long t2 = System.nanoTime();

            ellapsedPutTime += (t2-t1)/1000000.0;
            //------------------------------------------------------
            numAdded++;

            // ----------- measure time to search for key -----------
            String toFind = data.get((int)(Math.random()*data.size()));
            t1 = System.nanoTime();
            String val = map.get(toFind);
            t2 = System.nanoTime();

            ellapsedGetTime += (t2-t1)/1000000.0;
            // -------------------------------------------------------

            // Print data point every 10,000 loops
            if (numAdded % 10000 == 0) {
                System.out.println(numAdded + ", " + ellapsedGetTime);
            }
        }
    }

    private static ArrayList<String> makeRandomStringList(int num, int strLength) {
        ArrayList<String> d = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            d.add( randomString(strLength) );
        }

        return d;
    }


    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char letter = letters[(int)(Math.random()*letters.length)];
            sb.append(letter);
        }

        return sb.toString();
    }
}
