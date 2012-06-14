/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.unina.android.hardware;

public class Sensor
{
	 /**
     * A constant describing an accelerometer sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     */
    public static final int TYPE_ACCELEROMETER = 1;

    /**
     * A constant describing a magnetic field sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     */
    public static final int TYPE_MAGNETIC_FIELD = 2;

    /**
     * A constant describing an orientation sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     *
     * @deprecated use {@link android.hardware.SensorManager#getOrientation
     *             SensorManager.getOrientation()} instead.
     */
    @Deprecated
    public static final int TYPE_ORIENTATION = 3;

    /** A constant describing a gyroscope sensor type */
    public static final int TYPE_GYROSCOPE = 4;

    /**
     * A constant describing an light sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     */
    public static final int TYPE_LIGHT = 5;

    /** A constant describing a pressure sensor type */
    public static final int TYPE_PRESSURE = 6;

    /**
     * A constant describing a temperature sensor type
     *
     * @deprecated use
     *             {@link android.hardware.Sensor#TYPE_AMBIENT_TEMPERATURE
     *             Sensor.TYPE_AMBIENT_TEMPERATURE} instead.
     */
    @Deprecated
    public static final int TYPE_TEMPERATURE = 7;

    /**
     * A constant describing an proximity sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     */
    public static final int TYPE_PROXIMITY = 8;

    /**
     * A constant describing a gravity sensor type.
     * See {@link android.hardware.SensorEvent SensorEvent}
     * for more details.
     */
    public static final int TYPE_GRAVITY = 9;

    /**
     * A constant describing a linear acceleration sensor type.
     * See {@link android.hardware.SensorEvent SensorEvent}
     * for more details.
     */
    public static final int TYPE_LINEAR_ACCELERATION = 10;

    /**
     * A constant describing a rotation vector sensor type.
     * See {@link android.hardware.SensorEvent SensorEvent}
     * for more details.
     */
    public static final int TYPE_ROTATION_VECTOR = 11;

    /**
     * A constant describing a relative humidity sensor type.
     * See {@link android.hardware.SensorEvent SensorEvent}
     * for more details.
     */
    public static final int TYPE_RELATIVE_HUMIDITY = 12;

    /** A constant describing an ambient temperature sensor type */
    public static final int TYPE_AMBIENT_TEMPERATURE = 13;

    /** 
     * A constant describing all sensor types.
     */
    public static final int TYPE_ALL = -1;
    
    private it.unina.android.hardware.mock.MockSensor mockSensor;
    private android.hardware.Sensor realSensor;

    protected Sensor() {
    	
    }  
    
    protected Sensor(it.unina.android.hardware.mock.MockSensor sensor) {
    	this.mockSensor = sensor;
    	this.realSensor = null;
    }

    protected Sensor(android.hardware.Sensor sensor) {
    	this.realSensor = sensor;
    	this.mockSensor = null;
    }    
    
    /**
     * @return name string of the sensor.
     */
    public String getName() {
        if (this.realSensor != null)
        	return this.realSensor.getName();
        else if (this.mockSensor != null)
        	return this.mockSensor.getName();
        else
        	return null; //NOTA: qui ci vorrebbe throw new SensorNotFoundException()
    }

    /**
     * @return vendor string of this sensor.
     */
    public String getVendor() {
        if (this.realSensor != null)
        	return this.realSensor.getVendor();
        else if (this.mockSensor != null)
        	return this.mockSensor.getVendor();
        else
        	return null;
    }

    /**
     * @return generic type of this sensor.
     */
    public int getType() {
        if (this.realSensor != null)
        	return this.realSensor.getType();
        else if (this.mockSensor != null)
        	return this.mockSensor.getType();
        else
        	return 0;
    }

    /**
     * @return version of the sensor's module.
     */
    public int getVersion() {
        if (this.realSensor != null)
        	return this.realSensor.getVersion();
        else if (this.mockSensor != null)
        	return this.mockSensor.getVersion();
        else
        	return 0;
    }

    /**
     * @return maximum range of the sensor in the sensor's unit.
     */
    public float getMaximumRange() {
        if (this.realSensor != null)
        	return this.realSensor.getMaximumRange();
        else if (this.mockSensor != null)
        	return this.mockSensor.getMaximumRange();
        else
        	return 0;
    }

    /**
     * @return resolution of the sensor in the sensor's unit.
     */
    public float getResolution() {
        if (this.realSensor != null)
        	return this.realSensor.getResolution();
        else if (this.mockSensor != null)
        	return this.mockSensor.getResolution();
        else
        	return 0;
    }

    /**
     * @return the power in mA used by this sensor while in use
     */
    public float getPower() {
        if (this.realSensor != null)
        	return this.realSensor.getPower();
        else if (this.mockSensor != null)
        	return this.mockSensor.getPower();
        else
        	return 0;
    }
    
    public it.unina.android.hardware.mock.MockSensor getMockSensor()
    {
    	return this.mockSensor;
    }
    
    public android.hardware.Sensor getRealSensor()
    {
    	return this.realSensor;
    }
}
