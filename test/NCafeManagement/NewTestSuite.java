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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Asus TUF
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({NCafeManagement.DatabaseConnectionTest.class, NCafeManagement.PaymentGUITest.class, NCafeManagement.RegularAdminGUITest.class, NCafeManagement.PaymentInterfaceTest.class, NCafeManagement.QRISTest.class, NCafeManagement.SuperAdminGUITest.class, NCafeManagement.PCManagerGUITest.class, NCafeManagement.AfterLoginGuiTest.class, NCafeManagement.TransactionHistoryTest.class, NCafeManagement.LoginGUITest.class})
public class NewTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
