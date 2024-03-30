package com.example.myweather.commons.api.domains.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.myweather.commons.api.domains.exception.BaseException;

import org.springframework.data.jpa.repository.JpaRepository;
import lombok.SneakyThrows;

import static com.example.myweather.commons.api.domains.data.UtilMyWeather.getMessage;

@Service
public abstract class EndPointServiceImpl<T, ID>   implements IEndPointService<T, ID>{
	
	@Override
	@SneakyThrows
	public T save(T entity) {
		try {
	       	return getDao().save(entity);
		} catch (Exception e) {
			throw new BaseException().builder()
									.message(getMessage("my-weather.api.create.error"))
									.module(nameModule())
									.exception(e)
									.build();
		}
		
	}
	
	@Override
	@SneakyThrows
	public List<T> saveAll(List<T> entityList) {
		try {
			List<T>  entityListResult = this.getDao().saveAll(entityList);
			
			return entityListResult;
		}catch (Exception e) {
			throw new BaseException().builder()
									.message(getMessage("my-weather.api.create-list.error"))
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T update(T entity) {
		try {
			return getDao().save(entity);
		}catch (Exception e) {
			throw new BaseException().builder()
									.message(getMessage("my-weather.api.update.error"))
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T delete(ID id) {
		try {
			T entity = getById(id);
			entity = statusChangeDelete(entity);
			return this.update(entity);
		
		}catch (Exception e) {
				throw new BaseException().builder()
										.message(getMessage("my-weather.api.delete.error"))
										.module(nameModule())
										.exception(e)
										.build();
		}
	}

	@Override
	@SneakyThrows
	public T getById(ID id) {
		try {	
			Optional<T> obj = getDao().findById(id);
			if (obj.isPresent()) {
				return obj.get();
			}
			return null;
		}catch (Exception e) {
			throw new BaseException().builder()
									.message(getMessage("my-weather.api.read.error"))
									.module(nameModule())
									.exception(e)
									.build();
	}
	}

	@Override
	@SneakyThrows
	public List<T> getAll() {
		try {	
			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return returnList;
		}catch (Exception e) {
			throw new BaseException().builder()
									.message(getMessage("my-weather.api.read-list.error"))
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	

	public abstract JpaRepository<T, ID> getDao();
	public abstract T statusChangeDelete(T entity);
	public abstract String nameModule();
}
