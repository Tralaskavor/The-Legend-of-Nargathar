package td.nargathar_remake;

public enum EntityType {
	
	PLAYER("player", 30, 59, 100), // id, width, height, weight for gravity
	PLAYERSHADOW("player shadow", 24, 28, 100),
	MONSTER("monster", 32, 38, 60);
	
	private String id;
	private int width, height;
	private float weight;
	
	private EntityType(String id, int width, int height, float weight) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}
}
