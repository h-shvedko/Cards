package com.cards.shvedko.Helpers;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.Users;
import com.cards.shvedko.ModelDAO.CardsDAO;

public class FillDatabase {

    static void fillCardsFromCSV(String fileName){
        try{
            String[] data = ReadCSV.read(fileName);

            if(data.length > 0){
                for(int i=0; i< data.length; i++){
                    CardsDAO cardsDAO = new CardsDAO();
                    cardsDAO.cards.setUser((Users) A_Controller.globalUserModel);

                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
