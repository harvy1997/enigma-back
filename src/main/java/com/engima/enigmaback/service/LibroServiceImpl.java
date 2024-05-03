package com.engima.enigmaback.service;

import com.engima.enigmaback.entities.Libro;
import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.repositories.LibroRepository;
import com.engima.enigmaback.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;

	@Override
	public List<Libro> findAll() throws ApiException {
		return libroRepository.findAll();
	}

	@Override
	public Libro findById(Integer id) throws ApiException {
		Optional<Libro> entity=libroRepository.findById(id);
		if (entity.isEmpty())throw new ApiException(HttpStatus.BAD_REQUEST, ErrorMessages.MSG_NO_SE_ENCUENTRA);
		return entity.get();
	}

	@Override
	public Libro save(Libro entity) throws ApiException {
		return libroRepository.save(entity);
	}

	@Override
	public void delete(Libro entity) throws ApiException {
		libroRepository.delete(entity);
	}

	@Override
	public void deleteById(Integer id) throws ApiException {
		libroRepository.deleteById(id);
	}

	@Override
	public Libro findByIsbn(String isbn) {
		return libroRepository.findByIsbn(isbn);
	}
}
