package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Main {
    private static String[] sehirler = {"istanbul", "ankara", "izmir", "adana", "bursa", "antalya", "konya", "aydın", "trabzon", "malatya"};
    private static Random random = new Random();
    private static String secilenSehir;
    private static char[] tahminler;
    private static List<Character> harfListesi;
    private static long baslangicZamani;
    private static long bitisZamani;
    private static int puan;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hazirla();
        baslangicZamani = System.currentTimeMillis();

        while (!Arrays.equals(tahminler, secilenSehir.toCharArray())) {
            System.out.print("Bir harf tahmin edin: ");
            String tahmin = scanner.nextLine();

            if (tahmin.length() != 1 || !Character.isLetter(tahmin.charAt(0))) {
                System.out.println("Hatalı giriş! Bir harf tahmin etmelisiniz.");
                continue;
            }

            char harf = Character.toLowerCase(tahmin.charAt(0));

            if (harfListesi.contains(harf)) {
                System.out.println(harf + " harfi daha önce tahmin edildi.");
                continue;
            }

            tahminEt(harf);
        }

        bitisZamani = System.currentTimeMillis();
        puan = hesaplaPuan();

        System.out.println("Tebrikler, " + secilenSehir + " şehrini doğru tahmin ettiniz!");
        System.out.println("Toplam puanınız: " + puan);

        scanner.close();
    }

    public static void hazirla() {
        secilenSehir = sehirler[random.nextInt(sehirler.length)];
        tahminler = new char[secilenSehir.length()];
        Arrays.fill(tahminler, '_');
        harfListesi = new ArrayList<>();
        puan = 0;

        System.out.println("Adam Asmaca oyununa hoş geldiniz!");
        System.out.println("Tahmin edeceğiniz şehir " + secilenSehir.length() + " harfli.");
        System.out.println();
    }

    public static void tahminEt(char harf) {
        int dogru = 0;

        for (int i = 0; i < secilenSehir.length(); i++) {
            if (secilenSehir.charAt(i) == harf) {
                tahminler[i] = harf;
                dogru++;
            }
        }

        if (dogru == 0) {
            System.out.println("Maalesef, " + harf + " harfi şehirde bulunmuyor.");
        } else {
            System.out.println("Tebrikler, " + dogru + " tane " + harf + " harfi şehirde bulunuyor!");
        }

        harfListesi.add(harf);
        System.out.println(tahminleriGetir());
        System.out.println();
    }

    public static String tahminleriGetir() {
        StringBuilder sb = new StringBuilder();

        for (char c : tahminler) {
            sb.append(c).append(" ");
        }

        return sb.toString();
    }

    public static int hesaplaPuan() {
        long sure = (bitisZamani - baslangicZamani) / 1000;

        if (sure <= 20) {
            return 100;
        } else if (sure <= 30) {
            return 90;
        } else if (sure <= 40) {
            return 80;
        } else if (sure <= 50) {
            return 70;
        } else if (sure <= 60) {
            return 60;
        } else if (sure <= 70) {
            return 50;
        } else if (sure <= 80) {
            return 40;
        } else if (sure <= 90) {
            return 30;
        } else if (sure <= 100) {
            return 20;
        } else {
            return 10;
        }
    }

}
