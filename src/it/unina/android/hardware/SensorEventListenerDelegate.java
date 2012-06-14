package it.unina.android.hardware;

import it.unina.android.hardware.mock.MockSensor;
import it.unina.android.hardware.mock.MockSensorEvent;

public class SensorEventListenerDelegate implements it.unina.android.hardware.mock.MockSensorEventListener, android.hardware.SensorEventListener
{	
	SensorEventListener listener;
	Sensor sensor;	
	
	public SensorEventListenerDelegate(SensorEventListener listener, Sensor sensor, int rate)
	{
		super();
		this.listener = listener;
		this.sensor = sensor;
	}
	
	public void riseSensorEvent(SensorEvent event)
	{				
		if (event.sensor.getType() == this.sensor.getType())
			this.listener.onSensorChanged(event);
	}
	
	public void riseAccuracyEvent(Sensor sensor, int accuracy)
	{
		if (sensor.getType() == this.sensor.getType())
			this.listener.onAccuracyChanged(sensor, accuracy);
	}
	
	public SensorEventListener getListener()
	{
		return this.listener;
	}
	
	public Sensor getSensor()
	{
		return this.sensor;
	}
	
	/* PER IL TESTING 
	public void riseSensorEvent(SensorEvent event)
	{
		for(SensorEventListenerDelegate l: this.listeners)
			l.riseSensorEvent(event);
	}

	public void riseAccuracyEvent(Sensor sensor, int accuracy)
	{
		for(SensorEventListenerDelegate l: this.listeners)
			l.riseAccuracyEvent(sensor, accuracy);
	}	*/

	/* EVENTI DEL SENSOR-MANAGER REALE */	
	public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
		it.unina.android.hardware.Sensor s = SensorManager.getInstance().getDefaultSensor(sensor.getType());
		this.riseAccuracyEvent(s, accuracy);
	}

	public void onSensorChanged(android.hardware.SensorEvent event)
	{
		it.unina.android.hardware.SensorEvent evt = new it.unina.android.hardware.SensorEvent(event);		
		this.riseSensorEvent(evt);
	}

	/* EVENTI DEL SENSOR-MANAGER FITTIZIO */
	public void onAccuracyChanged(MockSensor sensor, int accuracy) {
		it.unina.android.hardware.Sensor s = SensorManager.getInstance().getDefaultSensor(sensor.getType());
		this.riseAccuracyEvent(s, accuracy);		
	}

	public void onSensorChanged(MockSensorEvent event) {
		it.unina.android.hardware.SensorEvent evt = new it.unina.android.hardware.SensorEvent(event);
		this.riseSensorEvent(evt);		
	}
}
