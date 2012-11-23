package it.unina.android.hardware;


public class SensorEventListenerDelegate
{
	private static int DELAY_MS_FASTEST = 0;
	private static int DELAY_MS_GAME = 20; //20000
	private static int DELAY_MS_UI = 60; //66667
	private static int DELAY_MS_NORMAL = 200; //200000	
	
	SensorEventListener listener;
	Sensor sensor;	
    private int mDelay;
    private long mNextUpdateTime;	
	
	public SensorEventListenerDelegate(SensorEventListener listener, Sensor sensor, int rate)
	{
		super();
		this.listener = listener;
		this.sensor = sensor;
        
        //setta il delay a partire dal rate
        switch(rate)
        {
	        case SensorManager.SENSOR_DELAY_FASTEST:
	            this.mDelay = DELAY_MS_FASTEST;
	            break;
	
	        case SensorManager.SENSOR_DELAY_GAME:
	        	this.mDelay = DELAY_MS_GAME;
	            break;
	
	        case SensorManager.SENSOR_DELAY_UI:
	        	this.mDelay = DELAY_MS_UI;
	            break;
	
	        case SensorManager.SENSOR_DELAY_NORMAL:
	        default:
	        	this.mDelay = DELAY_MS_NORMAL; 
        }
		
		this.mNextUpdateTime = 0;
	}
	
	public void riseEvent(SensorEvent event)
	{
		//controlla che l'evento sia del tipo relativo al listener
		if (event.sensor.getType() == this.sensor.getType())
		{
			//TODO: verifica sia passato il trascorso il delay
			//...
			this.listener.onSensorChanged(event);
		}
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
}
