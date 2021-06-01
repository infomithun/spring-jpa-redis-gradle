package com.mithun.redis.service;

import com.mithun.redis.ResouceNotFoundException;
import com.mithun.redis.repository.EmployeeRepository;
import com.mithun.redis.repository.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    @Transactional
    @CachePut(value = "employee", key = "#employee.id")
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    @CachePut(value = "employee", key = "#employee.id")
    public Employee update(Employee employee) {
        repository.findById(employee.getId()).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found :: " + employee.getId()));
        final Employee updatedEmployee = repository.save(employee);
        return updatedEmployee;
    }

    @Override
    @Transactional
    @Cacheable(value = "employee", key = "#id")
    public Employee get(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found :: " + id));
    }

    @Override
    @Transactional
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "employee", key = "#id")
    public void delete(int id) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Employee not found :: " + id));
        repository.delete(employee);
    }
}
