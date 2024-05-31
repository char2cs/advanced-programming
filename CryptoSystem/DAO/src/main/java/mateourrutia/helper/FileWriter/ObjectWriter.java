package mateourrutia.helper.FileWriter;

import java.security.SecureRandom;

public abstract class ObjectWriter {
	private Integer uuid;

	public ObjectWriter(Integer uuid) {
		this.uuid = uuid;
	}

	public ObjectWriter() {
		generateUuid();
	}

	public Integer getUuid() {
		return uuid;
	}

	private void generateUuid() {
		SecureRandom secureRandom = new SecureRandom();
		this.uuid = secureRandom.nextInt(128);
	}
}
