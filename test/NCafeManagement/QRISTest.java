package NCafeManagement;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Asus TUF
 */
public class QRISTest {
    
    public QRISTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Metode ini dijalankan sekali sebelum pengujian apa pun dalam kelas ini dieksekusi
        System.out.println("Memulai Suite Pengujian QRIS");
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Metode ini dijalankan sekali setelah semua pengujian dalam kelas ini telah dieksekusi
        System.out.println("Menyelesaikan Suite Pengujian QRIS");
    }
    
    @Before
    public void setUp() {
        // Metode ini dijalankan sebelum setiap metode pengujian dieksekusi
        System.out.println("Menyetel untuk pengujian individual");
    }
    
    @After
    public void tearDown() {
        // Metode ini dijalankan setelah setiap metode pengujian dieksekusi
        System.out.println("Membongkar setelah pengujian individual");
    }

    @Test
    public void testQrisGUI() throws InterruptedException, InvocationTargetException {
        // Menguji inisialisasi GUI
        SwingUtilities.invokeAndWait(() -> {
            QRIS qris = new QRIS();
            assertNotNull(qris);
            qris.setVisible(true); // Tampilkan GUI untuk mengeksekusi kode inisialisasi
        });
    }

    @Test
    public void testQrisConstructor() {
        // Menguji konstruktor QRIS
        QRIS qris = new QRIS();
        assertNotNull(qris);
    }

    /**
     * Pengujian metode main dari kelas QRIS.
     */
    @Test
    public void testMain() {
        System.out.println("Mengujikan metode main QRIS");
        String[] args = null;

        try {
            QRIS.main(args);
            System.out.println("Metode main QRIS dieksekusi dengan sukses");
        } catch (Exception e) {
            fail("Pengecualian dilemparkan selama eksekusi metode main QRIS: " + e.getMessage());
        }
    }


}
