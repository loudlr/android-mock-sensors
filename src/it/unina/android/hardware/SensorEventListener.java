package it.unina.android.hardware;

public interface SensorEventListener {
	public void onAccuracyChanged(Sensor sensor, int accuracy);
	public void onSensorChanged(SensorEvent event);
}
