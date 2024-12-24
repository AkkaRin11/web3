package org.example.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.example.repository.DB;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named("resultListBean")
@ViewScoped
public class ResultListBean implements Serializable {

    @Inject
    DB db;

    @Getter
    @Setter
    private String customHtml;

    @PostConstruct
    public void init() throws SQLException {
        getResultTableHtml();
    }

    public void getResultTableHtml() throws SQLException {
        List<List<String>> resultTable = db.getResultTable();

        customHtml =
                "<table id=\"resultTable\">\n" +
                "    <tr>\n" +
                "        <th>Результат</th>\n" +
                "        <th>X</th>\n" +
                "        <th>Y</th>\n" +
                "        <th>R</th>\n" +
                "        <th>Время работы</th>\n" +
                "        <th>Время</th>\n" +
                "    </tr>";

        for (List<String> column: resultTable){
            customHtml += "<tr>";
            for (String ceil: column){
                customHtml += "<th>" + ceil + "</th>";
            }
            customHtml += "</tr>";
        }

        customHtml += "</table>";
    }
}
