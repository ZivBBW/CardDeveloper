package ch.zivfed.bmicalc;

import android.app.Service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceUnitTest {

    @Test
    public void testBMICalculation() {
        LocalService service = new LocalService();

        assertEquals(16.3, service.getBmi(50.0f, 1.75f), 0.00001);
    }

    @Test
    public void testBMIClassification() {
        LocalService service = new LocalService();

        assertEquals(BmiClassification.UNDERWEIGHT, service.getBmiClassification(10.0f));
        assertEquals(BmiClassification.NORMALWEIGHT, service.getBmiClassification(20.0f));
        assertEquals(BmiClassification.PREOBESITY, service.getBmiClassification(28.0f));
        assertEquals(BmiClassification.OBESITY1, service.getBmiClassification(32.0f));
        assertEquals(BmiClassification.OBESITY2, service.getBmiClassification(37.0f));
        assertEquals(BmiClassification.OBESITY3, service.getBmiClassification(50.0f));
    }
}