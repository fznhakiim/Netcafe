package NCafeManagement;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test kelas PaymentInterface menggunakan JUnit 4.
 * Pengujian ini mencakup implementasi dari PaymentInterface dan pengujian metode-metodenya.
 * 
 * Author: Asus TUF
 */
public class PaymentInterfaceTest {
    
    public PaymentInterfaceTest() {
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
        // Inisialisasi sebelum setiap test dijalankan.
    }
    
    @After
    public void tearDown() {
        // Pembersihan setelah setiap test dijalankan.
    }

    /**
     * Implementasi konkret dari PaymentInterface untuk tujuan pengujian.
     */
    public class PaymentInterfaceImpl implements PaymentInterface {
        private double amount;
        private Date date;

        @Override
        public boolean processPayment(double amount) {
            if (amount > 0) {
                this.amount = amount;
                this.date = new Date(); // Menetapkan tanggal saat pembayaran diproses
                return true;
            }
            return false;
        }

        @Override
        public double getAmount() {
            return this.amount;
        }

        @Override
        public void setAmount(double amount) {
            this.amount = amount;
        }

        @Override
        public Date getDate() {
            return this.date;
        }

        @Override
        public void setDate(Date date) {
            this.date = date;
        }
    }
    
    /**
     * Test dari metode processPayment dengan jumlah positif.
     */
    @Test
    public void testProcessPayment_Success() {
        System.out.println("processPayment - Success Case");
        double amount = 100.0;
        PaymentInterface instance = new PaymentInterfaceImpl();
        boolean expResult = true;
        boolean result = instance.processPayment(amount);
        assertEquals("Payment should be processed successfully for positive amount.", expResult, result);
        assertEquals("Amount should be set correctly.", amount, instance.getAmount(), 0.001);
        assertNotNull("Date should be set after successful payment.", instance.getDate());
    }
    
    /**
     * Test dari metode processPayment dengan jumlah nol.
     */
    @Test
    public void testProcessPayment_Failure_ZeroAmount() {
        System.out.println("processPayment - Failure Case (Zero Amount)");
        double amount = 0.0;
        PaymentInterface instance = new PaymentInterfaceImpl();
        boolean expResult = false;
        boolean result = instance.processPayment(amount);
        assertEquals("Payment should fail for zero amount.", expResult, result);
        assertEquals("Amount should remain unchanged.", 0.0, instance.getAmount(), 0.001);
        assertNull("Date should not be set for failed payment.", instance.getDate());
    }
    
    /**
     * Test dari metode processPayment dengan jumlah negatif.
     */
    @Test
    public void testProcessPayment_Failure_NegativeAmount() {
        System.out.println("processPayment - Failure Case (Negative Amount)");
        double amount = -50.0;
        PaymentInterface instance = new PaymentInterfaceImpl();
        boolean expResult = false;
        boolean result = instance.processPayment(amount);
        assertEquals("Payment should fail for negative amount.", expResult, result);
        assertEquals("Amount should remain unchanged.", 0.0, instance.getAmount(), 0.001);
        assertNull("Date should not be set for failed payment.", instance.getDate());
    }

    /**
     * Test dari metode getAmount.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        PaymentInterface instance = new PaymentInterfaceImpl();
        instance.setAmount(250.0);
        double expResult = 250.0;
        double result = instance.getAmount();
        assertEquals("getAmount should return the set amount.", expResult, result, 0.001);
    }

    /**
     * Test dari metode setAmount.
     */
    @Test
    public void testSetAmount() {
        System.out.println("setAmount");
        double amount = 300.0;
        PaymentInterface instance = new PaymentInterfaceImpl();
        instance.setAmount(amount);
        assertEquals("setAmount should correctly set the amount.", amount, instance.getAmount(), 0.001);
    }

    /**
     * Test dari metode getDate.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        PaymentInterface instance = new PaymentInterfaceImpl();
        Date currentDate = new Date();
        instance.setDate(currentDate);
        Date expResult = currentDate;
        Date result = instance.getDate();
        assertEquals("getDate should return the set date.", expResult, result);
    }

    /**
     * Test dari metode setDate.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = new Date();
        PaymentInterface instance = new PaymentInterfaceImpl();
        instance.setDate(date);
        assertEquals("setDate should correctly set the date.", date, instance.getDate());
    }
}
