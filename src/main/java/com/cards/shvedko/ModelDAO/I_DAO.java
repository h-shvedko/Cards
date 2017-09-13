package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.A_Models;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public interface I_DAO {
    boolean save() throws Exception;

    void update(int id, String[] record);

    void delete(int id);

    boolean validate(A_Models model) throws ConstraintViolationException;

    Object select(String criteria);

    List selectAll();

    List selectAllBy(String criteria);

}
