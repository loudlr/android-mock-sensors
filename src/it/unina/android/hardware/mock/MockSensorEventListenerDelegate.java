package it.unina.android.hardware.mock;


public class MockSensorEventListenerDelegate
{
	private static int DELAY_MS_FASTEST = 0;
	private static int DELAY_MS_GAME = 20; //20000
	private static int DELAY_MS_UI = 60; //66667
	private static int DELAY_MS_NORMAL = 200; //200000	
	
	MockSensorEventListener listener;
	MockSensor sensor;	
    private int mDelay;
    private long mNextUpdateTime;	
	
	public MockSensorEventListenerDelegate(MockSensorEventListener listener, MockSensor sensor, int rate)
	{
		super();
		this.listener = listener;
		this.sensor = sensor;
        
        //setta il delay a partire dal rate
        switch(rate)
        {
	        case MockSensorManager.SENSOR_DELAY_FASTEST:
	            this.mDelay = DELAY_MS_FASTEST;
	            break;
	
	        case MockSensorManager.SENSOR_DELAY_GAME:
	        	this.mDelay = DELAY_MS_GAME;
	            break;
	
	        case MockSensorManager.SENSOR_DELAY_UI:
	        	this.mDelay = DELAY_MS_UI;
	            break;
	
	        case MockSensorManager.SENSOR_DELAY_NORMAL:
	        default:
	        	this.mDelay = DELAY_MS_NORMAL; 
        }
		
		this.mNextUpdateTime = 0;
	}
	
	public void riseEvent(MockSensorEvent event)
	{
		//controlla che l'evento sia del tipo relativo al listener
		if (event.sensor.getType() == this.sensor.getType())
		{
			//TODO: verifica sia passato il trascorso il delay
			//...
			
			this.listener.onSensorChanged(event);
		}
	}
	
	public void riseAccuracyEvent(MockSensor sensor, int accuracy)
	{
		if (sensor.getType() == this.sensor.getType())
			this.listener.onAccuracyChanged(sensor, accuracy);
	}	
	
	public MockSensorEventListener getListener()
	{
		return this.listener;
	}
	
	public MockSensor getSensor()
	{
		return this.sensor;
	}
}
