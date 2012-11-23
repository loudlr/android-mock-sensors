package it.unina.android.hardware;


import it.unina.android.hardware.Sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Handler;
import android.util.Log;

public class SensorManager
{	
	/* TAG PER IL LOG */
	private static final String TAG = "SensorManagerMock";
		
	/* ABILITAZIONE SENSORI */
	public static boolean ACCELEROMETER_ENABLED = true;
	public static boolean ORIENTATION_ENABLED = true;
	public static boolean MAGNETIC_FIELD_ENABLED = true;
	public static boolean AMBIENT_TEMPERATURE_ENABLED = true;
	public static boolean TEMPERATURE_ENABLED = true;
	//TODO: gli altri sensori
	
	
	/* Istanze dei sensori */
	private HashMap<Integer, Sensor> sensorsList = null;

	/* INSTANZA SINGLETON */
	private static SensorManager singleton;
	public static SensorManager getInstance()
	{
		if (SensorManager.singleton == null)
			SensorManager.singleton = new SensorManager();
		
		return SensorManager.singleton;
	}
	
	/* LISTENERS DEGLI EVENTI DEI SENSORI */
	public ArrayList<SensorEventListenerDelegate> listeners = null;
	
	private SensorManager()
	{
		sensorsList = new HashMap<Integer, Sensor>();
		this.listeners = new ArrayList<SensorEventListenerDelegate>();
	}
	
	public boolean registerListener(SensorEventListener listener, Sensor sensor, int rate)
	{
		if (listener == null || sensor == null)
			return false;
		
		this.listeners.add(new SensorEventListenerDelegate(listener, sensor, rate));		
		return true;
	}
		
	public void unregisterListener(SensorEventListener listener, Sensor sensor)
	{
		if (listener == null || sensor == null)
			return;
		
		for (int index = 0; index < this.listeners.size(); index++)
		{
			SensorEventListenerDelegate l = this.listeners.get(index);
			if(l.getListener() == listener && l.getSensor().getType() == sensor.getType())
				this.listeners.remove(index);
		}
	}
	
	public void unregisterListener(SensorEventListener listener)
	{
		if (listener == null)
			return;
		
		for (int index = 0; index < this.listeners.size(); index++)
		{
			SensorEventListenerDelegate l = this.listeners.get(index);
			if(l.getListener() == listener)
				this.listeners.remove(index);
		}		
	}
	
	public boolean hasRegisteredSensorListener()
	{
		return (this.listeners != null && this.listeners.size() > 0);
	}
	
