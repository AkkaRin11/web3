package org.example.bean;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.example.repository.DB;
import org.example.service.Validation;

import java.io.Serializable;
import java.sql.SQLException;

@Getter
@Setter
@Named("inputBean")
@ViewScoped
public class InputBean implements Serializable {
    private String textValue = "0";
    private double sliderValue = 1;
    private double radioValue = 0;

    @Inject
    DB db;

    @Inject
    ResultListBean resultListBean;

    public void sendData() throws SQLException {
        if (Validation.checkValidNumber(textValue)){
            db.addResult(radioValue, Double.valueOf(textValue), sliderValue, System.currentTimeMillis());
            resultListBean.getResultTableHtml();

        } else {
            // что-то сделать, тип неправильные данные
        }
    }

    /*
    Функуия отправки данных
    Фунция отрисовки Графика
    Стартовая страница
     */

    public String redirectToPage() {
        return "main?faces-redirect=true";  // Перенаправление
    }
}
