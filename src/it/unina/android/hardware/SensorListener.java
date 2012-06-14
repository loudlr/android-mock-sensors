package it.unina.android.hardware;

@Deprecated
public interface SensorListener {
	public void onSensorChanged(int sensor, float[] values);
	public void onAccuracyChanged(int sensor, int accuracy);
}
