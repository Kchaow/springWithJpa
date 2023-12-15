package example.service;

import java.util.Optional;
import java.util.stream.Stream;

import example.entities.Singer;

public interface SingerService 
{
	String ALL_SINGER_NATIVE_QUERY =
			"SELECT id, first_name, last_name, birth_date, version FROM singer";
	
	Stream<Singer> findAll();
	Stream<Singer> findAllWithAlbum();
	Optional<Singer> findById(Long id);
	Optional<Singer> save(Singer singer);
	void delete(Singer singer);
	Stream<Singer> findAllByNativeQuery();
}
