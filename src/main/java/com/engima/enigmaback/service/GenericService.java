package com.engima.enigmaback.service;

import com.engima.enigmaback.exceptions.ApiException;

import java.util.List;

public interface GenericService<T, ID> {

	public List<T> findAll() throws ApiException;

	public T findById(ID id) throws ApiException;

	public T save(T entity) throws ApiException;

	public void delete(T entity) throws ApiException;

	public void deleteById(ID id) throws ApiException;

}
