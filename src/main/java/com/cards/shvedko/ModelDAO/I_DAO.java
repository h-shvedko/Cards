package com.cards.shvedko.ModelDAO;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public interface I_DAO {
    void save();

    void update(int id, String[] record);

    void delete(int id);

    boolean validate();

    I_DAO select(String criteria);
}
