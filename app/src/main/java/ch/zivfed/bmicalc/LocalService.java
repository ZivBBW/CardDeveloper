package ch.zivfed.bmicalc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public float getBmi(float weight, float height) {
        float result = weight / (height * height);
        result = (float) (Math.round(result * 10.00) / 10.00);
        return result;
    }

    public BmiClassification getBmiClassification(float bmi) {
        if(bmi < 18.5) {
            return BmiClassification.UNDERWEIGHT;
        } else if(bmi < 24.9) {
            return BmiClassification.NORMALWEIGHT;
        } else if(bmi < 29.9) {
            return BmiClassification.PREOBESITY;
        } else if(bmi < 34.9) {
            return BmiClassification.OBESITY1;
        } else if(bmi < 39.9) {
            return BmiClassification.OBESITY2;
        } else {
            return BmiClassification.OBESITY3;
        }
    }
}
