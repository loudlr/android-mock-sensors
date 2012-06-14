package it.unina.android.hardware;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class SensorEvent
{
	public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

	public int accuracy;
	public Sensor sensor;
	public String time;
	public float[] values = null;
	public int type;
	public long timestamp;

	public SensorEvent(Sensor sensor, int accuracy)
	{
		this(sensor, null, accuracy);
	}
	
	public SensorEvent(Sensor sensor, float[] values)
	{
        this(sensor, values, 0);
	}	
	
	public SensorEvent(Sensor sensor, float[] values, int accuracy)
	{
		super();
        this.values = values;
        this.sensor = sensor;
        this.time = ( new SimpleDateFormat(DATE_FORMAT) ).format( ( new GregorianCalendar() ).getTime() );
        this.timestamp = System.currentTimeMillis();
	}
	
	public SensorEvent(int size) {
        this.values = new float[size];
        this.time = ( new SimpleDateFormat(DATE_FORMAT) ).format( ( new GregorianCalendar() ).getTime() );
        this.timestamp = System.currentTimeMillis();        
    }   
    
	public SensorEvent(android.hardware.SensorEvent event) {
		super();
        this.values = event.values;
        
        if (SensorManager.getInstance() != null)
        	this.sensor = SensorManager.getInstance().getDefaultSensor(event.sensor.getType());
        else
        	this.sensor = null; //new Sensor(event.sensor); //non sara' consistente con il sensore ottenuto dal manager
        
        this.time = ( new SimpleDateFormat(DATE_FORMAT) ).format( ( new GregorianCalendar() ).getTime() );
        this.timestamp = event.timestamp;
        this.accuracy = event.accuracy;
	}    	

	public SensorEvent(it.unina.android.hardware.mock.MockSensorEvent event) {
		super();
        this.values = event.values;
       	
        if (SensorManager.getInstance() != null)
        	this.sensor = SensorManager.getInstance().getDefaultSensor(event.sensor.getType());
        else
        	this.sensor = null; //new Sensor(event.sensor); //non sara' consistente con il sensore ottenuto dal manager
        
        this.time = ( new SimpleDateFormat(DATE_FORMAT) ).format( ( new GregorianCalendar() ).getTime() );
        this.timestamp = event.timestamp;
        this.accuracy = event.accuracy;
	}
}
