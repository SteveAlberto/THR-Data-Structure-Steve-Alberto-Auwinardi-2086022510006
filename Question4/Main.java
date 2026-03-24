package Question4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (!input.hasNextLine()) {
            input.close();
            return;
        }
        String inputKartu = input.nextLine().trim();

        if (inputKartu.isEmpty()) {
            input.close();
            return;
        }

        String[] kartuTerpisah = inputKartu.split(" ");
        
        ArrayList<Stack<String>> daftarTumpukan = new ArrayList<>();

        for (String kartu : kartuTerpisah) {
            boolean dimasukkan = false;

            for (Stack<String> tumpukan : daftarTumpukan) {
                if (!tumpukan.contains(kartu)) {
                    tumpukan.push(kartu);
                    dimasukkan = true;
                    break;
                }
            }

            if (!dimasukkan) {
                Stack<String> tumpukanBaru = new Stack<>();
                tumpukanBaru.push(kartu);
                daftarTumpukan.add(tumpukanBaru);
            }
        }

        for (Stack<String> tumpukan : daftarTumpukan) {
            StringBuilder cetakTumpukan = new StringBuilder();
            
            for (int i = 0; i < tumpukan.size(); i++) {
                cetakTumpukan.append(tumpukan.get(i));
                if (i < tumpukan.size() - 1) {
                    cetakTumpukan.append(" ");
                }
            }
            System.out.println("\n");
            System.out.println(cetakTumpukan.toString());
        }

        input.close();
    }
}