package org.poo.cb;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
        } else {
            UserFactory userFactory = UserFactory.getInstance();
            ArrayList<Double>number = new ArrayList<>();
            ArrayList<String>numele = new ArrayList<>();
            ArrayList<String>numele1 = new ArrayList<>();
            ArrayList<Double>number2 = new ArrayList<>();
            ArrayList<Double>number3 = new ArrayList<>();
            ArrayList<String>numele2 = new ArrayList<>();
            ArrayList<Integer>number4 = new ArrayList<>();

            try {
                File filePath = new File("src/main/resources/" + args[1]);
                Scanner myReader = new Scanner(filePath);
                myReader.nextLine();
                while (myReader.hasNextLine()){
                    String data = myReader.nextLine();
                    String[] number1 = data.split(",");
                    String nume = number1[0];
                    double sum1 = 0.00;
                    for(int i=1;i<=10;i++){
                        double numar = Double.parseDouble(number1[i]);
                        sum1 += numar;
                    }
                    double sum2 = 0.00;
                    for(int i = 6;i<= 10;i++){
                        sum2 += Double.parseDouble(number1[i]);
                    }
                    number3.add(Double.parseDouble(number1[10]));
                    number.add(sum1/10);
                    number.add(sum2/5);
                    numele.add(nume);
                }
            } catch (FileNotFoundException e){
                System.out.println("eroare");

            }
            try {
                File filePath = new File("src/main/resources/" + args[2]);
                Scanner myReader = new Scanner(filePath);
                CommandInvoker commandInvoker = new CommandInvoker();
                while (myReader.hasNextLine()) {
                    // Read the next line
                    String line = myReader.next();
                    if (line.equals("CREATE")) {
                        int ok = 1;
                        myReader.next();
                        String line2 = myReader.next();
                        String line3 = myReader.next();
                        String line4 = myReader.next();
                        String line5 = myReader.nextLine();
                        for (Utilizator users1 : userFactory.users) {
                            if (users1.email.equals(line2))
                                ok = 0;
                        }
                        if (ok == 1) {
                            userFactory.createUtilizator(Utilizator.Utilizator1.newInstance()
                                    .setEmail(line2)
                                    .setName(line3)
                                    .setLastname(line4)
                                    .setAddress(line5)
                            );
                        } else
                            System.out.println("user with " + line2 + " already exists");
                    } else if (line.equals("LIST")) {
                        String line1 = myReader.next();
                        if (line1.equals("USER")) {
                            int ok =1;
                            String data = myReader.nextLine();
                            for (Utilizator users1 : userFactory.users) {
                                if ((users1.email).length() + 1 == data.length()) {
                                    if ((users1.friends).size() != 0) {
                                        System.out.print("{" + '"' + "email" + '"' + ":" + '"' + users1.email + '"' + "," + '"' + "firstname" + '"' + ":" + '"' + users1.name + '"' + "," + '"' + "lastname" + '"' + ":" + '"' + users1.lastname + '"' + ',' + '"' + "address" + '"' + ":" + '"' + users1.address + '"' + ',' + '"' + "friends" + '"' + ":[" + '"');
                                        for(int i=0;i<users1.friends.size() -  1;i++){
                                            System.out.print(users1.friends.get(i) + ",");
                                        }
                                        System.out.println(users1.friends.get(users1.friends.size()-1) + '"' + "]}");
                                    } else
                                        System.out.println("{" + '"' + "email" + '"' + ":" + '"' + users1.email + '"' + "," + '"' + "firstname" + '"' + ":" + '"' + users1.name + '"' + "," + '"' + "lastname" + '"' + ":" + '"' + users1.lastname + '"' + ',' + '"' + "address" + '"' + ":" + '"' + users1.address + '"' + ',' + '"' + "friends" + '"' + ":[]}");
                                    ok =0;
                                }
                            }
                            if(ok == 1){
                                System.out.println("User with email" + data + " doesn't exist");
                            }
                        } else if (line1.equals("PORTFOLIO")) {
                            String data = myReader.nextLine();
                            for (Utilizator users1 : userFactory.users) {
                                if ((users1.email).length() + 1 == data.length()) {
                                    if(numele2.size() == 0) {
                                        System.out.print("{" + '"' + "stocks" + '"' + ":" + "[]" + "," + '"' + "accounts" + '"' + ":[");
                                    }
                                    else {
                                        System.out.print("{" + '"' + "stocks" + '"' + ":[");
                                        for (int i = 0; i < numele2.size() - 1; i++) {
                                            System.out.print("{" + '"' + "stockname" + '"' + ":" + '"' + numele2.get(i) + '"' + "," + '"' + "amount" + '"' + ":" + number4.get(i) + "}" + ",");

                                        }
                                        System.out.print("{" + '"' + "stockname" + '"' + ":" + '"' + numele2.get(numele2.size() - 1) + '"' + "," + '"' + "amount" + '"' + ":" + number4.get(numele2.size() - 1) + "}");
                                        System.out.print("]" + "," + '"' + "accounts" + '"' + ":[");
                                    }
                                    for (int i = 0; i < users1.accounts.size() - 1; i++) {
                                        System.out.print("{" + '"' + "currencyname" + '"' + ":" + '"' + users1.accounts.get(i) + '"' + "," + '"' + "amount" + '"' + ":" + '"' + users1.sums.get(i) + "0" + '"' + "},");
                                    }
                                    System.out.print("{" + '"' + "currencyname" + '"' + ":" + '"' + users1.accounts.get(users1.accounts.size() - 1) + '"' + "," + '"' + "amount" + '"' + ":" + '"' + users1.sums.get(users1.sums.size() - 1) + "0" + '"' + "}");
                                    System.out.println("]}");
                                }
                            }
                        }
                    } else if (line.equals("EXCHANGE")) {
                        myReader.next();
                        String data = myReader.next();
                        String data1 = myReader.next();
                        String data2 = myReader.next();
                        String data3 = myReader.nextLine();
                        Double sum = Double.parseDouble(data3.substring(1));
                        for (Utilizator users1 : userFactory.users) {
                            if ((users1.email).length() == data.length()) {
                                Command money = new ExchangeMoneyCommand(users1, data1,data2,sum);
                                commandInvoker.setCommand(money);
                                commandInvoker.executeCommand();
                            }
                        }
                    } else if (line.equals("TRANSFER")) {
                        myReader.next();
                        String data = myReader.next();
                        String data1 = myReader.next();
                        String data2 = myReader.next();
                        String data3 = myReader.nextLine();
                        Double sum = Double.parseDouble(data3.substring(1));
                        int ok1 = 1;
                        int l1 = 0;
                        for (Utilizator users1 : userFactory.users) {
                            if ((users1.email).length() == data.length()) {
                                for (int l = 0; l < users1.friends.size(); l++) {
                                    if (users1.friends.get(l).equals(data1)) {
                                        l1 = 1;
                                    }
                                }
                                if (l1 == 1) {
                                    if (users1.Transfer(data2, sum) == 1) {
                                        ok1 = 0;
                                        Command money = new TransferMoneyCommand1(users1, data2,sum);
                                        commandInvoker.setCommand(money);
                                        commandInvoker.executeCommand();
                                    } else
                                        System.out.println("Insufficient amount in account " + data2 + " for transfer");
                                }
                                else
                                    System.out.println("You are not allowed to transfer money to " + data2);
                            }
                        }
                        for (Utilizator users1 : userFactory.users) {
                            if (((users1).email).length() == data1.length()) {
                                if (ok1 == 0) {
                                    Command money = new TransferMoneyCommand2(users1, data2,sum);
                                    commandInvoker.setCommand(money);
                                    commandInvoker.executeCommand();
                                }
                            }
                        }
                    } else if (line.equals("RECOMMEND")) {
                        myReader.nextLine();
                        System.out.print("{" + '"' + "stocksToBuy" + '"' + ":[");
                        for (int i = 0; i < numele.size(); i++) {
                            if (number.get(2 * i) < number.get(2 * i + 1)) {
                                number2.add(number.get(2 * i + 1) - number.get(2 * i));
                                numele1.add(numele.get(i));
                            }
                        }
                        for (int i = 0; i < numele1.size() - 1; i++)
                            for (int j = i + 1; j < numele1.size(); j++) {
                                if (number2.get(i) > number2.get(j)) {
                                    Collections.swap(number2, i, j);
                                    Collections.swap(numele1, i, j);
                                }
                            }

                        for (int j = 0; j < numele1.size() - 1; j++) {
                            System.out.print('"' + numele1.get(j) + '"' + ",");
                        }
                        System.out.print('"' + numele1.get(numele1.size() - 1) + '"');
                        System.out.println("]}");
                    } else if (line.equals("ADD")) {
                        String line1 = myReader.next();
                        if (line1.equals("FRIEND")) {
                            String data = myReader.next();
                            String data1 = myReader.nextLine();
                            int ok1 = 1;
                            int ok2 = 1;
                            for(Utilizator users1 : userFactory.users){
                                if((users1.email).length() == data.length()){
                                    ok1 = 0;
                                }
                            }
                            if(ok1 == 0){
                                for(Utilizator users1 : userFactory.users){
                                    if((users1.email).length() == data.length()){
                                        for(int l=0;l<users1.friends.size();l++){
                                            if(users1.friends.get(l).length() == data1.length() -1){
                                                ok2 =0;
                                            }
                                        }
                                    }
                                }
                                if(ok2 ==1) {
                                    for (Utilizator users1 : userFactory.users) {
                                        if ((users1.email).length() == data.length()) {
                                            Command prieten = new AddFriendCommand(users1, data1.substring(1));
                                            commandInvoker.setCommand(prieten);
                                            commandInvoker.executeCommand();
                                        } else if ((users1.email).length() == data1.length() - 1) {
                                            Command prieten = new AddFriendCommand(users1, data);
                                            commandInvoker.setCommand(prieten);
                                            commandInvoker.executeCommand();
                                        }
                                    }
                                }
                                else
                                    System.out.println("User with " + data1.substring(1) + " is already a friend");
                            }
                            else
                                System.out.println("User with " + data + "doesn't exist");
                        }
                        if (line1.equals("ACCOUNT")) {
                            String data = myReader.next();
                            String data1 = myReader.nextLine();
                            for (Utilizator users1 : userFactory.users) {
                                if ((users1.email).length() == data.length()) {
                                    AccountFactory c = new AccountFactory();
                                    MonedeAccount cont = c.createaccount(users1,data1.substring(1));
                                    cont.cont();
                                }
                            }
                        }
                        if (line1.equals("MONEY")) {
                            String data = myReader.next();
                            String data1 = myReader.next();
                            String data2 = myReader.nextLine();
                            Double sum = Double.parseDouble(data2.substring(1));
                            for (Utilizator users1 : userFactory.users) {
                                if (users1.email.length() == data.length()) {
                                    Command money = new AddAccountMoneyCommand(users1, data1, sum);
                                    commandInvoker.setCommand(money);
                                    commandInvoker.executeCommand();
                                }
                            }
                        }
                    } else if (line.equals("BUY")) {
                        String line1 = myReader.next();
                        if(line1.equals("STOCKS")) {
                            String data = myReader.next();
                            String data1 = myReader.next();
                            String data2 = myReader.nextLine();
                            int numar = Integer.parseInt(data2.substring(1));
                            int k = 0;
                            for (Utilizator user1 : userFactory.users) {
                                if (user1.email.length() == data.length()) {
                                    for (int j = 0; j < numele.size(); j++) {
                                        if (numele.get(j).equals(data1)) {
                                            for(int l=0;l<user1.accounts.size();l++){
                                                if(user1.accounts.get(l).equals("USD")){
                                                    k = l;
                                                }
                                            }
                                            Double numa = user1.sums.get(k) - numar * number3.get(j);
                                            if (numa < 0)
                                                System.out.println("Insufficient amount in account for buying stock");
                                            else {
                                                numele2.add(data1);
                                                number4.add(numar);
                                                String numarAproximatString = String.format("%.2f", numa);
                                                double numarAproximat = Double.parseDouble(numarAproximatString);
                                                user1.sums.set(k, numarAproximat);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if(line1.equals("PREMIUM")){
                            String data = myReader.nextLine();
                            for(Utilizator user1: userFactory.users){
                                if(user1.email.length() == data.length() - 1){
                                    for(int l=0;l<user1.accounts.size();l++){
                                        if(user1.accounts.get(l).equals("USD")){
                                            double suma = user1.sums.get(l);
                                            if(suma >= 100)
                                                user1.sums.set(l,suma - 100);
                                            else
                                                System.out.println("Insufficient amount in account for buying premium option");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                        myReader.nextLine();
                }
                for (int i=userFactory.users.size()-1;i>=0;i--){
                    userFactory.users.remove(i);

                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Eroare");
            }
        }
    }
}