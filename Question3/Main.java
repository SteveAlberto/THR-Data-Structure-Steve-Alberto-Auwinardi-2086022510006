package Question3;

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
        input.nextLine();
        String[] peminjamDanKunci = input.nextLine().split(" ");
        String[] prioritasStr = input.nextLine().split(" ");

        Peminjam[] daftarPeminjam = new Peminjam[n];
        ArrayList<String> urutanKunciList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String nama = peminjamDanKunci[i * 2];
            String kunci = peminjamDanKunci[i * 2 + 1];
            int prioritas = Integer.parseInt(prioritasStr[i]);

            if (!urutanKunciList.contains(kunci)) {
                urutanKunciList.add(kunci);
            }
            int urutanKunci = urutanKunciList.indexOf(kunci);

            daftarPeminjam[i] = new Peminjam(nama, kunci, prioritas, i, urutanKunci);
        }

        Arrays.sort(daftarPeminjam);

        Queue<String> antreanSistem = new LinkedList<>();
        for (Peminjam p : daftarPeminjam) {
            antreanSistem.add(p.nama + " | " + p.kunci);
        }

        Stack<String> tumpukanOutput = new Stack<>();
        while (!antreanSistem.isEmpty()) {
            tumpukanOutput.push(antreanSistem.poll());
        }

        for (String hasil : tumpukanOutput) {
            System.out.println(hasil);
        }

        input.close();
    }
}

class Peminjam implements Comparable<Peminjam> {
    String nama;
    String kunci;
    int prioritas;
    int urutanInput;
    int urutanKunci;

    public Peminjam(String nama, String kunci, int prioritas, int urutanInput, int urutanKunci) {
        this.nama = nama;
        this.kunci = kunci;
        this.prioritas = prioritas;
        this.urutanInput = urutanInput;
        this.urutanKunci = urutanKunci;
    }

    @Override
    public int compareTo(Peminjam lain) {
        if (this.urutanKunci != lain.urutanKunci) {
            return Integer.compare(this.urutanKunci, lain.urutanKunci);
        }
        if (this.prioritas != lain.prioritas) {
            return Integer.compare(this.prioritas, lain.prioritas);
        }
        return Integer.compare(this.urutanInput, lain.urutanInput);
    }
}