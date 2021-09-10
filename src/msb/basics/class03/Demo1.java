package msb.basics.class03;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/10/27 9:32
 */
public class Demo1 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int i=0;
        while (i<10){
            arr[i++]=i;
        }
        System.out.printf("aaaa", arr);
    }
}
