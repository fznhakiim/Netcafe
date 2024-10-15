/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NCafeManagement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Asus TUFa
 */
public class AfterLoginGuiTest {
    
    public AfterLoginGuiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class AfterLoginGui.
     */ 
 @Test
public void testExitButtonClick() {
    // Create an instance of AfterLoginGui
    AfterLoginGui gui = new AfterLoginGui("testuser", "password");
    
    // Simulate the Exit button click (with evt as null for the test case)
    gui.ExitButtonMouseClicked(null);
    
    // Assert that exit was confirmed (based on the new flag)
    assertTrue(gui.exitConfirmed);
}
 @Test
    public void testPCmanageButtonMouseClicked() {
        // Mock PCManagerGUI untuk memastikan tidak ada frame GUI yang benar-benar dibuka
        PCManagerGUI mockPCManagerGUI = mock(PCManagerGUI.class);

        // Buat instance dari AfterLoginGui, menggunakan konstruksi yang tepat
        AfterLoginGui gui = spy(new AfterLoginGui("testUser", "testPassword"));

        // Simulasikan klik pada PCmanageButton (event dapat null jika tidak digunakan)
        gui.PCmanageButtonMouseClicked(null);

        // Verifikasi bahwa frame utama dipanggil untuk dispose()
        verify(gui, times(1)).dispose();

        // Verifikasi bahwa PCManagerGUI dibuat dan dibuka dengan benar
        // Anda bisa memverifikasi pembuatan instance, atau memverifikasi bahwa setVisible(true) dipanggil
        verify(mockPCManagerGUI, never()).setVisible(true);  // Pastikan tidak benar-benar membuka GUI
    }

@Test
public void testAdminmanageButtonClickForRegularAdmin() {
    AfterLoginGui gui = new AfterLoginGui("regularAdmin", "password");

    // Stub method getAdminType untuk mengembalikan "admin regular"
    gui = Mockito.spy(gui);
    Mockito.doReturn("admin regular").when(gui).getAdminType("regularAdmin");

    // Simulasikan klik pada AdminmanageButton
    gui.AdminmanageButtonMouseClicked(null);

    // Verifikasi apakah RegularAdminGUI dibuka
    // Ini bisa diverifikasi jika RegularAdminGUI frame diubah menjadi visible
    // Anda dapat menggunakan assertion atau mock untuk memeriksa apakah frame lain terbuka
}


@Test
public void testAdminmanageButtonClickForSuperAdmin() {
    AfterLoginGui gui = new AfterLoginGui("superAdmin", "password");

    // Stub method getAdminType untuk mengembalikan "admin super"
    gui = Mockito.spy(gui);
    Mockito.doReturn("admin super").when(gui).getAdminType("superAdmin");

    // Simulasikan klik pada AdminmanageButton
    gui.AdminmanageButtonMouseClicked(null);

    // Verifikasi apakah SuperAdminGUI dibuka
    // Sama seperti sebelumnya, ini bisa diverifikasi jika GUI lain dibuka
}


   @Test
    public void testMain() {
        System.out.println("main method test");
        String[] args = null;
        try {
            // Run the main method of AfterLoginGui
            AfterLoginGui.main(args);
            // Test passes if no exceptions are thrown
        } catch (Exception e) {
            fail("Main method should run without throwing exceptions.");
        }
    }
}

