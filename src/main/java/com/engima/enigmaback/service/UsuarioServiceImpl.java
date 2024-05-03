package com.engima.enigmaback.service;

import com.engima.enigmaback.Dto.UsuarioResponseDto;
import com.engima.enigmaback.entities.Usuario;
import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.repositories.UsuarioRepository;
import com.engima.enigmaback.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Scope("singleton")
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findAll() throws ApiException {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Integer id) throws ApiException {
		Optional<Usuario> entity=usuarioRepository.findById(id);
		if (entity.isEmpty())throw new ApiException(HttpStatus.BAD_REQUEST, ErrorMessages.MSG_NO_SE_ENCUENTRA);
		return entity.get();
	}

	@Override
	public Usuario save(Usuario entity) throws ApiException {
		return usuarioRepository.save(entity);
	}

	@Override
	public void delete(Usuario entity) throws ApiException {
		usuarioRepository.delete(entity);
	}

	@Override
	public void deleteById(Integer id) throws ApiException {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario findByIdentificacion(String identificacion) {
		return usuarioRepository.findByidentificacionUsuario(identificacion);
	}

	@Override
	public List<UsuarioResponseDto> usuariosPrestamos(Date fecha) {
		List<Usuario> usuarioList=usuarioRepository.usuariosPrestamos(fecha);
		List<UsuarioResponseDto> usuarioResponseDtoList=new ArrayList<>();
		for(Usuario usuario:usuarioList){
			UsuarioResponseDto usuarioResponseDto=UsuarioResponseDto.builder()
					.identificacionUsuario(usuario.getIdentificacionUsuario())
					.nombre(usuario.getNombre())
					.idUsuario(usuario.getIdUsuario())
					.build();
			usuarioResponseDtoList.add(usuarioResponseDto);
		}
		return usuarioResponseDtoList;
	}
}
