package com.cards.shvedko.Helpers;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.Users;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.TmpCardsDAO;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public class FillDatabase {

    public static void fillCardsFromCSV(List<String> content) throws UnsupportedEncodingException {

        if(content.size() > 0){
            for (String line : content) {
                String[] values = line.split(",");
                TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();
                tmpCardsDAO.tmpCards.setName(values[1]);
                tmpCardsDAO.tmpCards.setForeignName("qqqqqqqqqqqqqqq");

                if (tmpCardsDAO.validate(tmpCardsDAO.tmpCards)) {
                    try {
                        if (!tmpCardsDAO.save()) {
                            throw new Exception(tmpCardsDAO.errorMsg);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    if (tmpCardsDAO.errorSet != null) {
                        for (ConstraintViolation violation : tmpCardsDAO.errorSet) {
                            Path wrongAttribute = violation.getPropertyPath();
                            String message = violation.getMessage();
                            if (wrongAttribute.iterator().hasNext()) {
                                for (Iterator<Path.Node> it = wrongAttribute.iterator(); it.hasNext(); ) {
                                    Path.Node attribute = it.next();
                                    String nameOfAttrib = attribute.getName();
                                    System.out.println(nameOfAttrib +"-----------"+ message+"\n\n\n\n\n\n\n");
                                }
                            }
                        }
                    }
                }
            }
        }

//        try{
//
//            if(data.length > 0){
//                for(int i=0; i< data.length; i++){
//                    CardsDAO cardsDAO = new CardsDAO();
//                    cardsDAO.cards.setUser((Users) A_Controller.globalUserModel);
//
//                }
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}
