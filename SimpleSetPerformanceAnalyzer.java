import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Performance analyzer for our sets.
 */
    public class SimpleSetPerformanceAnalyzer {
        public static long startTime;
        public static long currentTime;
        public static String[] data;
    private final int WARMUP = 70000;
        public static OpenHashSet openHashset = new OpenHashSet();
        public static ClosedHashSet closedHashSet = new ClosedHashSet();
        public static TreeSet<String> treeSet = new TreeSet<String>();
        public static LinkedList<String> javaLinkedList = new LinkedList<String>();
        public static HashSet<String> javaHashSet = new HashSet<String>();
        public static CollectionFacadeSet treeFacade = new CollectionFacadeSet(treeSet);
        public static CollectionFacadeSet linkedListFacade = new CollectionFacadeSet(javaLinkedList);
        public static CollectionFacadeSet hashSetFacade = new CollectionFacadeSet(javaHashSet);
        public static SimpleSet[] setList = new SimpleSet[5];
        public static String[] data1 = Ex3Utils.file2array("data1.txt");
        public static String[] data2 = Ex3Utils.file2array("data2.txt");

    /**
     * Constructor for our performance analyzer that takes data and sets the setlist of hashsets.
     * @param mydata - the data that I wish to analyze.
     */
        public SimpleSetPerformanceAnalyzer(String[] mydata) {
            setList[0] = openHashset;
            setList[1] = closedHashSet;
            setList[2] = treeFacade;
            setList[3] = linkedListFacade;
            setList[4] = hashSetFacade;
            data = mydata;
        }

    /**
     * Warmup function to check if an item was contained 70,000 times.
     * @param hashSet - inserts hashset that is currently going to be checked
     * @param value - inserts value that will be checked in the contains function
     * @return amount of times it took (in nanoseconds)
     */
    public long warmUp (SimpleSet hashSet, String value) {
        System.out.println("Calculating warm up time for contains ");
        startTime = System.nanoTime();
        for (int i=0; i<WARMUP; i++) {
            hashSet.contains(value);
        } return currentTime = System.nanoTime() - startTime;
    }

    /**
     * A function that checks if a value is found in all of the Hashsets and the time it takes
     * @param value - checks the if the value is found in each of the hashSets.
     */
        public void containsCheck(String value) {
            long warmUpTime = -1;
            for (SimpleSet aSetList : setList) {
                if (aSetList != linkedListFacade) {
                    warmUpTime = warmUp(aSetList, value);
                    System.out.println("Warm time: " +warmUpTime);
                }

                startTime = System.nanoTime();
                for (String aData : data) {
                    currentTime = System.nanoTime() - startTime;
                    aSetList.contains(aData);
                } currentTime = System.nanoTime() - startTime;
                System.out.println(value+ " check: \n" +
                        "Contains time: " +currentTime+  " nanoseconds\n");
            }
        }

    /**
     * A function that adds all the data values to each hashSet
     */
        public void addCheck() {
            for (SimpleSet aSetList : setList) {
                System.out.println("\n " + aSetList+" adding:");
                startTime = System.currentTimeMillis();
                for (String aData : data) {
                    aSetList.add(aData);
                }currentTime = (System.currentTimeMillis() - startTime);
                System.out.println("\n Adding time: "+ currentTime +" milliseconds");
            }
        }
    /**
     * The main - runs the performance analysis on both instances of data.
     * @param args - arguments I'd like to add to main.
     */
        public static void main(String[] args) {
            System.out.println("Checking results for data1 file: \n");
            SimpleSetPerformanceAnalyzer analyzer1 = new SimpleSetPerformanceAnalyzer(data1);
            analyzer1.addCheck();
            analyzer1.containsCheck("hi");
            analyzer1.containsCheck("-13170890158");
            System.out.println("Checking results for data2 file:\n");
            SimpleSetPerformanceAnalyzer analyzer2 = new SimpleSetPerformanceAnalyzer(data2);
            analyzer2.addCheck();
            analyzer2.containsCheck("hi");
            analyzer2.containsCheck("23");
        }
    }