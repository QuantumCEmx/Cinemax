package com.navigation;

import com.storage.Database;
import com.storage.File;
import com.table.Table;

import java.util.Map;

import org.json.JSONObject;

import com.general.Theater;
import com.input.Input;

public class Selection {

    public final File file = new File();

    public Selection() {
    }

    public Selection(Map<String, String> schema, String header, String pathName, String prefix) {

        this.SelectionLCRUD(schema, header, pathName, prefix);

    }

    public void SelectionLCRUD(Map<String, String> schema, String header, String pathName, String prefix) {
        Nav selector = new Nav(header, new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();

        Input input = new Input();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(pathName);
                    break;
                case 2:
                    new Database().insert(new Input().getData(schema), pathName, prefix);
                    break;
                case 3:
                    String id = input.getId();

                    new Database().update(pathName, id, new Input().getData(schema));
                    break;
                case 4:
                    new Database().delete(pathName, input.getId());
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }
    }

    public void theater() {

        Nav selector = new Nav("Theater", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();
        File file = new File();

        Input input = new Input();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(file.theater);
                    break;
                case 2:
                    new Database().insert(new Theater().getData(), file.theater, "TH");
                    break;
                case 3:
                    String id = input.getId();

                    new Database().update(file.theater, id, new Theater().getData());
                    break;
                case 4:
                    new Database().delete(file.theater, input.getId());
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

    public void movie() {

        Nav selector = new Nav("Movie", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(file.movie);
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

    public void round(Map<String, String> schema, String pathName, String prefix) {

        Nav selector = new Nav("Round", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();
        Input input = new Input();
        File file = new File();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(pathName);
                    break;
                case 2:
                     String[] fruits = {"#","id", "theater_name" };
                    new Database().get(file.theater ,fruits);
                    new Database().insert(new Input().getData(schema), pathName, prefix);
                    break;
                case 3:
                    String id = input.getId();
                    new Database().update(pathName, id, new Input().getData(schema));
                    break;
                case 4:
                    new Database().delete(pathName, input.getId());
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

    public void ticket() {

        Nav selector = new Nav("Ticket", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(file.ticket);
                    break;

                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

}
