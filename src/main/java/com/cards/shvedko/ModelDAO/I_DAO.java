package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.A_Models;

import javax.validation.ConstraintViolationException;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public interface I_DAO {
    void save();

    void update(int id, String[] record);

    void delete(int id);

    boolean validate(A_Models model) throws ConstraintViolationException;

    I_DAO select(String criteria);

}
