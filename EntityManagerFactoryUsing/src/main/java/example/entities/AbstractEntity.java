package example.entities;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	@Version
	@Column(name = "version")
	protected int version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	

	public int getVersion()
	{
		return version;
	}
	
	public void setVersion(int version)
	{
		this.version = version;
	}
}
