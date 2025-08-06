package app;

import model.entities.Developer;
import model.entities.Employee;
import model.entities.Intern;
import model.entities.Manager;
import service.EmployeeRegisterService;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

//        Manual testing
        EmployeeRegisterService registerService = new EmployeeRegisterService();

        registerService.registerEmployee("Andre", "86239085545", "mail@mail.com", 3000.00, 2);
        registerService.registerEmployee("Jonas", "88622286645", "mail@email.com", 800.00, 3);
        registerService.registerEmployee("Robs", "93538627010", "soniaaliciacosta@valltech.com.br", 4000.00, 1);

       registerService.removeEmployee("93538627010", sc);


    }
}
