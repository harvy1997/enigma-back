package com.engima.enigmaback.service;

import com.engima.enigmaback.entities.TipoUsuario;
import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.repositories.TipoUsuarioRepository;
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
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	@Override
	public List<TipoUsuario> findAll() throws ApiException {
		return tipoUsuarioRepository.findAll();
	}

	@Override
	public TipoUsuario findById(Integer id) throws ApiException {
		Optional<TipoUsuario> entity=tipoUsuarioRepository.findById(id);
		if (entity.isEmpty())throw new ApiException(HttpStatus.BAD_REQUEST, ErrorMessages.MSG_NO_SE_ENCUENTRA);
		return entity.get();
	}

	@Override
	public TipoUsuario save(TipoUsuario entity) throws ApiException {
		return tipoUsuarioRepository.save(entity);
	}

	@Override
	public void delete(TipoUsuario entity) throws ApiException {
		tipoUsuarioRepository.delete(entity);
	}

	@Override
	public void deleteById(Integer id) throws ApiException {
		tipoUsuarioRepository.deleteById(id);
	}

}
