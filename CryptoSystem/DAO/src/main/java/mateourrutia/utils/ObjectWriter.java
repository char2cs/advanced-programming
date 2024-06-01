package mateourrutia.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * Esta clase solo otorga a sus hijos un Unique ID,
 * Util como soporte para FileWriter y Listed, por ex.
 */
public abstract class ObjectWriter implements Serializable {
	private final UUID uuid;

	public ObjectWriter() {
		this.uuid = UUID.randomUUID();
	}

	protected ObjectWriter(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		return "ObjectWriter{" +
				"uuid=" + uuid +
				"}\n";
	}
}