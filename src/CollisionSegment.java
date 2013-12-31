
public class CollisionSegment {
	 public int objectID;
     private boolean enabled;
     public Segment segment;
     float colitionDistance;
     
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public float getColitionDistance() {
		if (enabled)
			return colitionDistance;
		else
			return -1;
	}
	public void setColitionDistance(float colitionDistance) {
		this.colitionDistance = colitionDistance;
	}
     
     
}
