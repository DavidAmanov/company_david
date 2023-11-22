package telran.company.dto;

import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;
import telran.view.*;
import telran.company.controler.*;

import java.util.List;

public class CompanyAppl {
    private static final String FILE_NAME = "employees.data";

    public static void main(String[] args) {
        CompanyService company = new CompanyServiceImpl();
        company.restore(FILE_NAME);
        Item[] items = CompanyItems.getItems(company, FILE_NAME);
        inputOutput io = new StandartInputOutput();
        Menu menu = new Menu(items, "Company Application");
        menu.perform(io);
    }
}
