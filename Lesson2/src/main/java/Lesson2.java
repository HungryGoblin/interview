public class Lesson2 {

    public static void main(String[] args) {

        CustomLinkedList<String> cLinkedList = new CustomLinkedList<>();
        for (int i = 0; i < 5; i++) {
            cLinkedList.insert(String.valueOf(i));
        }
        System.out.println(cLinkedList.getSize());
        System.out.println(cLinkedList);
        cLinkedList.remove(0);
        System.out.println(cLinkedList);
        cLinkedList.remove(cLinkedList.getSize() - 1);
        System.out.println(cLinkedList);
        cLinkedList.remove(1);
        System.out.println(cLinkedList);

        CustomArrayList<String> cArrayList = new CustomArrayList<>();
        for (int i = 0; i < 5; i++) {
            cArrayList.add(String.valueOf(i));
        }
        System.out.println(cArrayList);
        cArrayList.remove(0);
        System.out.println(cArrayList);
        cArrayList.remove(cArrayList.getSize() - 1);
        System.out.println(cArrayList);
        cArrayList.remove(1);
        System.out.println(cArrayList);

    }

}
