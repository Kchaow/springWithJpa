package example.entities;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "album")
@NoArgsConstructor
@AllArgsConstructor
public class Album extends AbstractEntity
{
	@Serial
	private static final long serialVersionUID = 3L;
	
	private Long id;
	private Singer singer;
	private LocalDate releaseDate;
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "singer_id")
	public Singer getSinger()
	{
		return singer;
	}
	
	public void setSinger(Singer singer) 
	{
		this.singer = singer;
	}
	
	@Column
	public String getTitle()
	{
		return title;
	}
	
	@Column(name = "release_date")
	public LocalDate getReleaseDate() 
	{
		return releaseDate;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public void setReleaseDate(LocalDate releaseDate) 
	{
		this.releaseDate = releaseDate;
	}
}
