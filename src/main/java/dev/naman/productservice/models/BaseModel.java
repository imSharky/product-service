package dev.naman.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
//	@Id
//	@GeneratedValue(generator = "uuidgenerator")
//	@GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
//	@Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
//	private UUID uuid;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}