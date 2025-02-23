package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Command{
    void execute();
}

interface UserReceiver{
    void addFriend(String friend);
    void Exchange(String account1,String account2,Double Sum);
    void addAccountMoney(String account,Double Sum);
    void TransferMoney1(String account,Double Sum);
    void TransferMoney2(String account,Double Sum);

}

abstract class MonedeAccount{
    public abstract void cont();

    protected Utilizator user;
    protected String account;
}

class EuroAccount extends MonedeAccount{
    private Utilizator user;
    private String account;
    public EuroAccount(Utilizator user,String account){
        this.user = user;
        this.account = account;
    }
    public void cont(){
        int ok = 1;
        for(int i=0;i<user.accounts.size();i++){
            if(user.accounts.get(i).equals(account)){
                ok = 0;
            }
        }
        if(ok == 0){
            System.out.println("Account in currency EUR already exists for user");
        }
        if(ok == 1) {
            user.addAccount(account);
        }
    }
}
class USDollar extends MonedeAccount{
    private Utilizator user;
    private String account;
    public USDollar(Utilizator user,String account){
        this.user = user;
        this.account = account;
    }
    public void cont(){
        int ok = 1;
        for(int i=0;i<user.accounts.size();i++){
            if(user.accounts.get(i).equals(account)){
                ok = 0;
            }
        }
        if(ok == 0){
            System.out.println("Account in currency USD already exists for user");
        }
        if(ok == 1) {
            user.addAccount(account);
        }
    }
}

class GPDAccount extends MonedeAccount {
    private Utilizator user;
    private String account;
    public GPDAccount(Utilizator user,String account){
        this.user = user;
        this.account = account;
    }
    public void cont(){
        int ok = 1;
        for(int i=0;i<user.accounts.size();i++){
            if(user.accounts.get(i).equals(account)){
                ok = 0;
            }
        }
        if(ok == 0){
            System.out.println("Account in currency GPD already exists for user");
        }
        if(ok == 1) {
            user.addAccount(account);
        }
    }
}
class JPYAccount extends MonedeAccount {
    private Utilizator user;
    private String account;
    public JPYAccount(Utilizator user,String account){
        this.user = user;
        this.account = account;
    }
    public void cont(){
        int ok = 1;
        for(int i=0;i<user.accounts.size();i++){
            if(user.accounts.get(i).equals(account)){
                ok = 0;
            }
        }
        if(ok == 0){
            System.out.println("Account in currency JPY already exists for user");
        }
        if(ok == 1) {
            user.addAccount(account);
        }
    }
}
class CADAccount extends MonedeAccount {
    private Utilizator user;
    private String account;
    public CADAccount(Utilizator user,String account){
        this.user = user;
        this.account = account;
    }
    public void cont(){
        int ok = 1;
        for(int i=0;i<user.accounts.size();i++){
            if(user.accounts.get(i).equals(account)){
                ok = 0;
            }
        }
        if(ok == 0){
            System.out.println("Account in currency CAD already exists for user");
        }
        if(ok == 1) {
            user.addAccount(account);
        }
    }
}

class AccountFactory{
    public MonedeAccount createaccount(Utilizator user,String account){
        switch (account) {
            case "EUR":
                return new EuroAccount(user,account);
            case "USD":
                return new USDollar(user,account);
            case "GDP":
                return new GPDAccount(user,account);
            case "JPY":
                return new JPYAccount(user,account);
            case "CAD":
                return new CADAccount(user,account);
            default:
                throw new IllegalArgumentException("Unsupported account type: ");
        }
    }
}

class AddFriendCommand implements Command{
    private UserReceiver user;
    private String friend;
    public AddFriendCommand(UserReceiver user,String friend){
        this.user = user;
        this.friend = friend;
    }
    public void execute() {
        user.addFriend(friend);
    }
}

class ExchangeMoneyCommand implements Command{
    private UserReceiver user;
    private String account1;
    private String account2;
    private Double sum;
    public ExchangeMoneyCommand(UserReceiver user,String account1,String account2,Double sum){
        this.user = user;
        this.account1 = account1;
        this.account2 = account2;
        this.sum = sum;
    }
    public void execute(){
        user.Exchange(account1,account2,sum);
    }
}
class AddAccountMoneyCommand implements Command{
    private UserReceiver user;
    private String account;
    private Double sum;
    public AddAccountMoneyCommand(UserReceiver user,String account,Double sum){
        this.user = user;
        this.account = account;
        this.sum = sum;

    }
    public void execute(){
        user.addAccountMoney(account,sum);

    }

}
class TransferMoneyCommand1 implements Command{
    private UserReceiver user1;
    private String account;
    private Double Sum;

