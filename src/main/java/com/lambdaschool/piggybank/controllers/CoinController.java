package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {

    @Autowired
    CoinRepository coinrepo;

    private List<Coin> findCoin(List<Coin> myList, CheckCoin tester){
        List<Coin> tempList = new ArrayList<>();

        for (Coin c: myList) {
            if(tester.test(c)) {
                tempList.add(c);
            }
        }
        return tempList;
    }

    @GetMapping(value = "total", produces = "application/json")
    public ResponseEntity<?>ListAllCoins() {

        List<Coin> myList = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(myList::add);

        double total = 0;
        for(int i = 0; i<myList.toArray().length; i++) {
            total = total+myList.get(i).getValue();

            if (myList.get(i).getQuantity()==1){
                System.out.println(myList.get(i).getQuantity()+" "+ myList.get(i).getName());

            }else {
                System.out.println(myList.get(i).getQuantity()+" "+ myList.get(i).getNameplural());
            }
        }

        System.out.println("The piggy bank holds "+total);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
