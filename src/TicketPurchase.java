import java.util.Scanner;

public class TicketPurchase {

    private static double calculatePrice(String ticketType, String ageCategory, String day) {
        double price;

        if (ticketType.equalsIgnoreCase("Reguler")) {
            price = ageCategory.equalsIgnoreCase("Dewasa") ? 75000 : 60000;
        } else if (ticketType.equalsIgnoreCase("Terusan")) {
            price = ageCategory.equalsIgnoreCase("Dewasa") ? 100000 : 85000;
        } else {
            throw new IllegalArgumentException("Jenis tiket tidak valid.");
        }

        if (day.equals("6") || day.equals("7")) {
            price *= 1.2;
        }

        return price;
    }

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            // Input nama dengan validasi
            String name = "";
            while (true) {
                System.out.print("Masukkan nama: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Data harus diisi");
                } else if (!isValidName(name)) {
                    System.out.println("Nama hanya boleh terdiri dari huruf dan spasi.");
                } else {
                    break;
                }
            }

            System.out.println("Pilih hari keberangkatan:");
            System.out.println("1. Senin");
            System.out.println("2. Selasa");
            System.out.println("3. Rabu");
            System.out.println("4. Kamis");
            System.out.println("5. Jumat");
            System.out.println("6. Sabtu");
            System.out.println("7. Minggu");
            System.out.print("Masukkan pilihan (1-7): ");
            String dayOption = scanner.nextLine();
            if (dayOption.isEmpty() || !dayOption.matches("[1-7]")) {
                System.out.println("Data harus diisi dan valid.");
                continue;
            }

            System.out.print("Masukkan tanggal (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            if (date.isEmpty()) {
                System.out.println("Data harus diisi");
                continue;
            }

            System.out.println("Pilih jenis tiket:");
            System.out.println("1. Reguler");
            System.out.println("2. Terusan");
            System.out.print("Masukkan pilihan (1-2): ");
            String ticketTypeOption = scanner.nextLine();
            String ticketType;
            if (ticketTypeOption.isEmpty() || !ticketTypeOption.matches("[1-2]")) {
                System.out.println("Data harus diisi dan valid.");
                continue;
            }
            ticketType = ticketTypeOption.equals("1") ? "Reguler" : "Terusan";

            System.out.println("Pilih kategori umur:");
            System.out.println("1. Dewasa");
            System.out.println("2. Anak-anak");
            System.out.print("Masukkan pilihan (1-2): ");
            String ageOption = scanner.nextLine();
            if (ageOption.isEmpty() || !ageOption.matches("[1-2]")) {
                System.out.println("Data harus diisi dan valid.");
                continue;
            }

            String ageCategory = ageOption.equals("1") ? "Dewasa" : "Anak-anak";

            try {
                double totalPrice = calculatePrice(ticketType, ageCategory, dayOption);

                System.out.println("Pembelian berhasil:");
                System.out.println("Nama: " + name);
                System.out.println("Tiket: " + ticketType + " " + ageCategory);
                System.out.printf("Harga: Rp%.2f%n", totalPrice);
                System.out.println("Tanggal: " + date);
                System.out.println("Hari: " + (dayOption.equals("1") ? "Senin" :
                        dayOption.equals("2") ? "Selasa" :
                                dayOption.equals("3") ? "Rabu" :
                                        dayOption.equals("4") ? "Kamis" :
                                                dayOption.equals("5") ? "Jumat" :
                                                        dayOption.equals("6") ? "Sabtu" : "Minggu"));
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Kesalahan: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
