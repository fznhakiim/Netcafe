package NCafeManagement;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class TransactionHistoryTest {

    // Test addCashTransaction method
    @Test
    public void testAddCashTransaction() {
        TransactionHistory instance = new TransactionHistory();
        double amount = 10000.0;
        Date date = new Date();
        
        // Simpan output ke dalam stream untuk diuji
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        instance.addCashTransaction(amount, date);
        
        // Pastikan output dari processPayment berisi informasi yang diharapkan
        String output = outContent.toString();
        
        assertTrue("Output should contain the correct cash amount", output.contains("Cash payment processed for amount: Rp." + amount));
    }

    // Test addQrisTransaction method
    @Test
    public void testAddQrisTransaction() {
        TransactionHistory instance = new TransactionHistory();
        double amount = 15000.0;
        Date date = new Date();
        
        // Simpan output ke dalam stream untuk diuji
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        instance.addQrisTransaction(amount, date);
        
        // Pastikan output dari processPayment berisi informasi yang diharapkan
        String output = outContent.toString();

        assertTrue("Output should contain the correct QRIS amount", output.contains("Dummy QRIS payment processed for amount: Rp." + amount));
    }

    // Test readTransactionHistory method
    /*@Test
    public void testReadTransactionHistory() {
        TransactionHistory instance = new TransactionHistory();

        // Simpan output ke dalam stream untuk diuji
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        instance.readTransactionHistory();

        // Cek apakah ada transaksi yang dibaca dan ditampilkan
        // Namun, karena tidak ada transaksi yang ditulis sebelumnya, output mungkin kosong atau hanya mencetak pembuatan file
        String output = outContent.toString();

        // Jika file tidak ada, maka output harus mengandung pesan pembuatan file baru
        // Jika file ada, maka output harus mencetak transaksi
        // Untuk pengujian ini, kita anggap file tidak ada
        assertTrue("Output should indicate file not found and new file creation", output.contains("Transaction_History.txt not found, creating new file..."));
    }*/

    // Test writeTransactionHistory method
    @Test
    public void testWriteTransactionHistory() {
        TransactionHistory instance = new TransactionHistory();
        double amount = 5000.0;
        Date date = new Date();
        instance.addCashTransaction(amount, date); // Tambahkan satu transaksi tunai
        
        // Simpan output ke dalam stream untuk diuji
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        instance.writeTransactionHistory();
        String output = outContent.toString();
        
        assertTrue("Output should indicate successful write", output.contains("Succesfully saved transactions to file"));
    }

    // Test paymentToDuration method
    @Test
    public void testPaymentToDuration() {
        TransactionHistory instance = new TransactionHistory();
        double amount = 300.0;
        long duration = instance.paymentToDuration(amount);

        // Cek apakah durasi yang dihitung benar
        assertEquals("Duration should be calculated correctly", 120000, duration); // 300 / 150 = 2 menit -> 2 * 60000 = 120000 ms
    }

        @Test
    public void testSetFileName() {
        TransactionHistory instance = new TransactionHistory();
        String testFileName = "test_file.txt";

        instance.setFileName(testFileName);

        // Gunakan refleksi untuk memastikan bahwa nilai fileName sudah di-set dengan benar
        try {
            Field fileNameField = TransactionHistory.class.getDeclaredField("fileName");
            fileNameField.setAccessible(true);
            String actualFileName = (String) fileNameField.get(instance);

            assertEquals("The fileName should be set correctly.", testFileName, actualFileName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Error accessing fileName field: " + e.getMessage());
        }
    }
        @Test
public void testDisplayTransactions() {
    TransactionHistory instance = new TransactionHistory();
    Date testDate = new Date();
    double testAmount = 5000.0;
    
    instance.addCashTransaction(testAmount, testDate);
    
    // Tangkap output ke System.out
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    
    instance.displayTransactions();
    
    // Verifikasi output yang dihasilkan
    String output = outContent.toString();
    assertTrue("Output should contain the transaction date", output.contains(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(testDate)));
    assertTrue("Output should contain the transaction amount", output.contains(String.valueOf(testAmount)));
}

@Test
public void testReadTransactionHistory_FileNotFound() throws IOException {
    String fileName = "non_existing_file.txt";
    TransactionHistory instance = new TransactionHistory(fileName);
    
    // Pastikan file tidak ada sebelum pengujian
    File file = new File(fileName);
    if (file.exists()) {
        file.delete();
    }
    
    // Tangkap output ke System.out
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    
    instance.readTransactionHistory();
    
    // Verifikasi bahwa file baru dibuat dan output berisi pesan yang diharapkan
    assertTrue(outContent.toString().contains(fileName + " not found, creating new file..."));
    assertTrue("File should be created", file.exists());
    
    // Hapus file setelah pengujian
    if (file.exists()) {
        file.delete();
    }
}

@Test
public void testGetDate() {
    Date testDate = new Date();
    TransactionHistory instance = new TransactionHistory();
    instance.addCashTransaction(1000.0, testDate);
    
    Date result = instance.getDate();
    assertEquals("Date should be returned correctly", testDate, result);
}

@Test
public void testGetAmount() {
    double testAmount = 2500.0;
    TransactionHistory instance = new TransactionHistory();
    instance.addCashTransaction(testAmount, new Date());
    
    double result = instance.getAmount();
    assertEquals("Amount should be returned correctly", testAmount, result, 0.01);
}


    // Test file not found and creation of new file
    @Test
    public void testTransactionFileCreation() throws IOException {
        String fileName = "Transaction_History.txt"; // Sesuaikan dengan yang digunakan di kelas TransactionHistory
        File file = new File(fileName);
        
        // Pastikan file tidak ada sebelum pengujian
        if (file.exists()) {
            file.delete();
        }
        
        TransactionHistory instance = new TransactionHistory(fileName);
        
        // Simpan output ke dalam stream untuk diuji
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        instance.readTransactionHistory(); // Akan memicu pembuatan file jika tidak ditemukan
        
        String output = outContent.toString();
        
        assertTrue("Output should indicate file not found and new file creation", output.contains(fileName + " not found, creating new file..."));
        
        // Cek apakah file benar-benar dibuat
        assertTrue("File should be created", file.exists());
        
        // Hapus file setelah pengujian
        if (file.exists()) {
            file.delete();
        }
    }

    // Test error handling during writeTransactionHistory
    @Test
    public void testWriteTransactionHistoryError() {
        // Menggunakan konstruktor dengan nama file yang tidak valid untuk memicu IOException
        String invalidFileName = "?:/invalid_path/Transaction_History.txt"; // Nama file yang tidak valid
        TransactionHistory instance = new TransactionHistory(invalidFileName);
        
        // Atur output untuk menangkap pesan error
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        instance.writeTransactionHistory();
        String output = outContent.toString();
        
        assertTrue("Output should indicate an error during writing transactions", output.contains("Error writing transactions to file"));
    }
}
