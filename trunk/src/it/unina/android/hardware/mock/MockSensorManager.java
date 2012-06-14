package it.unina.android.hardware.mock;


import it.unina.android.hardware.Sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

public class MockSensorManager
{	
	/* TAG PER IL LOG */
	private static final String TAG = "SensorManagerMock";
	
	/* DA SensorManager di Android */
	public static final int SENSOR_DELAY_FASTEST = 0;
	public static final int SENSOR_DELAY_GAME = 1;
	public static final int SENSOR_DELAY_UI = 2;
	public static final int SENSOR_DELAY_NORMAL = 3;
	
	/* ABILITAZIONE SENSORI */
	public static boolean ACCELEROMETER_ENABLED = true;
	public static boolean ORIENTATION_ENABLED = false;
	public static boolean MAGNETIC_FIELD_ENABLED = true;
	public static boolean AMBIENT_TEMPERATURE_ENABLED = true;
	public static boolean TEMPERATURE_ENABLED = true;
	//TODO: gli altri sensori
	
	
	/* Istanze dei sensori */
	private HashMap<Integer, MockSensor> sensorsList = null;

	/* INSTANZA SINGLETON */
	private static MockSensorManager singleton;
	public static MockSensorManager getInstance()
	{
		if (MockSensorManager.singleton == null)
			MockSensorManager.singleton = new MockSensorManager();
		
		return MockSensorManager.singleton;
	}
	
	/* LISTENERS DEGLI EVENTI DEI SENSORI */
	public ArrayList<MockSensorEventListenerDelegate> listeners = null;
	
	private MockSensorManager()
	{
		sensorsList = new HashMap<Integer, MockSensor>();
		this.listeners = new ArrayList<MockSensorEventListenerDelegate>();
	}
	
	public boolean registerListener(MockSensorEventListener listener, MockSensor sensor, int rate)
	{
		if (listener == null || sensor == null)
			return false;
		
		this.listeners.add(new MockSensorEventListenerDelegate(listener, sensor, rate));		
		return true;
	}
		
	public void unregisterListener(MockSensorEventListener listener, MockSensor sensor)
	{
		if (listener == null || sensor == null)
			return;
		
		for (int index = 0; index < this.listeners.size(); index++)
		{
			MockSensorEventListenerDelegate l = this.listeners.get(index);
			if(l.getListener() == listener && l.getSensor().getType() == sensor.getType())
				this.listeners.remove(index);
		}
	}
	
	public void unregisterListener(MockSensorEventListener listener)
	{
		if (listener == null)
			return;
		
		for (int index = 0; index < this.listeners.size(); index++)
		{
			MockSensorEventListenerDelegate l = this.listeners.get(index);
			if(l.getListener() == listener)
				this.listeners.remove(index);
		}		
	}
	
	public List<MockSensor> getSensorList(int type)
	{
		ArrayList<MockSensor> sensors = new ArrayList<MockSensor>();
		
		if (type != Sensor.TYPE_ALL)
		{
			MockSensor m = this.getDefaultSensor(type);
			if (m != null)
				sensors.add( m );
		}
		else
		{
			//TODO: aggiungere gli altri sensori...
			if (MockSensorManager.ACCELEROMETER_ENABLED) sensors.add( this.getDefaultSensor(MockSensor.TYPE_ACCELEROMETER) );
			if (MockSensorManager.ORIENTATION_ENABLED) sensors.add( this.getDefaultSensor(MockSensor.TYPE_ORIENTATION) );
			if (MockSensorManager.MAGNETIC_FIELD_ENABLED) sensors.add( this.getDefaultSensor(MockSensor.TYPE_MAGNETIC_FIELD) );
			if (MockSensorManager.TEMPERATURE_ENABLED) sensors.add( this.getDefaultSensor(MockSensor.TYPE_TEMPERATURE) );
			if (MockSensorManager.AMBIENT_TEMPERATURE_ENABLED) sensors.add( this.getDefaultSensor(MockSensor.TYPE_AMBIENT_TEMPERATURE) );
		}
		
		return sensors;
	}	
		
	public MockSensor getDefaultSensor(int type)
	{
		MockSensor ret = null;

		//TODO: aggiungere gli altri sensori...
		if  (
				(MockSensorManager.ACCELEROMETER_ENABLED && type == MockSensor.TYPE_ACCELEROMETER) ||
				(MockSensorManager.ORIENTATION_ENABLED  && type == MockSensor.TYPE_ORIENTATION) ||
				(MockSensorManager.MAGNETIC_FIELD_ENABLED && type == MockSensor.TYPE_MAGNETIC_FIELD) ||
				(MockSensorManager.TEMPERATURE_ENABLED && type == MockSensor.TYPE_TEMPERATURE) ||
				(MockSensorManager.AMBIENT_TEMPERATURE_ENABLED && type == MockSensor.TYPE_AMBIENT_TEMPERATURE)
			)
		{
			ret = sensorsList.get(type);
			
			if (ret == null)
			{
				ret = new MockSensor(type);
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
	public void riseSensorEvent(MockSensorEvent event)
	{
		for(MockSensorEventListenerDelegate l: this.listeners)
			l.riseEvent(event);
	}
	
	public void riseAccuracyEvent(MockSensor sensor, int accuracy)
	{
		for(MockSensorEventListenerDelegate l: this.listeners)
			l.riseAccuracyEvent(sensor, accuracy);
	}
}
