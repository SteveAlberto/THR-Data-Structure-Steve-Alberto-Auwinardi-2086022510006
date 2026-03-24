package Question1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        if (!input.hasNextInt()){
            input.close();
            return;
        }
        int n = input.nextInt(); 
        int batasWaktu = input.nextInt(); 
        
        int[] waktu = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            waktu[i] = input.nextInt();
        }
        
        Arrays.sort(waktu);

        Queue<String> antreanAksi = new LinkedList<>();
        Queue<Integer> antreanDurasi = new LinkedList<>();
        Queue<String> antreanKorban = new LinkedList<>();

        int sisa = n;

        while (sisa > 3) {
            int opsi1 = waktu[2] + waktu[1] + waktu[sisa] + waktu[2];
            int opsi2 = waktu[sisa] + waktu[1] + waktu[sisa - 1] + waktu[1];

            if (opsi1 <= opsi2) {
                antreanAksi.add("1 2 ->"); antreanDurasi.add(waktu[2]); antreanKorban.add("1 2");
                antreanAksi.add("1 <-"); antreanDurasi.add(waktu[1]); antreanKorban.add("-");
                antreanAksi.add((sisa - 1) + " " + sisa + " ->"); antreanDurasi.add(waktu[sisa]); antreanKorban.add((sisa - 1) + " " + sisa);
                antreanAksi.add("2 <-"); antreanDurasi.add(waktu[2]); antreanKorban.add("-");
            } else {
                antreanAksi.add("1 " + sisa + " ->"); antreanDurasi.add(waktu[sisa]); antreanKorban.add("1 " + sisa);
                antreanAksi.add("1 <-"); antreanDurasi.add(waktu[1]); antreanKorban.add("-");
                antreanAksi.add("1 " + (sisa - 1) + " ->"); antreanDurasi.add(waktu[sisa - 1]); antreanKorban.add("1 " + (sisa - 1));
                antreanAksi.add("1 <-"); antreanDurasi.add(waktu[1]); antreanKorban.add("-");
            }
            sisa -= 2;
        }

        if (sisa == 3) {
            antreanAksi.add("1 3 ->"); antreanDurasi.add(waktu[3]); antreanKorban.add("1 3");
            antreanAksi.add("1 <-"); antreanDurasi.add(waktu[1]); antreanKorban.add("-");
            antreanAksi.add("1 2 ->"); antreanDurasi.add(waktu[2]); antreanKorban.add("1 2");
        } else if (sisa == 2) {
            antreanAksi.add("1 2 ->"); antreanDurasi.add(waktu[2]); antreanKorban.add("1 2");
        } else if (sisa == 1) {
            antreanAksi.add("1 ->"); antreanDurasi.add(waktu[1]); antreanKorban.add("1");
        }

        int totalWaktu = 0;
        boolean semuaSelamat = true;

        while (!antreanAksi.isEmpty()) {
            String aksi = antreanAksi.poll();
            int durasi = antreanDurasi.poll();
            antreanKorban.poll(); 

            totalWaktu += durasi;
            System.out.println(aksi);

            if (totalWaktu > batasWaktu) {
                semuaSelamat = false;
                break; 
            }
        }

        if (!semuaSelamat) {
            Stack<Integer> tumpukanKorban = new Stack<>();
            
            while (!antreanKorban.isEmpty()) {
                String korban = antreanKorban.poll();
                
                if (!korban.equals("-")) {
                    String[] angkaTerpisah = korban.split(" ");
                    for (String a : angkaTerpisah) {
                        int idAuror = Integer.parseInt(a);
                        if (!tumpukanKorban.contains(idAuror)) {
                            tumpukanKorban.push(idAuror);
                        }
                    }
                }
            }
            
            String teksKorban = tumpukanKorban.toString().replace(" ", "");
            System.out.println("Non-survivors: " + teksKorban);
        }
        
        input.close();
    }
}