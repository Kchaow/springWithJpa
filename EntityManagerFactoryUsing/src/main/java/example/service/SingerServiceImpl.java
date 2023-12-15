package example.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.entities.Singer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService 
{
	private static final Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly=true)
	@Override
	public Stream<Singer> findAll() {
		return em.createNamedQuery(Singer.FIND_ALL, Singer.class)
				.getResultStream();
	}

	@Transactional(readOnly=true)
	@Override
	public Stream<Singer> findAllWithAlbum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Singer> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Singer> save(Singer singer) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(Singer singer) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(readOnly=true)
	@Override
	public Stream<Singer> findAllByNativeQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
