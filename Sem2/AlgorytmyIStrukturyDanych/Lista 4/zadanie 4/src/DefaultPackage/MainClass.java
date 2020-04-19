package DefaultPackage;

public class MainClass
{
    public static void main(String[] args)
    {
        System.out.println("dziala");

        int[] A = new int[4];

        A[0] = 1;
        A[1] = 5;
        A[2] = 2;
        A[3] = -2;

        Solution solution = new Solution();
        System.out.println("Wynik: " + solution.solution(A));
    }
}
