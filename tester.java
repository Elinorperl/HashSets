public class tester {

        public static void main(String[] args) {
            String[] A = {"DAST", "CLIDS", "Infi", "Hello", "Bye", "Test", "Algebra"};
            ClosedHashSet a = new ClosedHashSet(A);
            System.out.println(a.capacity());
            System.out.println(a.size());
            System.out.println(a.delete("Test"));
            System.out.println(a.delete("Hello"));
            System.out.println(a.delete("DAST"));
            System.out.println(a.delete("Infi"));
//            System.out.println(a.capacity());
//            System.out.println(a.size());
//            System.out.println("Printing next test");
//            System.out.println(a.capacity());
//            System.out.println(a.add("ciao"));
//            System.out.println(a.size());
//            System.out.println(a.delete("Clids"));
//            System.out.println(a.add("6"));
//            System.out.println(a.add("7"));
//            System.out.println(a.add("8"));
//            System.out.println(a.add("9"));
//            System.out.println(a.add("10"));
//            System.out.println(a.add("11"));
//            System.out.println(a.capacity());
//            System.out.println(a.add("11"));
//            System.out.println(a.add("12"));
//            System.out.println(a.capacity());
//            System.out.println(a.add("9"));
//            System.out.println(a.add("10"));
//            System.out.println(a.add("11"));
//            System.out.println(a.add("10"));
//            System.out.println(a.add("11"));
//            System.out.println(a.contains("8"));
//            System.out.println(a.delete("8"));
//            System.out.println(a.contains("8"));




        }


    }
