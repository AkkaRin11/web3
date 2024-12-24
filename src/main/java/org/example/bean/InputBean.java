package org.example.bean;

import jakarta.annotation.PostConstruct;
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
    private double hiddenField1 = 0;
    private double hiddenField2 = 0;
    private double hiddenField3 = 0;

    @Inject
    DB db;

    @Inject
    ResultListBean resultListBean;

    public void sendData() throws SQLException {
        if (Validation.checkValidNumber(textValue)){
            db.addResult(radioValue, Double.valueOf(textValue), sliderValue, System.currentTimeMillis());
            resultListBean.getResultTableHtml();

        }
    }

    public void sendGraphData() throws SQLException {
        if (Validation.checkValidNumber(textValue)){
            db.addResult(hiddenField1, hiddenField2, hiddenField3, System.currentTimeMillis());
            resultListBean.getResultTableHtml();

        }
    }

    public String redirectToPage() {
        return "main?faces-redirect=true";
    }
}
