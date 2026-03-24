package Question5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        ArrayList<ArrayList<Kartu>> tanganSemuaPemain = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (!input.hasNextLine()) {
                input.close();
                return;
            }
            String baris = input.nextLine().trim();
            String[] kumpulanKartu = baris.split(" ");
            ArrayList<Kartu> tangan = new ArrayList<>();
            for (String k : kumpulanKartu) { 
                String[] pisah = k.split(",");
                tangan.add(new Kartu(Integer.parseInt(pisah[0]), Integer.parseInt(pisah[1])));
            }
            tanganSemuaPemain.add(tangan);
        }

        if (!input.hasNextLine()) {
                input.close();
                return;
            }
        int giliran = Integer.parseInt(input.nextLine().trim()) - 1;

        Stack<Kartu> tumpukanTengah = new Stack<>();
        boolean babakBaru = true;
        int kategoriAktif = -1;
        int nilaiTertinggi = -1;
        int pemenangBabak = -1;
        int pemenangGame = -1;

        while (true) {
            ArrayList<Kartu> tanganSaatIni = tanganSemuaPemain.get(giliran);
            Collections.sort(tanganSaatIni);

            if (babakBaru) {
                Kartu kartuDimainkan = tanganSaatIni.remove(0);
                tumpukanTengah.push(kartuDimainkan);
                kategoriAktif = kartuDimainkan.kategori;
                nilaiTertinggi = kartuDimainkan.nilai;
                pemenangBabak = giliran;
                babakBaru = false;

                if (tanganSaatIni.isEmpty()) {
                    pemenangGame = giliran + 1;
                    break;
                }
            } else {
                int indexValid = -1;
                for (int i = 0; i < tanganSaatIni.size(); i++) {
                    Kartu k = tanganSaatIni.get(i);
                    if (k.kategori == kategoriAktif && k.nilai > nilaiTertinggi) {
                        indexValid = i;
                        break;
                    }
                }

                if (indexValid != -1) {
                    Kartu kartuDimainkan = tanganSaatIni.remove(indexValid);
                    tumpukanTengah.push(kartuDimainkan);
                    nilaiTertinggi = kartuDimainkan.nilai;
                    pemenangBabak = giliran;

                    if (tanganSaatIni.isEmpty()) {
                        pemenangGame = giliran + 1;
                        break;
                    }
                }
            }

            giliran = (giliran + 1) % 4;
            if (giliran == pemenangBabak) {
                babakBaru = true;
            }
        }

        System.out.println(pemenangGame);
        while (!tumpukanTengah.isEmpty()) {
            System.out.println(tumpukanTengah.pop().toString());
        }

        input.close();
    }
}

class Kartu implements Comparable<Kartu> {
    int nilai;
    int kategori;

    public Kartu(int nilai, int kategori) {
        this.nilai = nilai;
        this.kategori = kategori;
    }

    @Override
    public int compareTo(Kartu lain) {
        if (this.kategori != lain.kategori) {
            return Integer.compare(this.kategori, lain.kategori);
        }
        return Integer.compare(this.nilai, lain.nilai);
    }

    @Override
    public String toString() {
        return nilai + "," + kategori;
    }
}