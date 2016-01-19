package project05;

public class VenusCrater {
	
	int craterId;
	String craterName;
	float latitude,longitude,diameter;
	
	public VenusCrater(int craterId, String craterName, float latitude,
			float longitude, float diameter) {
		super();
		this.craterId = craterId;
		this.craterName = craterName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.diameter = diameter;
	}
	public int getCraterId() {
		return craterId;
	}
	public void setCraterId(int craterId) {
		this.craterId = craterId;
	}
	public String getCraterName() {
		return craterName;
	}
	public void setCraterName(String craterName) {
		this.craterName = craterName;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getDiameter() {
		return diameter;
	}
	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}
	

}
