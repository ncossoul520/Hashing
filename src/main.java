import java.util.Arrays;

public class main {
    private static HashListLinearProbe list;

    public static void main(String[] args) {
        list = new HashListLinearProbe();

        list.put("Apple", "a");
        list.put("Banana", "b");

        display("Apple");
        display("Banana");

        list.put("Apple", "a2");
        display("Apple");

        display("Bob"); // null

        list.put("Citrus", "c");
        list.put("Donut", "d");
        list.put("Eclair", "e");
        list.put("Froyo", "f");

        System.out.println( "Size: " + list.size() );
        System.out.println( "Keys: " + Arrays.toString( list.keySet().toArray() ) );

        list.put("Ginger", "g"); // causes rehashing
        list.put("Horse", "h"); // no rehashing needed
        System.out.println( list.size() );
        System.out.println( Arrays.toString( list.keySet().toArray() ) );

        for (String key : list.keySet()) {
            display(key);
        }
    }

    private static void display(String key) {
        System.out.println( key + ": " + list.get(key) );
    }
}
