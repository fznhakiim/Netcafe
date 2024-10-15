package NCafeManagement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test kelas PCManagerGUI menggunakan JUnit 4.
 * Pengujian ini mencakup inisialisasi komponen GUI dan eksekusi metode main.
 * 
 * Author: Asus TUF
 */
public class PCManagerGUITest {
    
    /**
     * Test untuk memastikan bahwa instance PCManagerGUI dapat dibuat tanpa error.
     */
    @Test
    public void testPCManagerGUIInitialization() {
        try {
            PCManagerGUI gui = new PCManagerGUI();
            assertNotNull("PCManagerGUI instance should not be null", gui);
        } catch (Exception e) {
            fail("Exception thrown during PCManagerGUI initialization: " + e.getMessage());
        }
    }

    /**
     * Test untuk memastikan bahwa komponen-komponen GUI diinisialisasi dengan benar.
     * Menggunakan refleksi untuk mengakses komponen privat.
     */
    @Test
    public void testGUIComponentsInitialization() {
        PCManagerGUI gui = new PCManagerGUI();
        
        try {
            // Mengakses jPanel1
            Field panel1Field = PCManagerGUI.class.getDeclaredField("jPanel1");
            panel1Field.setAccessible(true);
            JPanel jPanel1 = (JPanel) panel1Field.get(gui);
            assertNotNull("jPanel1 should not be null", jPanel1);
            
            // Mengakses jPanel2
            Field panel2Field = PCManagerGUI.class.getDeclaredField("jPanel2");
            panel2Field.setAccessible(true);
            JPanel jPanel2 = (JPanel) panel2Field.get(gui);
            assertNotNull("jPanel2 should not be null", jPanel2);
            
            // Mengakses ViewPCButton
            Field viewPCButtonField = PCManagerGUI.class.getDeclaredField("ViewPCButton");
            viewPCButtonField.setAccessible(true);
            JButton viewPCButton = (JButton) viewPCButtonField.get(gui);
            assertNotNull("ViewPCButton should not be null", viewPCButton);
            
            // Mengakses ActivatePCButton
            Field activatePCButtonField = PCManagerGUI.class.getDeclaredField("ActivatePCButton");
            activatePCButtonField.setAccessible(true);
            JButton activatePCButton = (JButton) activatePCButtonField.get(gui);
            assertNotNull("ActivatePCButton should not be null", activatePCButton);
            
            // Mengakses AddNewPCButton
            Field addNewPCButtonField = PCManagerGUI.class.getDeclaredField("AddNewPCButton");
            addNewPCButtonField.setAccessible(true);
            JButton addNewPCButton = (JButton) addNewPCButtonField.get(gui);
            assertNotNull("AddNewPCButton should not be null", addNewPCButton);
            
            // Mengakses PaymentButton
            Field paymentButtonField = PCManagerGUI.class.getDeclaredField("PaymentButton");
            paymentButtonField.setAccessible(true);
            JButton paymentButton = (JButton) paymentButtonField.get(gui);
            assertNotNull("PaymentButton should not be null", paymentButton);
            
            // Mengakses Back button
            Field backButtonField = PCManagerGUI.class.getDeclaredField("Back");
            backButtonField.setAccessible(true);
            JButton backButton = (JButton) backButtonField.get(gui);
            assertNotNull("Back button should not be null", backButton);
            
            // Mengakses jTable1
            Field tableField = PCManagerGUI.class.getDeclaredField("jTable1");
            tableField.setAccessible(true);
            JTable jTable1 = (JTable) tableField.get(gui);
            assertNotNull("jTable1 should not be null", jTable1);
            
      
    } catch (NoSuchFieldException e) {
        fail("Field not found: " + e.getMessage());
    } catch (IllegalAccessException e) {
        fail("Access error: " + e.getMessage());
    } catch (Exception e) {
        fail("Unexpected error: " + e.getMessage());
    }
}
    
    /**
     * Test untuk memastikan bahwa metode main dapat dijalankan tanpa error.
     */
    @Test
    public void testMainMethod() {
        try {
            String[] args = null;
            PCManagerGUI.main(args);
            // Jika tidak ada exception, test dianggap berhasil
        } catch (Exception e) {
            fail("Exception thrown during main method execution: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memeriksa konfigurasi awal tabel.
     * Memastikan bahwa tabel memiliki kolom "ID", "Duration", dan "Status".
     */
    @Test
    public void testTableColumns() {
        PCManagerGUI gui = new PCManagerGUI();
        
        try {
            // Mengakses jTable1
            Field tableField = PCManagerGUI.class.getDeclaredField("jTable1");
            tableField.setAccessible(true);
            JTable jTable1 = (JTable) tableField.get(gui);
            assertNotNull("jTable1 should not be null", jTable1);
            
            // Memeriksa nama kolom
            String[] expectedColumns = {"ID", "Duration", "Status"};
            for (int i = 0; i < expectedColumns.length; i++) {
                assertEquals("Column " + i + " should be " + expectedColumns[i],
                             expectedColumns[i], jTable1.getColumnName(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing jTable1: " + e.getMessage());
        }
    }
}
