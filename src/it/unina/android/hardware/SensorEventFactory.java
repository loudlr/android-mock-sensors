package it.unina.android.hardware;

public class SensorEventFactory
{
	/* ACCELEROMETER */
	
	public static SensorEvent buildAccelerometerXYZEvent(float x, float y, float z)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
		float[] f = {x, y, z};
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), f);
	}
	
	public static SensorEvent buildAccelerometerEvent(float[] f)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), f);
	}
	
	public static SensorEvent buildAccelerometerAccuracyChangeEvent(int accuracy)
	{
		SensorManager sensorManager = SensorManager.getInstance();
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), accuracy);
	}
	
	
	
	/* ORIENTATION */
	
	public static SensorEvent buildOrientationXYZEvent(float x, float y, float z)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
		float[] f = {x, y, z};
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), f);
	}
	
	public static SensorEvent buildOrientationEvent(float[] f)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), f);
	}
	
	public static SensorEvent buildOrientationAccuracyChangeEvent(int accuracy)
	{
		SensorManager sensorManager = SensorManager.getInstance();
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), accuracy);
	}
	
	
	
	/* MAGNETIC FIELD */
	
	public static SensorEvent buildMagneticFieldXYZEvent(float x, float y, float z)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
		float[] f = {x, y, z};
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), f);
	}
	
	public static SensorEvent buildMagneticFieldEvent(float[] f)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), f);
	}
	
	public static SensorEvent buildMagneticFieldAccuracyChangeEvent(int accuracy)
	{
		SensorManager sensorManager = SensorManager.getInstance();
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), accuracy);
	}
	
	
	
	/* TEMPERATURE */
	
	public static SensorEvent buildTemperatureXYZEvent(float x, float y, float z)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
		float[] f = {x, y, z};
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE), f);
	}
	
	public static SensorEvent buildTemperatureEvent(float[] f)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE), f);
	}
	
	public static SensorEvent buildTemperatureEvent(int accuracy)
	{
		SensorManager sensorManager = SensorManager.getInstance();
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE), accuracy);
	}
	
	
	/* TEMPERATURE */
	
	public static SensorEvent buildAmbientTemperatureXYZEvent(float x, float y, float z)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
		float[] f = {x, y, z};
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), f);
	}
	
	public static SensorEvent buildAmbientTemperatureEvent(float[] f)
	{
		SensorManager sensorManager = SensorManager.getInstance();		
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), f);
	}
	
	public static SensorEvent buildAmbientTemperatureEvent(int accuracy)
	{
		SensorManager sensorManager = SensorManager.getInstance();
        return new SensorEvent(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), accuracy);
	}	
}
