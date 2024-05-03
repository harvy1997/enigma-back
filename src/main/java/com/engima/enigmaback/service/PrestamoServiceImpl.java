package com.engima.enigmaback.service;

import com.engima.enigmaback.Dto.PrestamoDto;
import com.engima.enigmaback.Dto.PrestamoRequestDto;
import com.engima.enigmaback.Dto.PrestamoResponseDto;
import com.engima.enigmaback.entities.Libro;
import com.engima.enigmaback.entities.Prestamo;
import com.engima.enigmaback.entities.Usuario;
import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.repositories.PrestamoRepository;
import com.engima.enigmaback.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.DayOfWeek;

@Scope("singleton")
@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private LibroService libroService;

	@Override
	public List<Prestamo> findAll() throws ApiException {
		return prestamoRepository.findAll();
	}

	@Override
	public Prestamo findById(Integer id) throws ApiException {
		Optional<Prestamo> entity=prestamoRepository.findById(id);
		if (entity.isEmpty())throw new ApiException(HttpStatus.BAD_REQUEST,ErrorMessages.MSG_NO_SE_ENCUENTRA);
		return entity.get();
	}

	@Override
	public Prestamo save(Prestamo entity) throws ApiException {
		return prestamoRepository.save(entity);
	}

	@Override
	public void delete(Prestamo entity) throws ApiException {
		prestamoRepository.delete(entity);
	}

	@Override
	public void deleteById(Integer id) throws ApiException {
		prestamoRepository.deleteById(id);
	}


	@Override
	public PrestamoResponseDto update(PrestamoRequestDto prestamoRequestDto) throws ApiException {
		LocalDate fecha_inicio=LocalDate.now();
		Usuario usuario=usuarioService.findByIdentificacion(prestamoRequestDto.getIdentificacionUsuario());
		Libro libro=libroService.findByIsbn(prestamoRequestDto.getIsbn());

		int diasDeTrabajo = 10; // Número de días hábiles a avanzar
		switch (prestamoRequestDto.getTipoUsuario()){
			case 1:
				diasDeTrabajo=10;
				break;
			case 2:
				diasDeTrabajo=8;
				break;
			case 3:
				diasDeTrabajo=7;
				List<Prestamo> prestamos =this.prestamoRepository.findByUsuario_identificacionUsuario(prestamoRequestDto.getIdentificacionUsuario());
				if(!prestamos.isEmpty()) throw ErrorMessages.get(HttpStatus.BAD_REQUEST,ErrorMessages.MSG_PRESTAMO_INVITADO,prestamoRequestDto.getIdentificacionUsuario());
				break;
			default:
				throw ErrorMessages.get(HttpStatus.BAD_REQUEST,ErrorMessages.MSG_TIPO_USUARIO,prestamoRequestDto.getIdentificacionUsuario());
		}
		LocalDate endDate = calcularDiaFinal(fecha_inicio, diasDeTrabajo);

		Prestamo prestamo=new Prestamo(null,java.sql.Date.valueOf(fecha_inicio),java.sql.Date.valueOf(endDate),null,libro,usuario);
		Prestamo prestamoGuardado=this.save(prestamo);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String fechaFormateada = endDate.format(formatter);

		PrestamoResponseDto response = new PrestamoResponseDto(prestamoGuardado.getIdPrestamo(),fechaFormateada);

		return response;
	}

	@Override
	public List<PrestamoDto> findByIdUsuario(String identificadorUsuario) throws ApiException {
		List<Prestamo> prestamoList=this.prestamoRepository.findByUsuario_identificacionUsuario(identificadorUsuario);
		List<PrestamoDto> prestamoResponseDto=new ArrayList<>();
		for(Prestamo prestamo:prestamoList){
			Libro libro= libroService.findById(prestamo.getLibro().getIdLibro());

			PrestamoDto prestamoDto=PrestamoDto.builder()
					.idPrestamo(prestamo.getIdPrestamo())
					.nombreLibro(libro.getNombre())
					.idLibro(libro.getIdLibro())
					.fechaMaximaEntrega(prestamo.getFechaMaximaEntrega())
					.fechaEntrega(prestamo.getFechaEntrega())
					.isbn(libro.getIsbn())
					.fechaInicio(prestamo.getFechaInicio())
					.build();
			prestamoResponseDto.add(prestamoDto);
		}
		return prestamoResponseDto;
	}

	private LocalDate calcularDiaFinal(LocalDate startDate, int numberOfWorkingDays) {
		LocalDate endDate = startDate;

		for (int i = 0; i < numberOfWorkingDays; ) {
			endDate = endDate.plusDays(1);
			if (endDate.getDayOfWeek() != DayOfWeek.SATURDAY && endDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
				i++; // Incrementa solo si el día no es sábado ni domingo
			}
		}

		return endDate;
	}
}
