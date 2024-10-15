package NCafeManagement;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * Test kelas RegularAdminGUI menggunakan JUnit 4.
 * Pengujian ini mencakup inisialisasi komponen GUI, eksekusi metode main,
 * dan pengujian aksi dasar tombol.
 * 
 * Author: Asus TUF
 */
public class RegularAdminGUITest {
    
    /**
     * Test untuk memastikan bahwa instance RegularAdminGUI dapat dibuat tanpa error.
     */
    @Test
    public void testRegularAdminGUIInitialization() {
        try {
            RegularAdminGUI gui = new RegularAdminGUI();
            assertNotNull("RegularAdminGUI instance should not be null", gui);
        } catch (Exception e) {
            fail("Exception thrown during RegularAdminGUI initialization: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa komponen-komponen GUI diinisialisasi dengan benar.
     * Menggunakan refleksi untuk mengakses komponen privat.
     */
    /*@Test
    public void testGUIComponentsInitialization() {
        RegularAdminGUI gui = new RegularAdminGUI();
        
        try {
            // Mengakses jPanel1
            Field panel1Field = RegularAdminGUI.class.getDeclaredField("jPanel1");
            panel1Field.setAccessible(true);
            JPanel jPanel1 = (JPanel) panel1Field.get(gui);
            assertNotNull("jPanel1 should not be null", jPanel1);
            
            // Mengakses jPanel2
            Field panel2Field = RegularAdminGUI.class.getDeclaredField("jPanel2");
            panel2Field.setAccessible(true);
            JPanel jPanel2 = (JPanel) panel2Field.get(gui);
            assertNotNull("jPanel2 should not be null", jPanel2);
            
            // Mengakses ViewAdminbtn
            Field viewAdminBtnField = RegularAdminGUI.class.getDeclaredField("ViewAdminbtn");
            viewAdminBtnField.setAccessible(true);
            JButton viewAdminBtn = (JButton) viewAdminBtnField.get(gui);
            assertNotNull("ViewAdminbtn should not be null", viewAdminBtn);
            
            // Mengakses ChangePWbutton
            Field changePWButtonField = RegularAdminGUI.class.getDeclaredField("ChangePWbutton");
            changePWButtonField.setAccessible(true);
            JButton changePWButton = (JButton) changePWButtonField.get(gui);
            assertNotNull("ChangePWbutton should not be null", changePWButton);
            
            // Mengakses BackAdminbutton
            Field backAdminButtonField = RegularAdminGUI.class.getDeclaredField("BackAdminbutton");
            backAdminButtonField.setAccessible(true);
            JButton backAdminButton = (JButton) backAdminButtonField.get(gui);
            assertNotNull("BackAdminbutton should not be null", backAdminButton);
            
            // Mengakses JtableRegularAdmin
            Field tableField = RegularAdminGUI.class.getDeclaredField("JtableRegularAdmin");
            tableField.setAccessible(true);
            JTable jTableRegularAdmin = (JTable) tableField.get(gui);
            assertNotNull("JtableRegularAdmin should not be null", jTableRegularAdmin);
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing GUI components: " + e.getMessage());
        }
    }*/
    
    /**
     * Test untuk memastikan bahwa metode main dapat dijalankan tanpa error.
     */
    @Test
    public void testMainMethod() {
        try {
            String[] args = null;
            RegularAdminGUI.main(args);
            // Jika tidak ada exception, test dianggap berhasil
        } catch (Exception e) {
            fail("Exception thrown during main method execution: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memeriksa konfigurasi awal tabel.
     * Memastikan bahwa tabel memiliki kolom "Username" dan "Status".
     */
    @Test
    public void testTableColumns() {
        RegularAdminGUI gui = new RegularAdminGUI();
        
        try {
            // Mengakses JtableRegularAdmin
            Field tableField = RegularAdminGUI.class.getDeclaredField("JtableRegularAdmin");
            tableField.setAccessible(true);
            JTable jTableRegularAdmin = (JTable) tableField.get(gui);
            assertNotNull("JtableRegularAdmin should not be null", jTableRegularAdmin);
            
            // Memeriksa nama kolom
            String[] expectedColumns = {"Username", "Status"};
            for (int i = 0; i < expectedColumns.length; i++) {
                assertEquals("Column " + i + " should be " + expectedColumns[i],
                             expectedColumns[i], jTableRegularAdmin.getColumnName(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing JtableRegularAdmin: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa aksi tombol "Back" dapat dijalankan tanpa error.
     */
    @Test
    public void testBackAdminbuttonMouseClicked() {
        RegularAdminGUI gui = new RegularAdminGUI("testUser", "testPass");
        
        try {
            // Mengakses BackAdminbutton
            Field backAdminButtonField = RegularAdminGUI.class.getDeclaredField("BackAdminbutton");
            backAdminButtonField.setAccessible(true);
            JButton backAdminButton = (JButton) backAdminButtonField.get(gui);
            assertNotNull("BackAdminbutton should not be null", backAdminButton);
            
            // Simulasikan klik tombol "Back"
            backAdminButton.doClick();
            
            // Verifikasi bahwa GUI RegularAdminGUI tidak terlihat setelah klik Back
            assertFalse("RegularAdminGUI should be disposed after clicking Back", gui.isVisible());
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing BackAdminbutton: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk memastikan bahwa aksi tombol "View Admin" dapat dijalankan tanpa error.
     */
    /*@Test
public void testViewAdminbtnMouseClicked() {
    RegularAdminGUI gui = new RegularAdminGUI();
    
    // Tampilkan GUI untuk memastikan semua komponen diinisialisasi dengan benar
    gui.setVisible(true);
    
    try {
        // Mengakses ViewAdminbtn
        Field viewAdminBtnField = RegularAdminGUI.class.getDeclaredField("ViewAdminbtn");
        viewAdminBtnField.setAccessible(true);
        JButton viewAdminBtn = (JButton) viewAdminBtnField.get(gui);
        assertNotNull("ViewAdminbtn should not be null", viewAdminBtn);
        
        // Simulasikan klik tombol "View Admin"
        viewAdminBtn.doClick();
        
        // Verifikasi bahwa tabel diupdate.
        // Untuk pengujian dasar, kita hanya memastikan tidak ada exception yang dilemparkan.
        
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Exception thrown while accessing ViewAdminbtn: " + e.getMessage());
    }
}*/

    
    /**
     * Test untuk memastikan bahwa aksi tombol "Change Admin Password" dapat dijalankan tanpa error.
     */
    @Test
    public void testChangePWbuttonMouseClicked() {
        RegularAdminGUI gui = new RegularAdminGUI();
        
        try {
            // Mengakses ChangePWbutton
            Field changePWButtonField = RegularAdminGUI.class.getDeclaredField("ChangePWbutton");
            changePWButtonField.setAccessible(true);
            JButton changePWButton = (JButton) changePWButtonField.get(gui);
            assertNotNull("ChangePWbutton should not be null", changePWButton);
            
            // Simulasikan klik tombol "Change Admin Password"
            changePWButton.doClick();
            
            // Verifikasi bahwa dialog input muncul dan password berubah.
            // Karena JUnit tidak dapat menangani dialog interaktif, pengujian ini terbatas pada memastikan tidak ada exception.
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing ChangePWbutton: " + e.getMessage());
        }
    }
}
