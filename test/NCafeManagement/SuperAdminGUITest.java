package NCafeManagement;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTable;
import java.lang.reflect.Field;

/**
 * Test kelas SuperAdminGUI menggunakan JUnit 4.
 * Pengujian ini mencakup inisialisasi komponen GUI dan aksi dasar tombol.
 */
public class SuperAdminGUITest {

    /**
     * Test untuk memastikan bahwa instance SuperAdminGUI dapat dibuat tanpa error.
     */
    @Test
    public void testSuperAdminGUIInitialization() {
        try {
            SuperAdminGUI gui = new SuperAdminGUI("admin", "password");
            assertNotNull("SuperAdminGUI instance should not be null", gui);
        } catch (Exception e) {
            fail("Exception thrown during SuperAdminGUI initialization: " + e.getMessage());
        }
    }
        @Test
        public void testDefaultConstructor() {
            try {
                SuperAdminGUI gui = new SuperAdminGUI();
                assertNotNull("SuperAdminGUI instance should not be null", gui);
            } catch (Exception e) {
                fail("Exception thrown during default SuperAdminGUI initialization: " + e.getMessage());
            }
        }

    /**
     * Test untuk memastikan bahwa komponen-komponen GUI diinisialisasi dengan benar.
     * Menggunakan refleksi untuk mengakses komponen privat.
     */
    @Test
    public void testGUIComponentsInitialization() {
        SuperAdminGUI gui = new SuperAdminGUI("admin", "password");

        try {
            // Mengakses ViewAdminbutton
            Field viewAdminBtnField = SuperAdminGUI.class.getDeclaredField("ViewAdminbutton");
            viewAdminBtnField.setAccessible(true);
            JButton viewAdminBtn = (JButton) viewAdminBtnField.get(gui);
            assertNotNull("ViewAdminbutton should not be null", viewAdminBtn);

            // Mengakses ChangePwButton
            Field changePWButtonField = SuperAdminGUI.class.getDeclaredField("ChangePwButton");
            changePWButtonField.setAccessible(true);
            JButton changePWButton = (JButton) changePWButtonField.get(gui);
            assertNotNull("ChangePwButton should not be null", changePWButton);

            // Mengakses JtableSadmin
            Field tableField = SuperAdminGUI.class.getDeclaredField("JtableSadmin");
            tableField.setAccessible(true);
            JTable jTableSadmin = (JTable) tableField.get(gui);
            assertNotNull("JtableSadmin should not be null", jTableSadmin);

            // Mengakses ChangeSPWbutton
            Field changeSPWButtonField = SuperAdminGUI.class.getDeclaredField("ChangeSPWbutton");
            changeSPWButtonField.setAccessible(true);
            JButton changeSPWButton = (JButton) changeSPWButtonField.get(gui);
            assertNotNull("ChangeSPWbutton should not be null", changeSPWButton);

            // Mengakses RemoveButton1
            Field removeButtonField = SuperAdminGUI.class.getDeclaredField("RemoveButton1");
            removeButtonField.setAccessible(true);
            JButton removeButton = (JButton) removeButtonField.get(gui);
            assertNotNull("RemoveButton1 should not be null", removeButton);

            // Mengakses BackSadmin
            Field backButtonField = SuperAdminGUI.class.getDeclaredField("BackSadmin");
            backButtonField.setAccessible(true);
            JButton backButton = (JButton) backButtonField.get(gui);
            assertNotNull("BackSadmin should not be null", backButton);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing GUI components: " + e.getMessage());
        }
    }

    /**
     * Test untuk memastikan bahwa aksi tombol "View Admin" dapat dijalankan tanpa error.
     */
    @Test
    public void testViewAdminbuttonClick() {
        SuperAdminGUI gui = new SuperAdminGUI("admin", "password");

        try {
            // Mengakses ViewAdminbutton
            Field viewAdminBtnField = SuperAdminGUI.class.getDeclaredField("ViewAdminbutton");
            viewAdminBtnField.setAccessible(true);
            JButton viewAdminBtn = (JButton) viewAdminBtnField.get(gui);
            assertNotNull("ViewAdminbutton should not be null", viewAdminBtn);

            // Simulasikan klik tombol "View Admin"
            viewAdminBtn.doClick();
            // Tambahkan assertions untuk memeriksa hasil dari klik ini

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing ViewAdminbutton: " + e.getMessage());
        }
    }

    // Tambahkan pengujian serupa untuk tombol lainnya

    @Test
    public void testChangePwButtonClick() {
        SuperAdminGUI gui = new SuperAdminGUI("admin", "password");

        try {
            // Mengakses ChangePwButton
            Field changePWButtonField = SuperAdminGUI.class.getDeclaredField("ChangePwButton");
            changePWButtonField.setAccessible(true);
            JButton changePWButton = (JButton) changePWButtonField.get(gui);
            assertNotNull("ChangePwButton should not be null", changePWButton);

            // Simulasikan klik tombol "Change Password"
            changePWButton.doClick();
            // Tambahkan assertions untuk memeriksa hasil dari klik ini

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing ChangePwButton: " + e.getMessage());
        }
    }

    // Tambahkan pengujian untuk tombol-tombol lainnya seperti BackSadmin, RemoveButton, dll.
}   
