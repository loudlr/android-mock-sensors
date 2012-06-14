package it.unina.android.hardware.mock;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import android.content.Context;

public class MockSensorEvent
{
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	public int accuracy;
	public MockSensor sensor;
	public String time;
	public float[] values = null;
	public long timestamp;
	
	public MockSensorEvent(MockSensor sensor, int accuracy)
	{
		this(sensor, null, accuracy);
	}
	
	public MockSensorEvent(MockSensor sensor, float[] values)
	{
        this(sensor, values, 0);
	}	
	
	public MockSensorEvent(MockSensor sensor, float[] values, int accuracy)
	{
		super();
        this.values = values;
        this.sensor = sensor;
        this.time = ( new SimpleDateFormat(DATE_FORMAT) ).format( ( new GregorianCalendar() ).getTime() );
        this.timestamp = System.currentTimeMillis();
	}
	
	public MockSensorEvent(int size) {
        this.values = new float[size];
        this.time = ( new SimpleDateFormat(DATE_FORMAT) ).format( ( new GregorianCalendar() ).getTime() );
        this.timestamp = System.currentTimeMillis();        
    }		
	
}
