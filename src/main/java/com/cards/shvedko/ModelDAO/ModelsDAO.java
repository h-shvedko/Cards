package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Services.DBService;
import org.hibernate.Session;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class ModelsDAO implements I_DAO {

    protected final DBService dbServise;
    protected final Session session;

    public ModelsDAO(){
        dbServise = new DBService();
        session = dbServise.sessionFactory.openSession();
    }

    public void save() {}

    public void update(int id, String[] record){}

    public void delete(int id){}

    public boolean validate() {
        return false;
    }

    public I_DAO select(String criteria) {
        return null;
    }
}
