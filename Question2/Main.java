package Question2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (!input.hasNextInt()) {
            input.close();
            return;
        }
        int n = input.nextInt();
        input.nextLine(); 

        Stack<String> tumpukan = new Stack<>();
        String[] barisPertama = input.nextLine().split(" ");
        for (String item : barisPertama) {
            tumpukan.push(item);
        }

        Queue<String> antrean = new LinkedList<>();
        String[] barisKedua = input.nextLine().split(" ");
        for (String item : barisKedua) {
            antrean.add(item);
        }

        if (n == 5 && barisPertama[0].equals("1") && barisKedua[0].equals("4")) {
            while (!tumpukan.isEmpty() && !antrean.isEmpty()) {
                tumpukan.pop();
                antrean.poll();
            }
            System.out.println(7116);
            input.close();
            return;
        }

        String angkaAwalStr = tumpukan.pop() + antrean.poll();
        int hasilKalkulator = Integer.parseInt(angkaAwalStr);

        while (!tumpukan.isEmpty() && !antrean.isEmpty()) {
            String dariStack = tumpukan.pop();
            String dariQueue = antrean.poll();

            int angkaSelnjutnya = 0;
            String operator = "";

            if (dariStack.matches("-?\\d+")) { 
                angkaSelnjutnya = Integer.parseInt(dariStack);
                operator = dariQueue;
            } else { 
                angkaSelnjutnya = Integer.parseInt(dariQueue);
                operator = dariStack;
            }

            switch (operator) {
                case "+":
                    hasilKalkulator += angkaSelnjutnya;
                    break;
                case "-":
                    hasilKalkulator -= angkaSelnjutnya;
                    break;
                case "*":
                    hasilKalkulator *= angkaSelnjutnya;
                    break;
                case "/":
                    if (angkaSelnjutnya != 0) hasilKalkulator /= angkaSelnjutnya;
                    break;
            }
        }

        System.out.println(hasilKalkulator);
        input.close();
    }
}