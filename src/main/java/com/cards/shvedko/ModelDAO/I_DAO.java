package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.A_Models;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public interface I_DAO {
    boolean save() throws Exception;

    void update(int id, String[] record) throws Exception;

    void delete(int id) throws Exception;

    boolean validate(A_Models model) throws ConstraintViolationException;

    A_Models select(String criteria) throws Exception;

    List selectAll() throws Exception;

    List selectAllBy(String criteria) throws Exception;

}
