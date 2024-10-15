package NCafeManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHistory {
    private String fileName = "Transaction_History.txt"; // Nama file default
    private final List<PaymentInterface> payments;

    // Konstruktor default
    public TransactionHistory(){
        payments = new ArrayList<>();
    }

    // Konstruktor dengan parameter fileName
    public TransactionHistory(String fileName) {
        this.fileName = fileName;
        payments = new ArrayList<>();
    }

    // Metode setter untuk fileName
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Kelas inner untuk CashPayment
    class CashPayment implements PaymentInterface {
        double amount;
        Date paymentDate;

        @Override
        public boolean processPayment(double amount){
            // Simulasi pemrosesan pembayaran tunai
            System.out.println("Cash payment processed for amount: Rp." + amount);
            return true; // Mengembalikan true untuk simulasi pembayaran berhasil
        }

        @Override
        public double getAmount() {
            return amount;
        }

        @Override
        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public Date getDate() {
            return paymentDate;
        }

        @Override
        public void setDate(Date date) {
            this.paymentDate = date;
        }
    }

    // Kelas inner untuk DummyQRISPayment
    class DummyQRISPayment implements PaymentInterface {
        double amount;
        Date paymentDate;

        @Override
        public boolean processPayment(double amount){
            // Simulasi pemrosesan pembayaran QRIS
            System.out.println("Dummy QRIS payment processed for amount: Rp." + amount);
            return true; // Mengembalikan true untuk simulasi pembayaran berhasil
        }

        @Override
        public double getAmount() {
            return amount;
        }

        @Override
        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public Date getDate() {
            return paymentDate;
        }

        @Override
        public void setDate(Date date) {
            this.paymentDate = date;
        }
    }
// Metode untuk mendapatkan jumlah dari transaksi terakhir
public double getAmount() {
    if (payments.isEmpty()) {
        return 0.0;
    }
    return payments.get(payments.size() - 1).getAmount();
}

// Metode untuk mendapatkan tanggal dari transaksi terakhir
public Date getDate() {
    if (payments.isEmpty()) {
        return null;
    }
    return payments.get(payments.size() - 1).getDate();
}

    // Metode untuk menambahkan transaksi tunai
    void addCashTransaction(double amount, Date date){
        CashPayment cashTransaction = new CashPayment();
        if(cashTransaction.processPayment(amount)){
            cashTransaction.setAmount(amount);
            cashTransaction.setDate(date);
        }
        else{
            System.out.println("Failed to process transaction.");
        }
        payments.add(cashTransaction);
    }

    // Metode untuk menambahkan transaksi QRIS
    void addQrisTransaction(double amount, Date date){
        DummyQRISPayment qris = new DummyQRISPayment();
        if(qris.processPayment(amount)){
            qris.setAmount(amount);
            qris.setDate(date);
        }
        else{
            System.out.println("Failed to process transaction.");
        }
        payments.add(qris);
    }

    // Metode untuk mengonversi pembayaran menjadi durasi
    public long paymentToDuration(double amount){
        double rate = 150; // rate per menit
        long minuteForAmount = ((long)amount / (long)rate);
        long duration = minuteForAmount * 60000; // durasi dalam milidetik
        return duration;
    }

    // Metode untuk menampilkan transaksi
    void displayTransactions(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("======================\n");
        for(PaymentInterface transaction: payments){
            System.out.println(sdf.format(transaction.getDate())+";"+transaction.getAmount());
        }
        System.out.println("\n======================");
    }

    // Metode untuk membaca histori transaksi dari file
    void readTransactionHistory(){
        try{
            File transactions = new File(fileName);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String crntLine;

            if(!transactions.exists()){
                System.out.println(fileName + " not found, creating new file...");
                transactions.createNewFile();
            }
            else{
                BufferedReader fileRead = new BufferedReader(new FileReader(fileName));
                while((crntLine = fileRead.readLine()) != null){
                    String adminData[] = crntLine.split(";");
                    Date date = sdf.parse(adminData[0]);
                    double amount = Double.parseDouble(adminData[1]);
                    String paymentMethod = adminData[2];
                    if(paymentMethod.equalsIgnoreCase("cash")){
                        addCashTransaction(amount, date);
                    }
                    else if(paymentMethod.equalsIgnoreCase("qris")){
                        addQrisTransaction(amount, date);
                    }
                }
                fileRead.close();
            }

        }
        catch(IOException e){
            System.out.println("Failure while reading or writing file");
        }
        catch(ParseException p){
            System.out.println("Error while parsing file data");
        }
    }

    // Metode untuk menulis histori transaksi ke file
    void writeTransactionHistory(){
        try{
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(PaymentInterface transaction : payments){
                if(transaction instanceof CashPayment){
                    fileWriter.write(sdf.format(transaction.getDate())+";"+transaction.getAmount()+";Cash\n");
                }
                else if (transaction instanceof DummyQRISPayment){
                    fileWriter.write(sdf.format(transaction.getDate())+";"+transaction.getAmount()+";QRIS\n");
                }
            }
            System.out.println("Succesfully saved transactions to file");
            fileWriter.close();
        }
        catch(IOException e){
            System.out.println("Error writing transactions to file");
        }
    }
}
