package td.nargathar_remake;

import java.util.HashMap;

public enum TileType {
	
	// Tiled ID is one less than reference ID below
	// Tiled id (+1), collidable?, name, changes map (teleport), hazardous (kills player), changes map (explosion), changes map (magic tiles)
	ROCK(1, true, "Rock", false, false, false, false),
	GRASS(2, true, "Grass", false, false, false, false),
	TELEPORT(3, true, "Teleporter", true, false, false, false),
	SPIKES(4, true, "Spikes", false, true, false, false),
	LAVA(5, true, "Lava", false, true, false, false),
	SPIKE2(6, true, "Spike 2", false, true, false, false),
	LAVA2(7, true, "Lava 2", false, true, false, false),
	IGNITER(8, true, "Igniter", false, false, true, false),
	DYNAMITE(9, true, "Dynamite", false, false, false, false),
	USEDIGNITER(11, true, "Used Igniter", false, false, false, false),
	MAGICTILE(12, true, "Magic Tile", false, false, false, false),
	ANGEL(13, true, "Angel", false, false, false, true),
	USEDANGEL(14, true, "Used Angel", false, false, false, false),
	ROCKHOT(15, true, "Rock Hot", false, false, false, false);
	
	public static final int TILE_SIZE = 32;
	
	private int id;
	private boolean collidable;
	private String name;
	private boolean change;
	private boolean hazardous;
	private boolean explodes;
	private boolean magic;
	
	private TileType (int id, boolean collidable, String name, boolean change, boolean hazardous, boolean explodes, boolean magic) {
		this.id = id;
		this.collidable = collidable;
		this.name = name;
		this.change = change;
		this.hazardous = hazardous;
		this.explodes = explodes;
		this.magic = magic;
	}

	public int getId() {
		return id;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public String getName() {
		return name;
	}
	
	public boolean changesMap() {
		return change;
	}
	
	public boolean isHazardous() {
		return hazardous;
	}
	
	public boolean explodes() {
		return explodes;
	}
	
	public boolean magic() {
		return magic;
	}
	
	private static HashMap<Integer, TileType> tileMap;
	
	static {
		tileMap = new HashMap<Integer, TileType>();
		for (TileType tileType : TileType.values()) {
			tileMap.put(tileType.getId(), tileType);
		}
	}
	
	public static TileType getTileTypeById (int id) {
		return tileMap.get(id);
	}
}
