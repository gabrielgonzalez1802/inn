package com.inn.address.services;

import com.inn.address.entities.State;
import com.inn.address.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public Optional<State> findById(Long id) {
        return stateRepository.findById(id);
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void deleteById(Long id) {
        stateRepository.deleteById(id);
    }
}