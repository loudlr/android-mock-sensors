package it.unina.android.hardware.mock;

public class MockSensorEventFactory
{
	/* ACCELEROMETER */
	
	public static MockSensorEvent buildAccelerometerXYZEvent(float x, float y, float z)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
		float[] f = {x, y, z};
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_ACCELEROMETER), f);
	}
	
	public static MockSensorEvent buildAccelerometerEvent(float[] f)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_ACCELEROMETER), f);
	}
	
	public static MockSensorEvent buildAccelerometerAccuracyChangeEvent(int accuracy)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_ACCELEROMETER), accuracy);
	}
	
	
	
	/* ORIENTATION */
	
	public static MockSensorEvent buildOrientationXYZEvent(float x, float y, float z)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
		float[] f = {x, y, z};
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_ORIENTATION), f);
	}
	
	public static MockSensorEvent buildOrientationEvent(float[] f)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_ORIENTATION), f);
	}
	
	public static MockSensorEvent buildOrientationAccuracyChangeEvent(int accuracy)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_ORIENTATION), accuracy);
	}
	
	
	
	/* MAGNETIC FIELD */
	
	public static MockSensorEvent buildMagneticFieldXYZEvent(float x, float y, float z)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
		float[] f = {x, y, z};
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_MAGNETIC_FIELD), f);
	}
	
	public static MockSensorEvent buildMagneticFieldEvent(float[] f)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_MAGNETIC_FIELD), f);
	}
	
	public static MockSensorEvent buildMagneticFieldAccuracyChangeEvent(int accuracy)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_MAGNETIC_FIELD), accuracy);
	}
	
	
	
	/* TEMPERATURE */
	
	public static MockSensorEvent buildTemperatureXYZEvent(float x, float y, float z)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
		float[] f = {x, y, z};
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_TEMPERATURE), f);
	}
	
	public static MockSensorEvent buildTemperatureEvent(float[] f)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_TEMPERATURE), f);
	}
	
	public static MockSensorEvent buildTemperatureEvent(int accuracy)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_TEMPERATURE), accuracy);
	}
	
	
	/* TEMPERATURE */
	
	public static MockSensorEvent buildAmbientTemperatureXYZEvent(float x, float y, float z)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
		float[] f = {x, y, z};
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_AMBIENT_TEMPERATURE), f);
	}
	
	public static MockSensorEvent buildAmbientTemperatureEvent(float[] f)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();		
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_AMBIENT_TEMPERATURE), f);
	}
	
	public static MockSensorEvent buildAmbientTemperatureEvent(int accuracy)
	{
		MockSensorManager sensorManager = MockSensorManager.getInstance();
        return new MockSensorEvent(sensorManager.getDefaultSensor(MockSensor.TYPE_AMBIENT_TEMPERATURE), accuracy);
	}	
}
