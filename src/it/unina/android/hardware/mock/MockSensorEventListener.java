package it.unina.android.hardware.mock;

public interface MockSensorEventListener {
	public void onAccuracyChanged(MockSensor sensor, int accuracy);
	public void onSensorChanged(MockSensorEvent event);
}
