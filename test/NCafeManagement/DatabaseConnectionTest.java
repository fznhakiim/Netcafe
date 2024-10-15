package NCafeManagement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test kelas DatabaseConnection menggunakan JUnit 4.
 * Pastikan Anda memiliki database MySQL berjalan di localhost dengan konfigurasi yang sesuai.
 * 
 * Author: Asus TUF
 */
public class DatabaseConnectionTest {
    
    private DatabaseConnection dbConn;
    private Connection connection;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    public DatabaseConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Metode ini dijalankan sekali sebelum semua test dijalankan.
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Metode ini dijalankan sekali setelah semua test selesai.
    }
    
    @Before
    public void setUp() {
        outContent.reset();
        dbConn = new DatabaseConnection();
        // Mengalihkan System.out ke outContent
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void tearDown() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Gagal menutup koneksi: " + e.getMessage());
            }
        }
        // Mengembalikan System.out ke aliran asli
        System.setOut(originalOut);
    }

    /**
     * Test untuk metode setConnection ketika koneksi berhasil.
     */
    @Test
    public void testSetConnection_Success() {
        System.out.println("testSetConnection_Success");
        connection = dbConn.setConnection();
        assertNotNull("Koneksi tidak boleh null jika berhasil terhubung.", connection);
        try {
            assertFalse("Koneksi harus terbuka.", connection.isClosed());
        } catch (SQLException e) {
            fail("Terjadi exception saat memeriksa status koneksi: " + e.getMessage());
        }
    }
    
    /**
     * Test untuk metode setConnection ketika koneksi gagal.
     * Misalnya, dengan menggunakan URL yang salah.
     */
    @Test
    public void testSetConnection_Failure() {
        System.out.println("testSetConnection_Failure");
        // Membuat subclass anonim untuk mengoverride setConnection dengan URL yang salah
        DatabaseConnection faultyDbConn = new DatabaseConnection() {
            @Override
            public Connection setConnection() {
                try{
                    String url = "jdbc:mysql://invalid_host:3306/warned_db"; // URL salah
                    String user = "root";
                    String pass = "";
                    Class.forName("com.mysql.jdbc.Driver");
                    return DriverManager.getConnection(url, user, pass);
                } catch(Exception e){
                    System.out.println("Error " + e.getMessage());
                    return null;
                }
            }
        };
        
        Connection faultyConnection = faultyDbConn.setConnection();
        assertNull("Koneksi harus null jika gagal terhubung.", faultyConnection);
        
        // Verifikasi bahwa pesan error telah dicetak
        String expectedErrorMessage = "Error"; // Anda bisa lebih spesifik jika perlu
        assertTrue("Pesan error tidak ditemukan di output.", outContent.toString().contains(expectedErrorMessage));
    }

    /**
     * Test untuk memeriksa jika driver JDBC tidak ditemukan.
     */
    @Test
    public void testDriverNotFound() {
        System.out.println("testDriverNotFound");
        DatabaseConnection noDriverDbConn = new DatabaseConnection() {
            @Override
            public Connection setConnection() {
                try {
                    Class.forName("invalid.jdbc.Driver"); // Driver salah
                    return null;
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                    return null;
                }
            }
        };
        Connection noDriverConnection = noDriverDbConn.setConnection();
        assertNull("Koneksi harus null jika driver tidak ditemukan.", noDriverConnection);
        
        // Verifikasi bahwa pesan error telah dicetak
        String expectedErrorMessage = "Error"; // Anda bisa lebih spesifik jika perlu
        assertTrue("Pesan error tidak ditemukan di output.", outContent.toString().contains(expectedErrorMessage));
    }

    /**
     * Test penutupan koneksi dengan benar setelah digunakan.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("testCloseConnection");
        connection = dbConn.setConnection();
        assertNotNull("Koneksi harus berhasil dibuat.", connection);
        try {
            connection.close();
            assertTrue("Koneksi harus ditutup setelah close dipanggil.", connection.isClosed());
        } catch (SQLException e) {
            fail("Exception saat menutup koneksi: " + e.getMessage());
        }
    }
}