    public TransferMoneyCommand1(UserReceiver user1,String account,Double Sum){
        this.user1 = user1;
        this.account = account;
        this.Sum = Sum;
    }
    public void execute(){
        user1.TransferMoney1(account,Sum);
    }


}
class TransferMoneyCommand2 implements Command{
    private UserReceiver user2;
    private String account;
    private Double Sum;

    public TransferMoneyCommand2(UserReceiver user2,String account,Double Sum){
        this.user2 = user2;
        this.account = account;
        this.Sum = Sum;
    }
    public void execute(){
        user2.TransferMoney2(account,Sum);
    }


}

class CommandInvoker {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }
    public void executeCommand(){
        if(command != null)
            command.execute();
        else
            System.out.println("Nicio comanda pentru executie");
    }
}

class UserFactory{
    List<Utilizator> users;
    private static UserFactory instance;
    private UserFactory() {
        // Private constructor to prevent instantiation outside the class
        this.users = new ArrayList<>();
    }
    public static UserFactory getInstance(){
        if(instance == null){
            instance = new UserFactory();
        }
        return instance;
    }

    public Utilizator createUtilizator(Utilizator.Utilizator1 user1){
        Utilizator user = user1.build();
        users.add(user);
        return user;

    }
}
class Utilizator implements UserReceiver {
    public String email;
    public List<String> friends;
    public List<String> accounts;
    public List<Double> sums;
    public String name;
    public String lastname;
    public String address;

    public Utilizator(Utilizator1 user) {
        this.email = user.email;
        this.name = user.name;
        this.lastname = user.lastname;
        this.address = user.address;
        this.friends = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.sums = new ArrayList<>();
    }

    public static Utilizator1 builder(){
        return new Utilizator1();
    }
    public Utilizator(){

    }
    public void addFriend(String friend){
        friends.add(friend);
    }
    public void addAccount(String account){
        accounts.add(account);
        sums.add(0.00);
    }
    public void addAccountMoney(String account,Double sum){
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).equals(account)){
                sums.set(i,sum);
            }
        }
    }
    public void Exchange(String account1,String account2,Double Sum){

        int nr1 = 0;
        double nr2 = 0.00;
        try {
            File filePath = new File("src/main/resources/common/exchangeRates.csv");
            Scanner myReader = new Scanner(filePath);
            String line = myReader.nextLine();
            String[] strings = line.split(",");
            for(int i=1;i<=5;i++){
                if(strings[i].equals(account1)){
                    nr1 = i;
                }
            }
            while(myReader.hasNextLine()){
                String line1 = myReader.nextLine();
                String[] strings1 = line1.split(",");
                if(strings1[0].equals(account2)){
                    String sir = strings1[nr1];
                    nr2 =Double.parseDouble(sir);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("eroare");
        }

        for(int i=0;i<accounts.size();i++){
            int ok = 1;
            if(accounts.get(i).equals(account1)){
                Double sum1 = sums.get(i);
                if(nr2 * Sum > sum1) {
                    ok = 0;
                    System.out.println("Insufficient amount in account " + account1 + " for exchange");
                }
                if(nr2 * 2 * Sum > sum1)
                    sum1 = sum1 - nr2 * Sum - (nr2* Sum)/10;
                else
                    sum1 = sum1 - nr2 * Sum;
                sums.set(i,sum1);
            }
            if(accounts.get(i).equals(account2) && ok == 1){
                Double sum2 = sums.get(i);
                sum2 = sum2 + Sum;
                sums.set(i,sum2);
            }
        }
    }

    public int Transfer(String account,Double Sum){
        int ok = 1;
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).equals(account)){
                Double sum1 = sums.get(i);
                if(sum1 < Sum)
                    ok = 0;
            }
        }
        return ok;
    }

    public void TransferMoney1(String account,Double sum){
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).equals(account)){
                Double sum1 = sums.get(i);
                sum1 -= sum;
                sums.set(i,sum1);
            }
        }
    }
    public void TransferMoney2(String account,Double sum){
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).equals(account)){
                Double sum2 = sums.get(i);
                sum2 += sum;
                sums.set(i,sum2);
            }
        }
    }
    public static class Utilizator1 {
        private String email;
        private String name;
        private String lastname;
        private String address;
        public static Utilizator1 newInstance()
        {
            return new Utilizator1();
        }
        public Utilizator1 setEmail(String email){
            this.email = email;
            return this;
        }
        public Utilizator1 setName(String name){
            this.name = name;
            return this;
        }
        public Utilizator1 setLastname(String lastname){
            this.lastname = lastname;
            return this;
        }
        public Utilizator1 setAddress(String address){
            this.address = address;
            return this;
        }
        public Utilizator build(){
            return new Utilizator(this);
        }
    }
}