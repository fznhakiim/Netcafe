package NCafeManagement;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.lang.reflect.Field;
import javax.swing.SwingUtilities;

/**
 * Test kelas PaymentGUI menggunakan JUnit 4.
 * Pengujian ini mencakup inisialisasi komponen GUI dan eksekusi metode main.
 * 
 * Author: Asus TUF
 */
public class PaymentGUITest {
    
   @Test
public void testPaymentGUIInitialization() {
    try {
        // Jalankan inisialisasi GUI di Event Dispatch Thread (EDT)
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                PaymentGUI gui = new PaymentGUI();
                assertNotNull("PaymentGUI instance should not be null", gui);
                
                // Tampilkan GUI (opsional, jika perlu untuk melihat)
                gui.setVisible(true);
            }
        });
    } catch (Exception e) {
        fail("Exception thrown during PaymentGUI initialization: " + e.getMessage());
    }
}

    /**
     * Test untuk memastikan bahwa komponen-komponen GUI diinisialisasi dengan benar.
     * Menggunakan refleksi untuk mengakses komponen privat.
     */
    @Test
    public void testGUIComponentsInitialization() {
        PaymentGUI gui = new PaymentGUI();
        
        try {
            // Mengakses jPanel1
            Field panel1Field = PaymentGUI.class.getDeclaredField("jPanel1");
            panel1Field.setAccessible(true);
            JPanel jPanel1 = (JPanel) panel1Field.get(gui);
            assertNotNull("jPanel1 should not be null", jPanel1);
            
            // Mengakses jPanel3
            Field panel3Field = PaymentGUI.class.getDeclaredField("jPanel3");
            panel3Field.setAccessible(true);
            JPanel jPanel3 = (JPanel) panel3Field.get(gui);
            assertNotNull("jPanel3 should not be null", jPanel3);
            
            // Mengakses BackButton
            Field backButtonField = PaymentGUI.class.getDeclaredField("BackButton");
            backButtonField.setAccessible(true);
            JButton backButton = (JButton) backButtonField.get(gui);
            assertNotNull("BackButton should not be null", backButton);
            
            // Mengakses QRISbutton
            Field qrisButtonField = PaymentGUI.class.getDeclaredField("QRISbutton");
            qrisButtonField.setAccessible(true);
            JButton qrisButton = (JButton) qrisButtonField.get(gui);
            assertNotNull("QRISbutton should not be null", qrisButton);
            
            // Mengakses CashButton
            Field cashButtonField = PaymentGUI.class.getDeclaredField("CashButton");
            cashButtonField.setAccessible(true);
            JButton cashButton = (JButton) cashButtonField.get(gui);
            assertNotNull("CashButton should not be null", cashButton);
            
            // Mengakses paymentHistory
            Field paymentHistoryField = PaymentGUI.class.getDeclaredField("paymentHistory");
            paymentHistoryField.setAccessible(true);
            JButton paymentHistory = (JButton) paymentHistoryField.get(gui);
            assertNotNull("paymentHistory should not be null", paymentHistory);
            
            // Mengakses TabelHistory
            Field tabelHistoryField = PaymentGUI.class.getDeclaredField("TabelHistory");
            tabelHistoryField.setAccessible(true);
            JTable tabelHistory = (JTable) tabelHistoryField.get(gui);
            assertNotNull("TabelHistory should not be null", tabelHistory);
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing GUI components: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa metode main dapat dijalankan tanpa error.
     */
    @Test
    public void testMainMethod() {
        try {
            String[] args = null;
            PaymentGUI.main(args);
            // Jika tidak ada exception, test dianggap berhasil
        } catch (Exception e) {
            fail("Exception thrown during main method execution: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memeriksa konfigurasi awal tabel.
     * Memastikan bahwa tabel memiliki kolom "ID Payment", "Customer Name", "Total", dan "Type".
     */
    @Test
    public void testTableColumns() {
        PaymentGUI gui = new PaymentGUI();
        
        try {
            // Mengakses TabelHistory
            Field tabelHistoryField = PaymentGUI.class.getDeclaredField("TabelHistory");
            tabelHistoryField.setAccessible(true);
            JTable tabelHistory = (JTable) tabelHistoryField.get(gui);
            assertNotNull("TabelHistory should not be null", tabelHistory);
            
            // Memeriksa nama kolom
            String[] expectedColumns = {"ID Payment", "Customer Name", "Total", "Type"};
            for (int i = 0; i < expectedColumns.length; i++) {
                assertEquals("Column " + i + " should be " + expectedColumns[i],
                             expectedColumns[i], tabelHistory.getColumnName(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing TabelHistory: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa aksi tombol "Back" dapat dijalankan tanpa error.
     */
    @Test
    public void testBackButtonAction() {
        PaymentGUI gui = new PaymentGUI();
        
        try {
            // Mengakses BackButton
            Field backButtonField = PaymentGUI.class.getDeclaredField("BackButton");
            backButtonField.setAccessible(true);
            JButton backButton = (JButton) backButtonField.get(gui);
            assertNotNull("BackButton should not be null", backButton);
            
            // Menambahkan ActionListener mock jika diperlukan
            // Namun, karena kita hanya menggunakan JUnit 4, kita hanya bisa memeriksa apakah tidak ada exception saat diklik
            backButton.doClick();
            
            // Verifikasi bahwa PCManagerGUI dibuka dan PaymentGUI ditutup
            // Karena ini melibatkan GUI, verifikasi bisa terbatas
            // Anda bisa memeriksa apakah PaymentGUI tidak terlihat setelah klik
            assertFalse("PaymentGUI should be disposed after clicking Back", gui.isVisible());
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing BackButton: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa aksi tombol "Payment History" dapat dijalankan tanpa error.
     */
    @Test
    public void testPaymentHistoryButtonAction() {
        PaymentGUI gui = new PaymentGUI();
        
        try {
            // Mengakses paymentHistory button
            Field paymentHistoryField = PaymentGUI.class.getDeclaredField("paymentHistory");
            paymentHistoryField.setAccessible(true);
            JButton paymentHistoryButton = (JButton) paymentHistoryField.get(gui);
            assertNotNull("paymentHistory button should not be null", paymentHistoryButton);
            
            // Simulasikan klik tombol "Payment History"
            paymentHistoryButton.doClick();
            
            // Verifikasi bahwa TabelHistory diperbarui
            // Ini memerlukan setup database atau mock data
            // Untuk pengujian dasar, pastikan tidak ada exception saat tombol diklik
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing paymentHistory button: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa aksi tombol "QRIS" dapat dijalankan tanpa error.
     */
    
    
    /**
     * Test untuk memastikan bahwa aksi tombol "Cash on Merchant" dapat dijalankan tanpa error.
     */
    @Test
    public void testCashButtonAction() {
        PaymentGUI gui = new PaymentGUI();
        
        try {
            // Mengakses CashButton
            Field cashButtonField = PaymentGUI.class.getDeclaredField("CashButton");
            cashButtonField.setAccessible(true);
            JButton cashButton = (JButton) cashButtonField.get(gui);
            assertNotNull("CashButton should not be null", cashButton);
            
            // Simulasikan klik tombol "Cash on Merchant"
            cashButton.doClick();
            
            // Verifikasi bahwa pembayaran diproses
            // Ini memerlukan setup database atau mock data
            // Untuk pengujian dasar, pastikan tidak ada exception saat tombol diklik
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing CashButton: " + e.getMessage());
        }
    }
}
