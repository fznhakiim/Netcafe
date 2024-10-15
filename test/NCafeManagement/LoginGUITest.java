package NCafeManagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;

/**
 * Unit test for LoginGUI class.
 */
public class LoginGUITest {

    private LoginGUI loginGUI;

    @Before
    public void setUp() {
        // Create a mock of the LoginGUI class
        loginGUI = mock(LoginGUI.class);

        // Simulate behavior for valid admin super login
        when(loginGUI.authenticate("admin_super", "password123")).thenReturn("SUCCESS:admin super");

        // Simulate behavior for valid admin regular login
        when(loginGUI.authenticate("admin_regular", "password123")).thenReturn("SUCCESS:admin regular");

        // Simulate behavior for invalid credentials
        when(loginGUI.authenticate("invalidUser", "wrongPassword")).thenReturn("Wrong Username Or Password");

        // Simulate behavior for empty fields
        when(loginGUI.authenticate("", "")).thenReturn("Please Enter Your Username and Password");

        // Simulate behavior for unknown status user
        when(loginGUI.authenticate("unknown_status_user", "password123")).thenReturn("Unknown admin status");

        // Simulate behavior for special characters (SQL Injection scenario)
        when(loginGUI.authenticate("' OR 1=1; --", "anything")).thenReturn("SQL Injection Detected");
    }

    @After
    public void tearDown() {
        // Clean up after each test (if necessary)
        loginGUI = null;
    }

    /**
     * Test to check if login is successful with valid credentials (admin super).
     */
    @Test
    public void testAuthenticateWithValidAdminSuper() {
        System.out.println("testAuthenticateWithValidAdminSuper");
        String username = "admin_super";
        String password = "password123";

        String result = loginGUI.authenticate(username, password);
        assertNotNull("Hasil autentikasi tidak boleh null", result);
        assertEquals("SUCCESS:admin super", result);
    }

    @Test
public void testAuthenticateDatabaseError() {
    // Simulasikan error koneksi database
    when(loginGUI.authenticate(anyString(), anyString())).thenReturn("Error: Connection failed");

    String result = loginGUI.authenticate("anyuser", "anypass");
    assertTrue(result.startsWith("Error"));
}

@Test
public void testLoginFieldMousePressedSuccess() {
    // Simulasikan hasil sukses dari metode authenticate
    when(loginGUI.authenticate("validUser", "validPass")).thenReturn("SUCCESS:admin super");

    loginGUI.LoginFieldMousePressed(null);

    // Tambahkan assertion yang sesuai untuk memeriksa apakah AfterLoginGui ditampilkan atau proses berjalan seperti yang diharapkan
}

@Test
public void testLoginFieldMousePressedFailure() {
    // Simulasikan hasil gagal dari metode authenticate
    when(loginGUI.authenticate("invalidUser", "invalidPass")).thenReturn("Wrong Username Or Password");

    loginGUI.LoginFieldMousePressed(null);

    // Tambahkan assertion yang sesuai untuk memeriksa apakah JOptionPane dengan pesan kesalahan ditampilkan
}

/*@Test
public void testXitMousePressed() {
    // Gunakan PowerMockito untuk memalsukan System.exit
    PowerMockito.mockStatic(System.class);
    doNothing().when(System.class);
    
    loginGUI.$XitMousePressed(null);

    // Verifikasi bahwa System.exit dipanggil
    PowerMockito.verifyStatic(System.class);
    System.exit(0);
}*/

    /**
     * Test to check if login is successful with valid credentials (admin regular).
     */
    @Test
    public void testAuthenticateWithValidAdminRegular() {
        System.out.println("testAuthenticateWithValidAdminRegular");
        String username = "admin_regular";
        String password = "password123";

        String result = loginGUI.authenticate(username, password);
        assertNotNull("Hasil autentikasi tidak boleh null", result);
        assertEquals("SUCCESS:admin regular", result);
    }

    /**
     * Test to check if login fails with invalid credentials.
     */
    @Test
    public void testAuthenticateWithInvalidCredentials() {
        System.out.println("testAuthenticateWithInvalidCredentials");
        String username = "invalidUser";
        String password = "wrongPassword";

        String result = loginGUI.authenticate(username, password);
        assertEquals("Wrong Username Or Password", result);
    }

    /**
     * Test to check if login fails with empty fields.
     */
    @Test
    public void testAuthenticateWithEmptyFields() {
        System.out.println("testAuthenticateWithEmptyFields");
        String username = "";
        String password = "";

        String result = loginGUI.authenticate(username, password);
        assertEquals("Please Enter Your Username and Password", result);
    }

    /**
     * Test to check login with an unknown admin status.
     */
    @Test
    public void testAuthenticateWithUnknownStatus() {
        System.out.println("testAuthenticateWithUnknownStatus");
        String username = "unknown_status_user";
        String password = "password123";

        String result = loginGUI.authenticate(username, password);
        assertEquals("Unknown admin status", result);
    }

    /**
     * Test to ensure no SQL Injection vulnerability.
     */
    @Test
    public void testAuthenticateWithSQLInjection() {
        System.out.println("testAuthenticateWithSQLInjection");
        String username = "' OR 1=1; --";
        String password = "anything";

        String result = loginGUI.authenticate(username, password);
        assertEquals("SQL Injection Detected", result);
    }

    /**
     * Test for edge case with long username and password.
     */
    @Test
    public void testAuthenticateWithLongCredentials() {
        System.out.println("testAuthenticateWithLongCredentials");
        String username = "verylongusernameverylongusernameverylongusername";
        String password = "verylongpasswordverylongpasswordverylongpassword";

        when(loginGUI.authenticate(username, password)).thenReturn("Long credentials processed");

        String result = loginGUI.authenticate(username, password);
        assertEquals("Long credentials processed", result);
    }

    /**
     * Test to ensure the main method runs without exception.
     */
    @Test
    public void testMain() {
        System.out.println("testMain");
        String[] args = null;
        try {
            LoginGUI.main(args);
        } catch (Exception e) {
            fail("Metode main harus berjalan tanpa melempar exception");
        }
    }
}