	public List<Sensor> getSensorList(int type)
	{
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		
		if (type != Sensor.TYPE_ALL)
		{
			Sensor m = this.getDefaultSensor(type);
			if (m != null)
				sensors.add( m );
		}
		else
		{
			//TODO: aggiungere gli altri sensori...
			if (SensorManager.ACCELEROMETER_ENABLED) sensors.add( this.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) );
			if (SensorManager.ORIENTATION_ENABLED) sensors.add( this.getDefaultSensor(Sensor.TYPE_ORIENTATION) );
			if (SensorManager.MAGNETIC_FIELD_ENABLED) sensors.add( this.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) );
			if (SensorManager.TEMPERATURE_ENABLED) sensors.add( this.getDefaultSensor(Sensor.TYPE_TEMPERATURE) );
			if (SensorManager.AMBIENT_TEMPERATURE_ENABLED) sensors.add( this.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) );
		}
		
		return sensors;
	}	
		
	public Sensor getDefaultSensor(int type)
	{
		Sensor ret = null;

		//TODO: aggiungere gli altri sensori...
		if  (
				(SensorManager.ACCELEROMETER_ENABLED && type == Sensor.TYPE_ACCELEROMETER) ||
				(SensorManager.ORIENTATION_ENABLED  && type == Sensor.TYPE_ORIENTATION) ||
				(SensorManager.MAGNETIC_FIELD_ENABLED && type == Sensor.TYPE_MAGNETIC_FIELD) ||
				(SensorManager.TEMPERATURE_ENABLED && type == Sensor.TYPE_TEMPERATURE) ||
				(SensorManager.AMBIENT_TEMPERATURE_ENABLED && type == Sensor.TYPE_AMBIENT_TEMPERATURE)
			)
		{
			ret = sensorsList.get(type);
			
			if (ret == null)
			{
				ret = new Sensor(type);
				sensorsList.put(type, ret);
			}
		}
		
		return ret;		
	}
	
	public void clearSensorsList()
	{
		sensorsList.clear();
	}
		
	/* PER IL TESTING */
	public void riseSensorEvent(SensorEvent event)
	{
		if (event != null && event.sensor != null && event.values != null && event.values.length == 3)
			Log.v(TAG, "SENSOR_EVENT [Sensor="+event.sensor.getName()+"][values={"+event.values[0]+", "+event.values[1]+", "+event.values[2]+"}]");
		else if (event != null && event.sensor != null && event.values != null && event.values.length < 3)
			Log.v(TAG, "SENSOR_EVENT: event with incorrect values[] length!");
		else if (event != null && event.sensor != null && event.values == null)
				Log.v(TAG, "SENSOR_EVENT: event with no values!");		
		else if (event != null && event.sensor == null && event.values != null && event.values.length == 3)
			Log.v(TAG, "SENSOR_EVENT: event with no sensor!");
		else
			Log.v(TAG, "SENSOR_EVENT: null event!");
		
		if (event != null)
			for(SensorEventListenerDelegate l: this.listeners)
				l.riseEvent(event);
	}
	
	public void riseAccuracyEvent(Sensor sensor, int accuracy)
	{
		if (sensor != null)
			Log.v(TAG, "SENSOR_ACCURACY_EVENT [Sensor="+sensor.getName()+"][accuracy="+accuracy+"]");
		else
			Log.v(TAG, "SENSOR_ACCURACY_EVENT: event with no sensor!");
		
		for(SensorEventListenerDelegate l: this.listeners)
			l.riseAccuracyEvent(sensor, accuracy);
	}
	
	/* Altre funzioni e costanti dal SensorManager reale */
	
	public static float getAltitude(float p0, float p)
	{
		return android.hardware.SensorManager.getAltitude(p0, p);
	}
	
	public static void getAngleChange( float[] angleChange, float[] R, float[] prevR)
	{
		android.hardware.SensorManager.getAngleChange(angleChange, R, prevR);
	}
	
	public static float getInclination(float[] I)
	{	
		return android.hardware.SensorManager.getInclination(I);
	}
	
	public static float[] getOrientation(float[] R, float values[])
	{
		return android.hardware.SensorManager.getOrientation(R, values);
	}
	
	public static void getQuaternionFromVector(float[] Q, float[] rv)
	{
		android.hardware.SensorManager.getQuaternionFromVector(Q, rv);
	}
		
	public static boolean getRotationMatrix(float[] R, float[] I, float[] gravity, float[] geomagnetic)
	{
		return android.hardware.SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);
	}
	
	public static void getRotationMatrixFromVector(float[] R, float[] rotationVector)
	{
		android.hardware.SensorManager.getRotationMatrixFromVector(R, rotationVector);
	}
	
	static boolean	 remapCoordinateSystem(float[] inR, int X, int Y, float[] outR)
	{
		return android.hardware.SensorManager.remapCoordinateSystem(inR, X, Y, outR);
	}
	
    @Deprecated
    public static final int SENSOR_ORIENTATION = 1 << 0;

    @Deprecated
    public static final int SENSOR_ACCELEROMETER = 1 << 1;

    @Deprecated
    public static final int SENSOR_TEMPERATURE = 1 << 2;

    @Deprecated
    public static final int SENSOR_MAGNETIC_FIELD = 1 << 3;

    @Deprecated
    public static final int SENSOR_LIGHT = 1 << 4;

    @Deprecated
    public static final int SENSOR_PROXIMITY = 1 << 5;

    @Deprecated
    public static final int SENSOR_TRICORDER = 1 << 6;

    @Deprecated
    public static final int SENSOR_ORIENTATION_RAW = 1 << 7;

    @Deprecated
    public static final int SENSOR_ALL = 0x7F;

    @Deprecated
    public static final int SENSOR_MIN = SENSOR_ORIENTATION;

    @Deprecated
    public static final int SENSOR_MAX = ((SENSOR_ALL + 1)>>1);

    @Deprecated
    public static final int DATA_X = 0;

    @Deprecated
    public static final int DATA_Y = 1;

    @Deprecated
    public static final int DATA_Z = 2;

    @Deprecated
    public static final int RAW_DATA_INDEX = 3;

    @Deprecated
    public static final int RAW_DATA_X = 3;

    @Deprecated
    public static final int RAW_DATA_Y = 4;

    @Deprecated
    public static final int RAW_DATA_Z = 5;

    public static final float STANDARD_GRAVITY = 9.80665f;
    public static final float GRAVITY_SUN             = 275.0f;
    public static final float GRAVITY_MERCURY         = 3.70f;
    public static final float GRAVITY_VENUS           = 8.87f;
    public static final float GRAVITY_EARTH           = 9.80665f;
    public static final float GRAVITY_MOON            = 1.6f;
    public static final float GRAVITY_MARS            = 3.71f;
    public static final float GRAVITY_JUPITER         = 23.12f;
    public static final float GRAVITY_SATURN          = 8.96f;
    public static final float GRAVITY_URANUS          = 8.69f;
    public static final float GRAVITY_NEPTUNE         = 11.0f;
    public static final float GRAVITY_PLUTO           = 0.6f;
    public static final float GRAVITY_DEATH_STAR_I    = 0.000000353036145f;
    public static final float GRAVITY_THE_ISLAND      = 4.815162342f;

    public static final float MAGNETIC_FIELD_EARTH_MAX = 60.0f;
    public static final float MAGNETIC_FIELD_EARTH_MIN = 30.0f;

    public static final float PRESSURE_STANDARD_ATMOSPHERE = 1013.25f;

    public static final float LIGHT_SUNLIGHT_MAX = 120000.0f;
    public static final float LIGHT_SUNLIGHT     = 110000.0f;
    public static final float LIGHT_SHADE        = 20000.0f;
    public static final float LIGHT_OVERCAST     = 10000.0f;
    public static final float LIGHT_SUNRISE      = 400.0f;
    public static final float LIGHT_CLOUDY       = 100.0f;
    public static final float LIGHT_FULLMOON     = 0.25f;
    public static final float LIGHT_NO_MOON      = 0.001f;

    public static final int SENSOR_DELAY_FASTEST = 0;
    public static final int SENSOR_DELAY_GAME = 1;
    public static final int SENSOR_DELAY_UI = 2;
    public static final int SENSOR_DELAY_NORMAL = 3;

    public static final int SENSOR_STATUS_UNRELIABLE = 0;
    public static final int SENSOR_STATUS_ACCURACY_LOW = 1;
    public static final int SENSOR_STATUS_ACCURACY_MEDIUM = 2;
    public static final int SENSOR_STATUS_ACCURACY_HIGH = 3;
    public static final int AXIS_X = 1;
    public static final int AXIS_Y = 2;
    public static final int AXIS_Z = 3;
    public static final int AXIS_MINUS_X = AXIS_X | 0x80;
    public static final int AXIS_MINUS_Y = AXIS_Y | 0x80;
    public static final int AXIS_MINUS_Z = AXIS_Z | 0x80;
    
	/* non implementate */
	public boolean	 registerListener(SensorEventListener listener, Sensor sensor, int rate, Handler handler)
	{
		return false;
	}
	
	@Deprecated
	public boolean	 registerListener(SensorListener listener, int sensors)
	{
		return false;
	}
	
	@Deprecated
	public boolean	 registerListener(SensorListener listener, int sensors, int rate)
	{
		return false;
	}
	
	@Deprecated
	public void unregisterListener(SensorListener listener)
	{
		//Nothing to do here
	}
	
	@Deprecated
	public void unregisterListener(SensorListener listener, int sensors)
	{
		//Nothing to do here
	}
}
